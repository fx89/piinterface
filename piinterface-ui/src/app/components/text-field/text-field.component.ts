import { Component, OnInit, Input, Output, EventEmitter, AfterViewInit } from '@angular/core';
import { v4 as uuid } from 'uuid';
import { ValidationService } from '../services/validation/validation.service';

@Component({
  selector: 'text-field',
  templateUrl: './text-field.component.html',
  styleUrls: ['./text-field.component.css']
})
export class TextFieldComponent implements OnInit, AfterViewInit {

  @Input()
  id : string = "_" + uuid();

  @Input()
  value : string;

  @Output()
  valueChange : EventEmitter<string> = new EventEmitter<string>();

  @Input()
  placeholder: string = "Enter value";

  @Input()
  widthPX: number;

  @Input()
  widthPCT: number;

  @Input()
  heightPX: number = 17;

  @Input()
  validationFunction : Function = () => "";

  isValid : boolean = true;

  constructor(private validationService : ValidationService) { }

  ngOnInit() {
    this.validate();
  }

  ngAfterViewInit() {
    window.addEventListener("click", (event) => {
      this.validate();
    });
  }
  


  onChange($event) {
    this.validate();
    this.valueChange.emit(this.value);
  }

  onKeyUp($event) {
    this.validate();
  }

  validate() {
    let validationMessage : string = this.validationFunction(this.value);
    this.validationService.setValidationMessage(this.getTextboxId(), validationMessage);
    this.isValid = validationMessage ? false : true;
  }

  getTextboxId() : string {
    return this.id + "_textbox";
  }


}
