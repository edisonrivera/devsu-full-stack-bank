import {HttpResourceRef} from '@angular/common/http';
import {Signal} from '@angular/core';
import {MovementFilterRequest} from '@features/movement/domain/models/api/request/movement-filter.request';
import {MovementFilterResponse} from '@features/movement/domain/models/api/response/movement-filter.response';

export abstract class MovementPort {
  abstract getMovements(request: Signal<MovementFilterRequest | undefined>): HttpResourceRef<MovementFilterResponse>;
}
