import { AfterViewInit, Component, OnInit } from '@angular/core';
import { piInstanceDescriptionFunction, piInstanceIconFunction, piInstanceTitleFunction, pinOperatingModeDisplayFunction } from 'src/app/common/display-functions';
import { LoadingModalService } from 'src/app/components/services/loading-modal/loading-modal.service';
import { ToastService } from 'src/app/components/services/toast/toast.service';
import { PiInstance } from 'src/app/model/PiInstance';
import { PiInstancePin } from 'src/app/model/PiInstancePin';
import { PinOperatingMode } from 'src/app/model/PinOperatingMode';
import { DataService } from 'src/app/services/data-service/data-service.service';

@Component({
  selector: 'app-pins',
  templateUrl: './pins.component.html',
  styleUrls: ['./pins.component.css']
})
export class PinsComponent implements AfterViewInit {

  piInstances : PiInstance[] = [];
  selectedPiInstnace : PiInstance;
  selectedPiInstancePins : PiInstancePin[] = [];
  selectedPin : PiInstancePin;

  pinOperatingModes : PinOperatingMode[] = [];
  pinOperatingModeDisplayFunction : Function = pinOperatingModeDisplayFunction;
  pinOperatingModeTitleFunction : Function = pinOperatingModeDisplayFunction;
  pinOperatingModeValidationFunction : Function = (pOm : PinOperatingMode) => (pOm && pOm.id) ? "" : "Operating mode not selected";

  piInstanceDropdownListTextboxDisplayFunction : Function = (item : PiInstance) => item ? item.name : "";
  piInstanceTitleFunction : Function = piInstanceTitleFunction;
  piInstanceDescriptionFunction : Function = piInstanceDescriptionFunction;
  piInstanceIconFunction : Function = piInstanceIconFunction;

  pinDelayMsValidationFunction = (delayMS) => {
      if (delayMS) {
        if (isNaN(delayMS)) {
          return "The Delay(ms) must be a number";
        }
        if (delayMS <= 25) {
          return "The Delay (ms) must be greater than 25";
        }
      }
  }

  pinStatesCountValidationFunction = (statesCount) => {
    const valMsg : string = "The states count must NULL or a number greater than 1";

    if (statesCount) {
      if (isNaN(statesCount)) {
        return valMsg;
      }
      if (statesCount <= 1) {
        return valMsg;
      }
    }
  }

  pinsTableColumnTitles = ["(B/G)", "Name", "Op. mode", "Delay(ms)", "#States"];
  pinsTableDisplayFunctions = [
    (item : PiInstancePin) => item ? item.boardId + "/" + item.gpioId : "",
    (item : PiInstancePin) => item ? item.name : "",
    (item : PiInstancePin) => item && item.operatingMode ? item.operatingMode.name : "",
    (item : PiInstancePin) => item ? item.delayMs : "",
    (item : PiInstancePin) => item && item.statesCount ? item.statesCount : "N/A",
  ];
  pinsTableDataGridRowNumberFunction : Function = (pin:PiInstancePin, rowIndex:number) => pin ? pin.id : (rowIndex+1);

  constructor(
    private dataService : DataService,
    private loadingModalService : LoadingModalService,
    private toastService : ToastService
  ) { 
    dataService.piInstancesRepository.errorEventEmitter.subscribe((err) => {
      this.toastService.showError(err.error, err.message);
      this.loadingModalService.hide();
    });

    dataService.piInstancePinsRepository.errorEventEmitter.subscribe((err) => {
      this.toastService.showError(err.error, err.message);
      this.loadingModalService.hide();
    });
  }

  ngAfterViewInit() {
    this.loadingModalService.title = "LOADING";
    this.loadingModalService.subtitle = "Loading pin operating modes";
    this.loadingModalService.show();

    this.dataService.pinOperatingModesRepository.getCustomOperation("findAll")
      .subscribe((ret) => {
        this.pinOperatingModes = ret;

        if (this.pinOperatingModes && this.pinOperatingModes.length > 0) {
          this.loadingModalService.title = "LOADING";
          this.loadingModalService.subtitle = "Loading registered PI instances";
          this.loadingModalService.show();

          this.dataService.piInstancesRepository.getCustomOperation("findAll")
                  .subscribe((ret) => {
                    this.piInstances = ret;

                    if (this.piInstances) {
                      if (this.piInstances.length > 0) {
                        this.selectedPiInstnace = this.piInstances[0];
                        this.loadSelectedPiInstancePins();
                      }
                    } else {
                      this.piInstances = [];
                      this.loadingModalService.hide();
                    }
                  });
        } else {
          this.toastService.showError("Corrupted registry", "There are no pin operating modes defined in the registry");
        }
      });
  }

  loadSelectedPiInstancePins() {
    this.selectedPin = undefined;

    this.loadingModalService.title = "LOADING";
    this.loadingModalService.subtitle = "Loading selected instance pins";
    this.loadingModalService.show();

    this.dataService.piInstancePinsRepository
      .getCustomOperation("findAllByPiInstanceId", new Map([["piInstanceId", this.selectedPiInstnace.id.toString()]]))
        .subscribe((ret) => {
          this.selectedPiInstancePins = ret;
          if (this.selectedPiInstancePins) {
            this.selectedPiInstancePins = this.selectedPiInstancePins.sort((p1,p2) => {
              if (p1 && p2) {
                return p1.id - p2.id;
              } else {
                if (p1) {
                  return 1;
                } else {
                  return -1;
                }
              }
            });
          } else {
            this.selectedPiInstancePins = [];
          }

          this.loadingModalService.hide();
        });
  }

  onSelectedPiInstanceChanged(selectedPiInstance : PiInstance) {
    this.selectedPiInstnace = selectedPiInstance;
    this.loadSelectedPiInstancePins();
  }

  savePiInstancePin : Function = (pin:PiInstancePin) => {
    this.loadingModalService.title = "SAVING";
    this.loadingModalService.subtitle = "Saving pin information";
    this.loadingModalService.show();

    this.dataService.piInstancePinsRepository.saveCustomOperation("save", pin)
          .subscribe((ret) => {
            this.loadSelectedPiInstancePins();
          });
  }
}
