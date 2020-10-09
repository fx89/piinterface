import { Component, Input, Output, OnInit, EventEmitter } from '@angular/core';
import { getSkinName } from '../../utils/skin-utils';
import { v4 as uuid } from 'uuid';

@Component({
  selector: 'icon',
  templateUrl: './icon.component.html',
  styleUrls: ['./icon.component.css']
})
export class IconComponent implements OnInit {
  @Input()
  id : string = "_" + uuid();

  @Input()
  picture : string

  @Input()
  text : string = "icon text"

  @Input()
  href : string = undefined

  @Input()
  altPictureSrc : string = "assets/skins/" + getSkinName() + "/icons/x.png";

  @Input()
  hasFrame : boolean = true;

  @Input()
  selected : boolean = false;

  @Input()
  hoverable : boolean = false;
  
  @Output()
  onClick : EventEmitter<any> = new EventEmitter<any>();

  constructor() { }

  ngOnInit() {
  }

  getImgId() : string {
    return this.id + "_img";
  }

  imgLoadError($event) {
    (<any>document.getElementById(this.getImgId())).src = this.altPictureSrc;
  }

  onImgClicked() {
    this.onClick.emit();
  }
}
