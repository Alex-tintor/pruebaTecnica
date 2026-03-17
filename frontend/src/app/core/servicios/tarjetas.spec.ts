import { TestBed } from '@angular/core/testing';

import { TarjetasService } from './tarjetasService';

describe('Tarjetas', () => {
  let service: TarjetasService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(TarjetasService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
