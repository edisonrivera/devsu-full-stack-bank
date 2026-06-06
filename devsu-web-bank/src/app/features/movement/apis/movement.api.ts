import {inject, Service, Signal} from '@angular/core';
import {environment} from '@env/environment';
import {HttpClient, httpResource, HttpResourceRef} from '@angular/common/http';
import {MovementFilterRequest} from '@features/movement/domain/models/api/request/movement-filter.request';
import {MovementFilterResponse} from '@features/movement/domain/models/api/response/movement-filter.response';
import {MovementPort} from '@features/movement/domain/ports/movement.port';
import {MovementCreateRequest} from '@features/movement/domain/models/api/request/movement-create.request';
import {Observable} from 'rxjs';

@Service()
export class MovementApi implements MovementPort {
  private readonly http = inject(HttpClient);
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

  createMovement(request: MovementCreateRequest): Observable<void> {
    return this.http.post<void>(this.baseUrl, request);
  }
}
