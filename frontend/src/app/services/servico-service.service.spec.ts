import { TestBed } from '@angular/core/testing';

import { ServicoServiceService } from './servico-service.service';

describe('ServicoServiceService', () => {
  let service: ServicoServiceService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(ServicoServiceService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
