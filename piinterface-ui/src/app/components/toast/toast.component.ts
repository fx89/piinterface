import { Component, Input, EventEmitter, AfterViewInit } from '@angular/core';
import { v4 as uuid } from 'uuid';

@Component({
  selector: 'toast',
  templateUrl: './toast.component.html',
  styleUrls: ['./toast.component.css']
})
export class ToastComponent implements AfterViewInit {
  @Input()
  id : string = "_" + uuid();

  @Input()
  title : string = "Toast";

  @Input()
  description : string = "This is a toast";

  @Input()
  iconPathName : string;

  @Input()
  showEvent : EventEmitter<number> = new EventEmitter<number>();

  @Input()
  minVisibleTimeMs : number = 3000;

  private visibleTimeMS : number = 3000;

  @Input()
  additionalClassName : string = "info"; // Possible values: info, warning, error;

  private toastElement : HTMLElement;

  constructor() { }

  ngAfterViewInit() {
    if (this.showEvent) {
      this.showEvent.subscribe((visibleTimeMS : number) => {
        this.visibleTimeMS = visibleTimeMS < this.minVisibleTimeMs ? this.minVisibleTimeMs : visibleTimeMS;
        this.showToast();
      });
    }
  }

  getToastFrameId() : string {
    return this.id + "_toast_frame";
  }

  showToast() {
    if (this.toastElement == null || this.toastElement == undefined) {
      this.toastElement = document.getElementById(this.getToastFrameId());
    } 

    if (this.toastElement) {
      this.toastElement.classList.remove('toast-frame-hidden');
      this.toastElement.classList.remove('toast-frame-visible');
      this.toastElement.classList.add('toast-frame-visible');
    }

    setTimeout(() => { this.hideToast(); }, this.visibleTimeMS);
  }

  hideToast() {
    if (this.toastElement == null || this.toastElement == undefined) {
      this.toastElement = document.getElementById(this.getToastFrameId());
    } 

    if (this.toastElement) {
      this.toastElement.classList.remove('toast-frame-hidden');
      this.toastElement.classList.add('toast-frame-hidden');
      this.toastElement.classList.remove('toast-frame-visible');
    }
  }

  toastClicked() {
    this.hideToast();
  }
}
