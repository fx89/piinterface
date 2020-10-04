import { Injectable, EventEmitter } from '@angular/core';
import { of } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class LoadingLogService {

  // Loading log properties
  msgAcquireFunction : Function = () => of("Test message");
  msgIconFunction : Function = (msg) => "";
  msgClassFunction : Function = (msg) => "info"; // Possible return values: info, warning, error
  msgTitleFunction: Function = (msg) => "";
  msgDescriptionFunction : Function = (msg) => msg.toString();
  pollingIntervalMS : number = 1000;

  // Used to show / hide the loading log
  showEvent : EventEmitter<boolean> = new EventEmitter<boolean>();

  constructor() { }

  // Functionality

  public setProperties(
    msgAcquireFunction : Function = () => [],
    msgIconFunction : Function = (msg) => "",
    msgClassFunction : Function = (msg) => "info", // Possible return values: info, warning, error
    msgTitleFunction: Function = (msg) => "",
    msgDescriptionFunction : Function = (msg) => msg.toString(),
    pollingIntervalMS : number = 1000
  ) : void {
    this.msgAcquireFunction = msgAcquireFunction;
    this.msgIconFunction = msgIconFunction;
    this.msgClassFunction = msgClassFunction;
    this.msgTitleFunction = msgTitleFunction;
    this.msgDescriptionFunction = msgDescriptionFunction;
    this.pollingIntervalMS = pollingIntervalMS;
  }

  public show() : void {
    this.showEvent.emit(true);
  }

  public hide() : void {
    this.showEvent.emit(false);
  }

}
