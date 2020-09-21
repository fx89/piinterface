import { Component, OnInit } from '@angular/core';
import { getSkinName } from '../../utils/skin-utils'

@Component({
  selector: 'app-main-menu',
  templateUrl: './main-menu.component.html',
  styleUrls: ['./main-menu.component.css']
})
export class MainMenuComponent implements OnInit {

  skinName : string
  piInstancesIconPicPathName : string
  pinsIconPicPathName : string
  pinGroupsIconPicPathName : string
  buttonsIconPicPathName : string

  constructor() { 
    this.skinName = getSkinName();
    this.piInstancesIconPicPathName = this.composeIconPicPathName(this.skinName, "pi-instances");
    this.pinsIconPicPathName = this.composeIconPicPathName(this.skinName, "pins");
    this.pinGroupsIconPicPathName = this.composeIconPicPathName(this.skinName, "pin-groups");
    this.buttonsIconPicPathName = this.composeIconPicPathName(this.skinName, "buttons");
  }

  ngOnInit() {
  }

  private composeIconPicPathName(skinName:string, iconFileName:string) : string {
    return "assets/skins/" + skinName + "/icons/main-menu/" + iconFileName + ".png";
  }
}
