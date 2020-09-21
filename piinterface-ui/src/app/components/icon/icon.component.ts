import { Component, Input, OnInit } from '@angular/core';

@Component({
  selector: 'icon',
  templateUrl: './icon.component.html',
  styleUrls: ['./icon.component.css']
})
export class IconComponent implements OnInit {
  @Input()
  picture : string

  @Input()
  text : string = "icon text"

  @Input()
  href : string = "www.altavista.com"

  constructor() { }

  ngOnInit() {
  }

}
