import { TestBed } from '@angular/core/testing';

import { RabbitMqService } from './rabbit-mq.service';

describe('RabbitMqService', () => {
  let service: RabbitMqService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(RabbitMqService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
