import { Injectable, EventEmitter } from '@angular/core';

@Injectable({
  providedIn: 'root'
})
export class LoadingModalService {

  public stateEvent : EventEmitter<boolean> = new EventEmitter<boolean>();

  public title : string = "LOADING";

  public subtitle : string = "please be patient";

  public status : boolean;

  constructor() { }

  public show(title?:string, subtitle?:string) {
    if (title) {
      this.title = title;
    }

    if (subtitle) {
      this.subtitle = subtitle;
    }

    this.stateEvent.emit(true);
  }

  public hide() {
    this.stateEvent.emit(false);
  }
}
