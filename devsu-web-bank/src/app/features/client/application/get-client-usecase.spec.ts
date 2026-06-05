import { TestBed } from '@angular/core/testing';

import { GetClientUsecase } from './get-client-usecase';

describe('GetClientUsecase', () => {
  let service: GetClientUsecase;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(GetClientUsecase);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
