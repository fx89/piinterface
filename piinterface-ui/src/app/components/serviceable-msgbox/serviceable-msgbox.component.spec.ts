import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { ServiceableMsgboxComponent } from './serviceable-msgbox.component';

describe('ServiceableMsgboxComponent', () => {
  let component: ServiceableMsgboxComponent;
  let fixture: ComponentFixture<ServiceableMsgboxComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ ServiceableMsgboxComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(ServiceableMsgboxComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
