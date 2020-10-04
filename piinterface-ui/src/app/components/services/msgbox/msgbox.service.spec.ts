import { TestBed } from '@angular/core/testing';

import { MsgboxService } from './msgbox.service';

describe('MsgboxService', () => {
  beforeEach(() => TestBed.configureTestingModule({}));

  it('should be created', () => {
    const service: MsgboxService = TestBed.get(MsgboxService);
    expect(service).toBeTruthy();
  });
});
