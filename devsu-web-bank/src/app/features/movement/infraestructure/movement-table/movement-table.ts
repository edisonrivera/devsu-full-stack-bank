import {Component, input, output} from '@angular/core';
import {Pagination} from '@shared/components/pagination';
import {CurrencyPipe, DatePipe} from '@angular/common';
import {MovementInfoResponse} from '@features/movement/domain/models/api/response/movement-info.response';

@Component({
  selector: 'app-movement-table',
  imports: [Pagination, DatePipe, CurrencyPipe],
  templateUrl: './movement-table.html'
})
export class MovementTable {
  readonly movements = input.required<MovementInfoResponse[]>();
  readonly totalMovements = input.required<number>();
  readonly loading = input<boolean>(false);
  readonly currentPage = input<number>(0);
  readonly pageSize = input<number>(10);

  readonly pageChange = output<number>();
}
