import { TestBed } from '@angular/core/testing';

import { FishingAreaGuardService } from './fishing-area-guard.service';

describe('FishingAreaGuardService', () => {
  let service: FishingAreaGuardService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(FishingAreaGuardService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
