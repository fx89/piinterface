import { Component, AfterViewInit, EventEmitter } from '@angular/core';
import { LoadingModalService } from 'src/app/components/services/loading-modal/loading-modal.service';
import { MsgboxService } from 'src/app/components/services/msgbox/msgbox.service';
import { IpAddressRange } from 'src/app/model/IpAddressRange';
import { PiInstance } from 'src/app/model/PiInstance';
import { DataService } from 'src/app/services/data-service/data-service.service';
import { getSkinName } from 'src/app/utils/skin-utils';
import { arrRemove } from 'src/app/utils/array-utils';
import { ToastService } from 'src/app/components/services/toast/toast.service';
import { BackendNotification } from 'src/app/model/BackendNotification';
import { LoadingLogService } from 'src/app/components/services/loading-log/loading-log.service';
import { PiInstancePin } from 'src/app/model/PiInstancePin';

@Component({
  selector: 'app-pi-instances',
  templateUrl: './pi-instances.component.html',
  styleUrls: ['./pi-instances.component.css']
})
export class PiInstancesComponent implements AfterViewInit {

  accessiblePiInstances : PiInstance[] = [];
  selectedPiInstance : any;
  piInstanceTitleFunction : Function = (item : PiInstance) => item.name;
  piInstanceDescriptionFunction : Function = (item : PiInstance) => "[" + item.id + "] " + item.lastRegisteredAddress + (item.isOffline ? " (OFFLINE)" : "");
  piInstanceIconFunction : Function = () => "assets/skins/" + getSkinName() + "/icons/main-menu/pi-instances.png";

  ipAddressRanges : IpAddressRange[] = [];
  selectedIpAddressRange : IpAddressRange;
  ipRangeTitleFunction : Function = (item : IpAddressRange) => item.name;
  ipRangeDescriptionFunction : Function = (item : IpAddressRange) => "[" + item.id + "] " + item.prefix + "(" + item.rangeStart + "/" + item.rangeEnd + ")";
  ipRangeIconFunction : Function = () => "assets/skins/" + getSkinName() + "/icons/list-element-squashed.png";
  ipRangeNameValidationFunction : Function = (value : string) => value && value.length >= 3 ? null : "Name must have at least 3 characters";
  ipRangePrefixValidationFunction : Function = (value : string) => value && value.length >= 6 ? null : "Prefix must have at least 6 characters";
  ipRangeDigitValidationFunction : Function = (value : string) => value && value.length > 0 ? null : "IP range element must have at least one caracter";

  selectedPiInstancePins : PiInstancePin[] = [];
  pinsTableHeaderColumnTitles = ["Board ID", "GPIO ID", "Name", "Available"];
  pinsTableDisplayFunctions = [
    (item:PiInstancePin) => item ? item.boardId : "",
    (item:PiInstancePin) => item ? item.gpioId : "",
    (item:PiInstancePin) => item ? item.name : "",
    (item:PiInstancePin) => (item && item.isAvailable) ? "Yes" : "No"
  ];
  pinsTableRowNumberFunction = (item:PiInstancePin, rowIndex:number, colIndex:number) => item ? item.id : rowIndex;

  rangeEditDialogShowEvent : EventEmitter<any> = new EventEmitter<any>();
  piInstancePinsDialogShowEvent : EventEmitter<any> = new EventEmitter<any>();

  private nListsLoaded : number = 0;

  private isScanning : boolean = false;

  constructor(
    private dataService : DataService,
    private loadingModalService : LoadingModalService,
    private msgBoxService : MsgboxService,
    private toastService : ToastService,
    private loadingLogService : LoadingLogService
  ) { 
    
  }

  private updateLoadedLists() {
    this.nListsLoaded++;
    if (this.nListsLoaded >= 2) {
      this.loadingModalService.hide();
    }
  }

  ngAfterViewInit() {
    this.loadingModalService.title = "LOADING";
    this.loadingModalService.subtitle = "instances and ranges";
    this.loadingModalService.show();

    this.nListsLoaded = 0;

    this.dataService.piInstancesRepository.errorEventEmitter.subscribe((err) => {
      this.toastService.showError(err.error, err.message);
      this.updateLoadedLists();
      this.isScanning = false;
    });

    this.dataService.ipAddressRangesRepository.errorEventEmitter.subscribe((err) => {
      this.toastService.showError(err.error, err.message);
      this.updateLoadedLists();
    });

    this.dataService.piInstancePinsRepository.errorEventEmitter.subscribe((err) => {
      this.toastService.showError(err.error, err.message);
      this.loadingModalService.hide();
    });

    this.loadIpRanges();
    this.loadPiInstances();
  }

