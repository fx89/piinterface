import { Component, OnInit } from '@angular/core';
import { LoadingLogService } from '../services/loading-log/loading-log.service';

@Component({
  selector: 'serviceable-loading-log',
  templateUrl: './serviceable-loading-log.component.html',
  styleUrls: ['./serviceable-loading-log.component.css']
})
export class ServiceableLoadingLogComponent implements OnInit {

  constructor(public svc : LoadingLogService) { }

  ngOnInit() {
  }

}
