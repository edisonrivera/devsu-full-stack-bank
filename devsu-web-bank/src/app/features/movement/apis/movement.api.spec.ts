import { TestBed } from '@angular/core/testing';

import { MovementApi } from './movement.api';

describe('AccountApi', () => {
  let service: MovementApi;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(MovementApi);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
