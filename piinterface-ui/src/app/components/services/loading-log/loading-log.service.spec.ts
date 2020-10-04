import { TestBed } from '@angular/core/testing';

import { LoadingLogService } from './loading-log.service';

describe('LoadingLogService', () => {
  beforeEach(() => TestBed.configureTestingModule({}));

  it('should be created', () => {
    const service: LoadingLogService = TestBed.get(LoadingLogService);
    expect(service).toBeTruthy();
  });
});