  private loadPiInstances() {
    this.dataService.piInstancesRepository.getCustomOperation("findAll").subscribe(ret => {
      this.accessiblePiInstances = ret;
      this.updateLoadedLists();
      this.deselectPiInstances();
    });
  }

  private loadIpRanges() {
    this.dataService.ipAddressRangesRepository.getCustomOperation("findAll").subscribe(ret => {
      this.ipAddressRanges = ret;
      this.updateLoadedLists();
      this.deselectIpAddressRanges();
    });
  }

  deselectPiInstances() {
    this.selectedPiInstance = undefined;
  }

  deselectIpAddressRanges() {
    this.selectedIpAddressRange = undefined;
  }

  showPinsButtonClicked() {
    this.loadingModalService.title = "LOADING";
    this.loadingModalService.subtitle = "Loading pins from registry";
    this.loadingModalService.show();

    this.dataService.piInstancePinsRepository
      .getCustomOperation("findAllByPiInstanceId", new Map([["piInstanceId", this.selectedPiInstance.id.toString()]]))
      .subscribe((ret) => {
        this.selectedPiInstancePins = ret;
        this.loadingModalService.hide();
        this.piInstancePinsDialogShowEvent.emit();
      });
    
  }

  rangeAddButtonClicked() {
    this.selectedIpAddressRange = new IpAddressRange(null, "New range", "192.168.1.", "1", "255");
    this.rangeEditDialogShowEvent.emit();
  }

  rangeRemoveBUttonClicked() {
    this.msgBoxService.showSimpleMsgBox(
      "Confirm deletion",
      "Are you sure you want to delete the selected IP address range?",
      () => {
        this.loadingModalService.subtitle = "Removing IP address range"
        this.loadingModalService.show();

        this.dataService.ipAddressRangesRepository
          .deleteCustomOperation("delete", null, new Map([["id", this.selectedIpAddressRange.id.toString()]]))
            .subscribe(() => {
              this.ipAddressRanges = arrRemove(this.ipAddressRanges, this.selectedIpAddressRange, (a,b)=> a.id == b.id);
              this.selectedIpAddressRange = undefined;
              this.loadingModalService.hide();
            });

      },
      "Yes", "No"
    );
  }

  onIpAddressRangeEditDialogSave = () => {
    this.loadingModalService.show();

    this.dataService.ipAddressRangesRepository
      .saveCustomOperation("save", this.selectedIpAddressRange).subscribe((ret) => {
        this.selectedIpAddressRange = ret;
        this.ipAddressRanges.push(ret);
        this.loadingModalService.hide();
      });
  }

  onIpAddressRangeEditDialogCancel = () => {
    this.selectedIpAddressRange = undefined;
  }

  onScanButtonClicked() {
    this.startLongRunningActionWithMonitoring(
      "Confirm scan",
      "Scanning for PI instances might take a while. Are you sure you want to proceed?",
      "Syncronizing PI instances",
      "synchronizePiInstances",
      null,
      "log"
    );
  }

  onResyncPiInstanceClicked() {
    this.startLongRunningActionWithMonitoring(
      "Confirm resync",
      "Synchronizing PI instance status and pins. Are you sure you want to proceed?",
      "Syncronizing PI instance",
      "resyncPiInstance",
      new Map([["piInstanceId", this.selectedPiInstance.id.toString()]])
    );
  }

  startLongRunningActionWithMonitoring(
    msgBoxTitle:string, msgBoxMessage:string,
    initialStatusMessage:string,
    operationName:string,
    params?:Map<string,string>,
    modalType:string = "loading" // Possible values: loading, log
  ) {
    this.msgBoxService.showSimpleMsgBox(
      msgBoxTitle,
      msgBoxMessage,
      () => {
        if (modalType == "loading") {
          this.isScanning = true;
          this.loadingModalService.title = "RUNNING";
          this.loadingModalService.subtitle = initialStatusMessage;
          this.loadingModalService.show();
        } else {
          this.loadingLogService.show();
        }

        this.dataService.piInstancesRepository.getCustomOperation(operationName, params)
          .subscribe(() => {
            if (modalType != "loading") {
              this.loadingLogService.hide();
            }
            this.isScanning = false;
            this.loadingModalService.title = "LOADING";
            this.loadingModalService.subtitle = "Reloading PI instances";
            this.loadPiInstances();
          });

        if (modalType == "loading") {
          this.monitorNotifications();
        }
      },
      "Yes", "No"
    );
  }

  monitorNotifications() {
    const timerId = setInterval(() => {
      if (this.isScanning) {
        this.dataService.notificationsRepository.getCustomOperation("findLast")
          .subscribe((ret : BackendNotification) => {
            if (ret && ret.message) {
              this.loadingModalService.subtitle = ret.message;
            }
          });
      } else {
        clearTimeout(timerId);
      }
    }, 1000);
  }
}
