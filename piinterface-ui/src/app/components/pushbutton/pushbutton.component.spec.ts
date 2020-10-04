import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { PushbuttonComponent } from './pushbutton.component';

describe('PushbuttonComponent', () => {
  let component: PushbuttonComponent;
  let fixture: ComponentFixture<PushbuttonComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ PushbuttonComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(PushbuttonComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
