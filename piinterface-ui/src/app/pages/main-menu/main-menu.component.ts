import { Component, OnInit } from '@angular/core';
import { NavigationService } from 'src/app/services/navigation-service/navigation.service';
import { getSkinName } from '../../utils/skin-utils';

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
  calendarIconPicPathName : string

  constructor(
    public navigationService : NavigationService
  ) { 
    this.skinName = getSkinName();
    this.piInstancesIconPicPathName = this.composeIconPicPathName(this.skinName, "pi-instances");
    this.pinsIconPicPathName = this.composeIconPicPathName(this.skinName, "pins");
    this.pinGroupsIconPicPathName = this.composeIconPicPathName(this.skinName, "pin-groups");
    this.buttonsIconPicPathName = this.composeIconPicPathName(this.skinName, "buttons");
    this.calendarIconPicPathName = this.composeIconPicPathName(this.skinName, "calendar");
  }

  ngOnInit() {
  }

  private composeIconPicPathName(skinName:string, iconFileName:string) : string {
    return "assets/skins/" + skinName + "/icons/main-menu/" + iconFileName + ".png";
  }

  goToPiInstances() {
    this.navigationService.currentPage = "pi-instances";
  }

  goToPins() {
    this.navigationService.currentPage = "pins";
  }

  goToPinGroups() {
    this.navigationService.currentPage = "pin-groups";
  }

  goToButtons() {
    this.navigationService.currentPage = "buttons";
  }

  goToCalendar() {
    this.navigationService.currentPage = "calendar";
  }
}
