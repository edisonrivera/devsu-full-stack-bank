import { TestBed } from '@angular/core/testing';

import { ReportApi } from './report.api';

describe('ClientApi', () => {
  let service: ReportApi;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(ReportApi);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
