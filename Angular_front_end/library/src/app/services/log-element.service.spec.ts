import { TestBed } from '@angular/core/testing';

import { LogElementService } from './log-element.service';

describe('LogElementService', () => {
  let service: LogElementService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(LogElementService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
