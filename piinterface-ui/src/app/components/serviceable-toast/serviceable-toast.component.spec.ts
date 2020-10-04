import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { ServiceableToastComponent } from './serviceable-toast.component';

describe('ServiceableToastComponent', () => {
  let component: ServiceableToastComponent;
  let fixture: ComponentFixture<ServiceableToastComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ ServiceableToastComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(ServiceableToastComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
