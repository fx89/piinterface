import { Component, Input, Output, OnInit, EventEmitter } from '@angular/core';

@Component({
  selector: 'pushbutton',
  templateUrl: './pushbutton.component.html',
  styleUrls: ['./pushbutton.component.css']
})
export class PushbuttonComponent implements OnInit {

  @Input()
  widthPX : number = 70;

  @Input()
  heightPX : number = 17;

  @Input()
  enabled : boolean = true;

  @Input()
  icon : string = null;

  @Input()
  text : string = "Ok";

  @Output()
  onClick : EventEmitter<any> = new EventEmitter<any>();

  constructor() { }

  ngOnInit() {
  }

  buttonClicked($event) {
    if (this.enabled) {
      this.onClick.emit($event);
    }
  }
}
