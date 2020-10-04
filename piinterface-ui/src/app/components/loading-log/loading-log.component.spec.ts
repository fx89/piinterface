import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { LoadingLogComponent } from './loading-log.component';

describe('LoadingLogComponent', () => {
  let component: LoadingLogComponent;
  let fixture: ComponentFixture<LoadingLogComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ LoadingLogComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(LoadingLogComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
