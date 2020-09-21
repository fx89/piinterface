import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { PinGroupsComponent } from './pin-groups.component';

describe('PinGroupsComponent', () => {
  let component: PinGroupsComponent;
  let fixture: ComponentFixture<PinGroupsComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ PinGroupsComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(PinGroupsComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
