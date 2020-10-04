import { Component, OnInit } from '@angular/core';
import { MsgboxService } from '../services/msgbox/msgbox.service';

@Component({
  selector: 'serviceable-msgbox',
  templateUrl: './serviceable-msgbox.component.html',
  styleUrls: ['./serviceable-msgbox.component.css']
})
export class ServiceableMsgboxComponent implements OnInit {

  constructor(public msgBoxService:MsgboxService) { }

  ngOnInit() {
  }

}
