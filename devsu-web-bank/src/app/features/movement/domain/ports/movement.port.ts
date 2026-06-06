import {HttpResourceRef} from '@angular/common/http';
import {Signal} from '@angular/core';
import {MovementFilterRequest} from '@features/movement/domain/models/api/request/movement-filter.request';
import {MovementFilterResponse} from '@features/movement/domain/models/api/response/movement-filter.response';
import {MovementCreateRequest} from '@features/movement/domain/models/api/request/movement-create.request';
import {Observable} from 'rxjs';

export abstract class MovementPort {
  abstract getMovements(request: Signal<MovementFilterRequest | undefined>): HttpResourceRef<MovementFilterResponse>;

  abstract createMovement(request: MovementCreateRequest): Observable<void>
}
