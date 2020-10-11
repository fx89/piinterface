import { Component, Input, OnInit, Output, EventEmitter } from '@angular/core';

@Component({
  selector: 'switch',
  templateUrl: './switch.component.html',
  styleUrls: ['./switch.component.css']
})
export class SwitchComponent implements OnInit {

  @Input()
  state : boolean = false;

  @Output()
  stateChange : EventEmitter<boolean> = new EventEmitter<boolean>();

  @Output()
  onChange : EventEmitter<boolean> = new EventEmitter<boolean>();

  @Input()
  offStateLabel : string = "Off";

  @Input()
  onStateLabel : string = "On";

  constructor() { }

  ngOnInit() {
  }

  onClick() {
    this.state = !this.state;
    this.stateChange.emit(this.state);
    this.onChange.emit(this.state);
  }
}
