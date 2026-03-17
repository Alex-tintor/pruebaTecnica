import { TestBed } from '@angular/core/testing';

import { TransaccionesService } from './transaccionesService';

describe('Transacciones', () => {
  let service: TransaccionesService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(TransaccionesService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
