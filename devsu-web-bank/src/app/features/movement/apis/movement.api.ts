import {Service, Signal} from '@angular/core';
import {environment} from '@env/environment';
import {httpResource, HttpResourceRef} from '@angular/common/http';
import {MovementFilterRequest} from '@features/movement/domain/models/api/request/movement-filter.request';
import {MovementFilterResponse} from '@features/movement/domain/models/api/response/movement-filter.response';
import {MovementPort} from '@features/movement/domain/ports/movement.port';

@Service()
export class MovementApi implements MovementPort {
  private readonly baseUrl = `${environment.apiUrl}/devsu-api-bank/v1/api/movements`;

  getMovements(request: Signal<MovementFilterRequest | undefined>): HttpResourceRef<MovementFilterResponse> {
    return httpResource<MovementFilterResponse>(() =>
      request() ?
        ({
          url: `${this.baseUrl}/filter`,
          method: 'POST',
          body: request()
        }) : undefined, {defaultValue: {movements: [], totalMovements: 0}});
  }
}
