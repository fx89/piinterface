import { Injectable, EventEmitter } from '@angular/core';
import { getSkinName } from 'src/app/utils/skin-utils';

@Injectable({
  providedIn: 'root'
})
export class ToastService {

  // Toast attributes
  toastTitle : string = "Toast";
  toastDescription : string = "This is a toast";
  toastIconPathName : string = "assets/skins/" + getSkinName() + "/icons/i.png";
  toastShowEvent : EventEmitter<any> = new EventEmitter<any>();
  toastMinVisibleTimeMs : number = 3000;
  toastAdditionalClassName : string = "info"; // Possible values: info, warning, error;

  constructor() { }

  // The functionality that's supposed to be exposed by the service
  public show(
    title:string="Toast", description:string="This is a toast",
    additionalClassName:string="info",
    minVisibleTimeMS:number=3000,
    iconPathName:string="assets/skins/" + getSkinName() + "/icons/i.png"
  ) {
    // Compute the visible time according to the number of characters
    let nChars : number = (title ? title.length : 0) + (description ? description.length : 0);
    let visibleTimeMS : number = 100 * nChars;
    if (visibleTimeMS < minVisibleTimeMS) {
      visibleTimeMS = minVisibleTimeMS;
    }

    // Set the attributes
    this.toastTitle = title;
    this.toastDescription = description;
    this.toastIconPathName = iconPathName;
    this.toastMinVisibleTimeMs = visibleTimeMS;
    this.toastAdditionalClassName = additionalClassName;

    // Emit the SHOW event
    this.toastShowEvent.emit(visibleTimeMS);
  }

  public showInfo(
    title:string="Toast", description:string="This is a toast",
    minVisibleTimeMS:number=3000,
    iconPathName:string="assets/skins/" + getSkinName() + "/icons/i.png"
  ) {
    this.show(title, description, "info", minVisibleTimeMS, iconPathName);
  }

  public showWarning(
    title:string="Toast", description:string="This is a toast",
    minVisibleTimeMS:number=3000,
    iconPathName:string="assets/skins/" + getSkinName() + "/icons/exclamation.png"
  ) {
    this.show(title, description, "warning", minVisibleTimeMS, iconPathName);
  }
  
  public showError(
    title:string="Toast", description:string="This is a toast",
    minVisibleTimeMS:number=3000,
    iconPathName:string="assets/skins/" + getSkinName() + "/icons/x.png"
  ) {
    this.show(title, description, "error", minVisibleTimeMS, iconPathName);
  }
}
