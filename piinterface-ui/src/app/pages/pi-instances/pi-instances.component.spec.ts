import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { PiInstancesComponent } from './pi-instances.component';

describe('PiInstancesComponent', () => {
  let component: PiInstancesComponent;
  let fixture: ComponentFixture<PiInstancesComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ PiInstancesComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(PiInstancesComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
