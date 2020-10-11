import { Component, OnInit, AfterViewInit, Input, Output, EventEmitter } from '@angular/core';
import { piInstanceDescriptionFunction, piInstanceIconFunction, piInstanceTitleFunction, pinDescriptionDisplayFunction, pinGroupDescriptionFunction, pinGroupIconFunction, pinGroupPinDescriptionFunction, pinGroupPinTitleFunction, pinGroupTitleFunction, pinIconFunction, pinTitleDisplayFunction } from 'src/app/common/display-functions';
import { CustomButtonSpecification } from 'src/app/components/crud-table/crud-table.component';
import { LoadingModalService } from 'src/app/components/services/loading-modal/loading-modal.service';
import { ToastService } from 'src/app/components/services/toast/toast.service';
import { PiInstance } from 'src/app/model/PiInstance';
import { PiInstancePin } from 'src/app/model/PiInstancePin';
import { PinGroup } from 'src/app/model/PinGroup';
import { PinGroupPin } from 'src/app/model/PinGroupPin';
import { PinGroupType } from 'src/app/model/PinGroupType';
import { DataService } from 'src/app/services/data-service/data-service.service';
import { nvl } from 'src/app/utils/lang';

@Component({
  selector: 'app-pin-groups',
  templateUrl: './pin-groups.component.html',
  styleUrls: ['./pin-groups.component.css']
})
export class PinGroupsComponent implements OnInit, AfterViewInit {

  piInstances : PiInstance[] = [];
  selectedPiInstance : PiInstance;

  piInstanceTitleFunction : Function = piInstanceTitleFunction;
  piInstanceDescriptionFunction : Function = piInstanceDescriptionFunction;
  piInstanceIconFunction : Function = piInstanceIconFunction;
  piInstanceDisplayFunction : Function = (item:PiInstance) => item ? item.name : "";

  pinGroupTypes : PinGroupType[] = [];

  pinGroupTypesDisplayFunction : Function = (item:PinGroupType) => item ? item.name : "";
  pinGroupTypesTitleFunction : Function = (item:PinGroupType) => item ? item.name : "";
  pinGroupTypesValidationFunction : Function = (item:PinGroupType) => (item && item.id) ? "" : "Pin group type not slected";
  
  pinGroups : PinGroup[] = [];
  selectedPinGroup : PinGroup;

  pinGroupNameValidationFunction : Function = (str:string) => str && str.length >= 3 ? "" : "Name must be at least 3 characters long";

  pinGroupTitleFunction : Function = pinGroupTitleFunction;
  pinGroupDescriptionFunction : Function = pinGroupDescriptionFunction;
  pinGroupIconFunction : Function = pinGroupIconFunction;

  newPinGroupFunction : Function = () => new PinGroup(null, "New pin group", null);
  
  selectedGroupPins : PinGroupPin[] = [];
  selectedPin : PinGroupPin;

  pinGroupPinTitleFunction : Function = pinGroupPinTitleFunction;
  pinGroupPinDescriptionFunction : Function = pinGroupPinDescriptionFunction;

  pinTitleFunction : Function = pinTitleDisplayFunction;
  pinDescriptionFunction : Function = pinDescriptionDisplayFunction;
  pinIconFunction : Function = pinIconFunction;

  selectedPiInstancePins : PiInstancePin[] = [];
  pinChosenToBeAddedToGroup : PiInstancePin;

  // Custom buttons for the GROUP / PIN map
  pinGroupPinsCustomButtons : CustomButtonSpecification[] = [
    // Move UP
    new CustomButtonSpecification("Move up", (item:PinGroupPin) => {
      this.dataService.pinGroupPinsRepository.getCustomOperationWithLoadingModal(
        "moveUp", new Map([["pinGroupPinId", item.id.toString()]]),
        () => { this.loadSelectedGroupPins(); }
      );
    }, 60),
    // Move DOWN
    new CustomButtonSpecification("Move down", (item:PinGroupPin) => {
      this.dataService.pinGroupPinsRepository.getCustomOperationWithLoadingModal(
        "moveDown", new Map([["pinGroupPinId", item.id.toString()]]),
        () => { this.loadSelectedGroupPins(); }
      );
    }, 80),
  ]

  constructor(
    private dataService : DataService,
    private loadingModalService : LoadingModalService,
    private toastService : ToastService
  ) { }

  ngOnInit() {
  }

  ngAfterViewInit(): void {
    this.loadPinGroupTypes().subscribe(() => { 
      this.loadPinGroups().subscribe(() => { 
        this.loadPiInstances().subscribe(() => {
          this.loadSelectedPiInstancePins();
        });
      });
    });
  }

  savePinGroup : Function = (pinGroup:PinGroup) => {
    return this.dataService.pinGroupsRepository.save(pinGroup, (ret) => { this.loadPinGroups(); });
  }

  deleteSelectedPinGroup : Function = () => {
    return this.dataService.pinGroupsRepository.delete(
      this.selectedPinGroup.id,
      () => { this.loadPinGroups(); }
    );
  }

  private loadPinGroupTypes() : EventEmitter<any> {
    return this.dataService.pinGroupTypesRepository.findAll((ret) => { this.pinGroupTypes = nvl(ret, []); });
  }

  private loadPinGroups() : EventEmitter<any> {
    return this.dataService.pinGroupsRepository.findAll((ret) => { this.pinGroups = nvl(ret, []); });
  }

  private loadSelectedGroupPins() {
    return this.dataService.pinGroupPinsRepository.findAllByParentId(
      this.selectedPinGroup.id,
      (ret) => {
        this.selectedGroupPins = nvl(ret, []); 
        this.selectedGroupPins = this.selectedGroupPins.sort((p1,p2) => p1.order > p2.order ? 1 : -1);
        this.selectedPin = undefined;
      }
    );
  }

  loadPiInstances() : EventEmitter<any> {
    return this.dataService.piInstancesRepository.findAll((ret) => {
      this.piInstances = nvl(ret, []); 
      if (this.piInstances.length > 0) {
        this.selectedPiInstance = this.piInstances[0];
      }
    });
  }

  loadSelectedPiInstancePins() : EventEmitter<any> {
    return this.dataService.piInstancePinsRepository.findAllByParentId(
      this.selectedPiInstance.id,
      (ret) => { this.selectedPiInstancePins = nvl(ret, []); }
    );
  }

  addSelectedPinToGroup() {
    return this.dataService.pinGroupPinsRepository.getCustomOperationWithLoadingModal(
        "mapPinOnGroup",
        new Map([
          ["pinId"  , this.pinChosenToBeAddedToGroup.id.toString()],
          ["groupId", this.selectedPinGroup.id.toString()]
        ]),
        (ret) => { this.pinChosenToBeAddedToGroup = undefined; }
    )
  }

  removeSelectedPinFromGroup() {
    return this.dataService.pinGroupPinsRepository.delete(
      this.selectedPin.id,
      () => { this.loadSelectedGroupPins(); }
    );
  }

  onSelectedPinGroupChanged() {
    this.loadSelectedGroupPins();
  }

  onSelectedPiInstanceChanged() {
    this.loadSelectedPiInstancePins();
  }

  onPinSelectionDialogSave = () => {
    this.addSelectedPinToGroup().subscribe((ret) => { this.loadSelectedGroupPins(); });
  }

  onPinGroupPinDelete = () => {
    this.removeSelectedPinFromGroup();
  }

  canSavePinGroupPin : Function = () => {
    if (this.pinChosenToBeAddedToGroup) {
      return true;
    }
    return false;
  }
}
