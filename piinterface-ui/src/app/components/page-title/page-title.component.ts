import { Component, OnInit, Input } from '@angular/core';
import { v4 as uuid } from 'uuid';
import { getSkinName } from '../../utils/skin-utils';

@Component({
  selector: 'page-title',
  templateUrl: './page-title.component.html',
  styleUrls: ['./page-title.component.css']
})
export class PageTitleComponent implements OnInit {

  @Input()
  id : string = "_" + uuid();

  @Input()
  iconFileRef : string;

  @Input()
  titleText : string;

  constructor() { }

  ngOnInit() {
  }

  getIconId() : string {
    return this.id + "_icon";
  }

  getIconSrc() : string {
    return "assets/skins/" + getSkinName() + "/icons/" + this.iconFileRef + ".png";
  }

  getBackgroundId() : string {
    return this.id + "_bg";
  }
}
