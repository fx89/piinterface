import { Component, Input, OnInit, Output, EventEmitter } from '@angular/core';
import { v4 as uuid } from 'uuid';

@Component({
  selector: 'data-table',
  templateUrl: './data-table.component.html',
  styleUrls: ['./data-table.component.css']
})
export class DataTableComponent implements OnInit {

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
  isDataGrid : boolean = true;

  @Input()
  showDataGridRowNumbers : boolean = false;

  constructor() { }

  ngOnInit() {
  }

  getDataItemValue(item:any, rowIndex:number, colIndex:number) : string {
    const dFunc : Function = this.displayFunctions[colIndex];

    if (dFunc) {
      return dFunc(item, rowIndex, colIndex);
    }

    return "!!! Missing display function !!!";
  }

  getTableId() : string {
    return this.id + "_table";
  }

  onTableRecordClicked(item:any, rowIndex:number, colIndex:number) {
    this.selectedItem = item;
  }
}
