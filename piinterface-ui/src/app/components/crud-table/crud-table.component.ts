import { Component, OnInit, Input, Output, EventEmitter, AfterViewInit } from '@angular/core';
import { v4 as uuid } from 'uuid';
import { ValidationService } from '../services/validation/validation.service';

@Component({
  selector: 'crud-table',
  templateUrl: './crud-table.component.html',
  styleUrls: ['./crud-table.component.css']
})
export class CrudTableComponent implements OnInit, AfterViewInit {

  @Input()
  id : string = "_" + uuid();

  @Input()
  data : any[] = [];

  @Input()
  columnTitles : string[] = ["Column 1"];

  @Input()
  displayFunctions : Function[] = [(item:any) => item.toString()];

  @Input()
  dataGridRowNumberFunction :Function = (item:any, rowIndex:number, colIndex:number) => rowIndex + 1;

  @Input()
  selectedItem : any;

  @Output()
  selectedItemChange : EventEmitter<any> = new EventEmitter<any>();

  @Input()
  showHeader : boolean = true;

  @Input()
  showDataGridRowNumbers : boolean = false;

  @Output()
  onSelectionChanged : EventEmitter<any> = new EventEmitter<any>();

  @Input()
  newItemFunction : Function = () => { return { name:"New generic object" }; }

  @Input()
  saveItemFunction : Function = (item:any) => { throw "not implemented"; }

  @Input()
  deleteItemFunction : Function = (item:any) => { throw "not implemented"; }

  @Input()
  showAddButton : boolean = true;

  @Input()
  showEditButton : boolean = true;

  @Input()
  showDelButton : boolean = true;

  @Input()
  dataTableHeightPX : number = 200;

  @Input()
  editDialogTitle : string = "Edit item";

  @Input()
  editDialogWidthPX : number = 300;

  @Input()
  editDialogHeightPX : number = 200;

  @Input()
  showEditDialogValidationMessages : boolean = true;

  @Input()
  delConfirmationMsgboxTitle : string = "Confirm deletion";

  @Input()
  delConfirmationMsgboxMessage : string = "Are you sure you want to delete the selected item?";

  @Input()
  contentType : string = "table"; // Possible values: table, list

  @Input()
  listTitleFunction : Function = (item:any) => item ? item.toString() : "";

  @Input()
  listDescriptionFunction : Function = (item:any) => "";

  @Input()
  listIconFunction : Function = (item:any) => "";

  @Input()
  dialogSaveButtonEnabled : Function = () => true;

  @Input()
  customButtons : CustomButtonSpecification[] = [];

  editDialogShowEvent : EventEmitter<any> = new EventEmitter<any>();

  delConfirmationMsgboxShowEvent : EventEmitter<any> = new EventEmitter<any>();

  containedComponentIds : string[] = [];

  constructor(private validation : ValidationService) { }

  ngOnInit() {
  }

  ngAfterViewInit() {
    this.containedComponentIds = this.validation.collectContainedElementIds(this.id);
  }

  getDataTableId() : string {
    return this.id + "_data_table";
  }

  getContainerId() : string {
    return this.id + "_container";
  }

  onTableSelectionChanged(selectedItem : any) {
    this.selectedItem = selectedItem;
    this.selectedItemChange.emit(selectedItem);
    this.onSelectionChanged.emit(selectedItem);
    this.validation.clearValidationMessages(this.containedComponentIds);
  }

  onDelButtonClicked() {
    this.delConfirmationMsgboxShowEvent.emit();
  }

  onDelConfirmationMsgboxOkClicked : Function = () => {
    this.deleteItemFunction(this.selectedItem);
    this.selectedItem = undefined;
  }

  onDelConfirmationMsgboxCancelClicked : Function = () => {
    this.selectedItem = undefined;
  }

  onEditButtonClicked() {
    this.selectedItemChange.emit(this.selectedItem);
    this.onSelectionChanged.emit(this.selectedItem);
    this.editDialogShowEvent.emit();
  }

  onAddButtonClicked() {
    this.selectedItem = this.newItemFunction();
    this.selectedItemChange.emit(this.selectedItem);
    this.onSelectionChanged.emit(this.selectedItem);
    this.editDialogShowEvent.emit();
  }

  onDialogOkButtonClicked : Function = () => {
    this.saveItemFunction(this.selectedItem);
    this.selectedItem = undefined;
  }

  onDialogCancelButtonClicked : Function = () => {
    this.selectedItem = undefined;
  }
}

export class CustomButtonSpecification {
  constructor(
    public text : string = "Click me",
    public callback : Function = (item:any) => {  },
    public widthPX : number = 40
  ){}
}