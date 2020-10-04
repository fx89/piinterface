import { Component, Input, EventEmitter, AfterViewInit } from '@angular/core';
import { of } from 'rxjs';
import { v4 as uuid } from 'uuid';

@Component({
  selector: 'loading-log',
  templateUrl: './loading-log.component.html',
  styleUrls: ['./loading-log.component.css']
})
export class LoadingLogComponent implements AfterViewInit {

  @Input()
  id : string = "_" + uuid();

  @Input()
  msgAcquireFunction : Function = () => of("Test message");

  @Input()
  msgIconFunction : Function = (msg) => "";

  @Input()
  msgClassFunction : Function = (msg) => "info"; // Possible return values: info, warning, error

  @Input()
  msgTitleFunction: Function = (msg) => "";

  @Input()
  msgDescriptionFunction : Function = (msg) => msg.toString();

  @Input()
  showEvent : EventEmitter<boolean> = new EventEmitter<boolean>();

  @Input()
  pollingIntervalMS : number = 1000;

  private messages : any[] = [];

  private frameComponent : HTMLElement;
  private listboxComponent : HTMLElement;
  private modalComponent : HTMLElement;

  private visibility : boolean = false;

  constructor() { }

  ngAfterViewInit() {
    this.frameComponent = document.getElementById(this.getLoadingLogFrameId());
    this.listboxComponent = document.getElementById(this.getLoadingLogListboxId());
    this.modalComponent = document.getElementById(this.getLoadingLogModalId());

    this.showEvent.subscribe((visibility:boolean) => {
      this.setVisibility(visibility);
    });

    this.monitorLog();
  }

  getLoadingLogFrameId() : string {
    return this.id + "_loading_log_frame";
  }

  getLoadingLogListboxId() : string {
    return this.id + "_loading_log_listbox";
  }

  getLoadingLogModalId() : string {
    return this.id + "_loading_log_modal";
  }

  private setVisibility(visibility:boolean) {
    if (this.frameComponent == null || this.frameComponent == undefined) {
      this.frameComponent= document.getElementById(this.getLoadingLogFrameId());
    }

    if (this.modalComponent == null || this.modalComponent == undefined) {
      this.modalComponent = document.getElementById(this.getLoadingLogModalId());
    }

    this.modalComponent.classList.remove('fade-out');
    this.modalComponent.classList.remove('fade-in');

    this.frameComponent.classList.remove('loading-log-frame-hidden');
    this.frameComponent.classList.remove('loading-log-frame-visible');

    if (visibility) {
      this.frameComponent.classList.add('loading-log-frame-visible');
      this.modalComponent.classList.add('fade-in');
      this.modalComponent.style.visibility = "visible"; // TODO: find out why the CSS animations are not working
      this.messages = [];
    } else {
      this.frameComponent.classList.add('loading-log-frame-hidden');
      this.modalComponent.classList.add('fade-out');
      this.modalComponent.style.visibility = "hidden"; // TODO: find out why the CSS animations are not working
    }

    this.visibility = visibility;
  }

  private monitorLog() : void {
    setInterval(() => {
      if (this.visibility) {
        if (this.listboxComponent == null || this.listboxComponent == undefined) {
          this.listboxComponent = document.getElementById(this.getLoadingLogListboxId());
        }

        this.msgAcquireFunction().subscribe((ret) => {
          this.messages = ret;
          setTimeout(() => { this.listboxComponent.scrollTop = this.listboxComponent.scrollHeight; }, 100);
        });
      }
    }, this.pollingIntervalMS);
  }
}
