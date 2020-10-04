import { Component, Input, OnInit, Output, EventEmitter } from '@angular/core';
import { v4 as uuid } from 'uuid';

@Component({
  selector: 'menu-button',
  templateUrl: './menu-button.component.html',
  styleUrls: ['./menu-button.component.css']
})
export class MenuButtonComponent implements OnInit {

  @Input()
  id : string = "_" + uuid();

  @Input()
  text : string = "Ok";

  @Input()
  widthPX : number = 50;

  @Output()
  onClick : EventEmitter<any> = new EventEmitter<any>();

  constructor() { }

  ngOnInit() {
  }

  onButtonClicked($event) {
    this.onClick.emit($event);
  }
}
