import { TestBed } from '@angular/core/testing';

import { LoadingModalService } from './loading-modal.service';

describe('LoadingModalService', () => {
  beforeEach(() => TestBed.configureTestingModule({}));

  it('should be created', () => {
    const service: LoadingModalService = TestBed.get(LoadingModalService);
    expect(service).toBeTruthy();
  });
});
