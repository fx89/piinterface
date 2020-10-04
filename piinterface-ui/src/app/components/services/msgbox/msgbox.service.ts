import { Injectable, EventEmitter } from '@angular/core';

@Injectable({
  providedIn: 'root'
})
export class MsgboxService {

  // Makes sure that calling the show() method won't do anything ithe message box is already visible
  private isMsgboxVisible : boolean = false;

  // Message box properties
  public msgBoxHasTwoButtons : boolean = false;
  public msgBoxTitle : string = "Message";
  public msgBoxMessage : string = "Do you want?";
  public msgBoxWidthPX : number = 100;
  public msgBoxHeightPX : number = 30;
  public msgBoxOkButtonText : string = "Ok";
  public msgBoxCancelButtonText : string = "No";
  public msgBoxShowEvent : EventEmitter<any> = new EventEmitter<any>();
  public msgBoxOkCallback : Function = () => {}
  public msgBoxCancelCallback : Function = () => {}

  constructor() { }

  // The only functionality this service is actually supposed to expose
  public show(
      title:string="Message", message:string="This is a message box",
      okCallback:Function=()=>{}, cancelCallback:Function=()=>{},
      widthPX:number=300, heightPX:number= 110,
      okButtonText:string="Ok",cancelButtonText?:string
  ) {
      if (this.isMsgboxVisible == false) {
        this.msgBoxHasTwoButtons = cancelButtonText ? true : false;
        this.msgBoxTitle = title;
        this.msgBoxMessage = message;
        this.msgBoxWidthPX = widthPX;
        this.msgBoxHeightPX = heightPX;
        this.msgBoxOkButtonText = okButtonText;
        this.msgBoxCancelButtonText = cancelButtonText;
        this.msgBoxOkCallback  = okCallback;
        this.msgBoxCancelCallback = cancelCallback;

        this.msgBoxShowEvent.emit();
        this.isMsgboxVisible = true;
      }
  }

  public showSimpleMsgBox(
    title:string="Message", message:string="This is a message box",
    okCallback:Function=()=>{},
    okButtonText:string="Ok", cancelButtonText?:string
  ) {
    this.show(title, message, okCallback, ()=>{}, 300, 110, okButtonText, cancelButtonText);
  }

  // Called by the serviceable msgbox when the OK button is clicked
  public serviceOkEventCallback : Function = () => {
    this.isMsgboxVisible = false;
    this.msgBoxOkCallback();
  }

  // Called by the serviceable msgbox when the CANCEL button is clicked
  public serviceCancelEventCallback : Function = () => {
    this.isMsgboxVisible = false;
    this.msgBoxCancelCallback();
  }
}
