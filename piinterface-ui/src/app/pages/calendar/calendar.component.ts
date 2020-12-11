import { AfterViewInit, Component, EventEmitter, OnInit } from '@angular/core';
import { Observable } from 'rxjs';
import { MsgboxService } from 'src/app/components/services/msgbox/msgbox.service';
import { ToastService } from 'src/app/components/services/toast/toast.service';
import { PiInstancePin } from 'src/app/model/PiInstancePin';
import { PinCalendarAction } from 'src/app/model/PinCalendarAction';
import { PinCalendarEntry } from 'src/app/model/PinCalendarEntry';
import { DataService } from 'src/app/services/data-service/data-service.service';

@Component({
  selector: 'app-calendar',
  templateUrl: './calendar.component.html',
  styleUrls: ['./calendar.component.css']
})
export class CalendarComponent implements OnInit, AfterViewInit {

  entries : PinCalendarEntry[] = [];
  selectedEntry : PinCalendarEntry;

  actions : PinCalendarAction[] = [];

  pins : PiInstancePin[] = [];

  columnTitles : string[] = [
    "Title",
    "Pin",
    "YYYY",
    "MM",
    "DD",
    "HH",
    "MI",
    "Act"
  ];

  displayFunctions : Function[] = [
    (item : PinCalendarEntry) => item.title,
    (item : PinCalendarEntry) => item.pin.piInstance.name + "/" + item.pin.name + " (" + item.pin.boardId + ")",
    (item : PinCalendarEntry) => this.timeUnitDisplayFunction(item.year, true),
    (item : PinCalendarEntry) => this.timeUnitDisplayFunction(item.month, true),
    (item : PinCalendarEntry) => this.timeUnitDisplayFunction(item.day, true),
    (item : PinCalendarEntry) => this.timeUnitDisplayFunction(item.hour, true),
    (item : PinCalendarEntry) => this.timeUnitDisplayFunction(item.minute, true),
    (item : PinCalendarEntry) => item.action.name
  ];

  rowNumberFunction : Function = (item:PinCalendarEntry, rowIndex:number) => rowIndex;

  pinValidationFunction : Function = (pin : PiInstancePin) => (this.selectedEntry?.pin?.id) ? "" : "Pin not selected";
  actionValidationFunction : Function = (action : PinCalendarAction) => (this.selectedEntry?.action?.id) ? "" : "Action not selected";
  titleValidationFunction : Function = (title : string) => this.selectedEntry?.title?.length >= 3 ? "" : "Title must be at least 3 characters long";

  pinDisplayFunction : Function = (pin : PiInstancePin) => pin?.piInstance?.name + " / " + pin?.name;
  actionDisplayFunction : Function = (action : PinCalendarAction) => action ? action.name : "Not selected";

  years : number[] = [-1, 2020, 2021, 2022, 2023, 2024, 2025, 2026, 2027, 2028, 2029, 2030];
  months : number[] = [-1, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12];
  days : number[] = [-1, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20, 21, 22, 23, 24, 25, 26, 27, 28, 29, 30, 31];
  hours : number[] = [-1, 0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20, 21, 22, 23];
  minutes : number[] = [0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 16, 18, 19, 20, 21, 22, 23, 24, 25, 26, 27, 28, 29, 30, 31, 32, 33, 34, 35, 36, 37, 38, 39, 40, 41, 42, 43, 44, 45, 46, 47, 48, 49, 50, 51, 52, 53, 54, 55, 56, 57, 58, 59];

  timeUnitDisplayFunction : Function = (year, partialWord:boolean) => year == -1 ? (partialWord ? "N/A" : "Irrelevant") : year;

  constructor(private dataService : DataService) { }

  ngOnInit(): void {
  }

  ngAfterViewInit() {
    this.loadPinCalendarActions().subscribe(() => {
      this.loadPins().subscribe(() => {
        this.loadPinCalendarEntries();
      });
    });
  }

  private loadPinCalendarActions() : EventEmitter<any> {
    return this.dataService.PinCalendarActionsRepository.findAll((ret) => {
      this.actions = ret;
    });
  }

  private loadPins() : EventEmitter<any> {
    return this.dataService.piInstancePinsRepository.findAll((ret) => {
      this.pins = [];
      for (let pin of ret) {
        if (pin.isAvailable) {
          this.pins.push(pin);
        }
      }
    });
  }

  private loadPinCalendarEntries() : EventEmitter<any> {
    return this.dataService.PinCalendarEntriesRepository.findAll((ret) => {
      this.entries = ret;
      this.selectedEntry = undefined;
    });
  }

  newCalendarEntry : Function = () => PinCalendarEntry.newEntry();

  saveCalendarEntry : Function = (item : PinCalendarEntry) => {
    this.dataService.PinCalendarEntriesRepository.save(item, () => {
      this.loadPinCalendarEntries();
    });
  }

  deleteCalendarEntry : Function = (item : PinCalendarEntry) => {
    this.dataService.PinCalendarEntriesRepository.delete(item.id, () => {
      this.loadPinCalendarEntries();
    });
  }

  onCalendarSelectionChanged(item : PinCalendarEntry) {
    this.selectedEntry = item;
  }
}
