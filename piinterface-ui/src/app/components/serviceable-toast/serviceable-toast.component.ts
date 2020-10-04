import { Component, OnInit } from '@angular/core';
import { ToastService } from '../services/toast/toast.service';

@Component({
  selector: 'serviceable-toast',
  templateUrl: './serviceable-toast.component.html',
  styleUrls: ['./serviceable-toast.component.css']
})
export class ServiceableToastComponent implements OnInit {

  constructor(public toastService : ToastService) { }

  ngOnInit() {
  }

}
