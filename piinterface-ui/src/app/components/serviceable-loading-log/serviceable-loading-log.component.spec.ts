import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { ServiceableLoadingLogComponent } from './serviceable-loading-log.component';

describe('ServiceableLoadingLogComponent', () => {
  let component: ServiceableLoadingLogComponent;
  let fixture: ComponentFixture<ServiceableLoadingLogComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ ServiceableLoadingLogComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(ServiceableLoadingLogComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
