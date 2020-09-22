import { Component, Input, OnInit } from '@angular/core';
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
  href : string = "www.altavista.com"

  @Input()
  altPictureSrc : string = "assets/skins/" + getSkinName() + "/icons/x.png";

  constructor() { }

  ngOnInit() {
  }

  getImgId() : string {
    return this.id + "_img";
  }

  imgLoadError($event) {
    (<any>document.getElementById(this.getImgId())).src = this.altPictureSrc;
  }
}
