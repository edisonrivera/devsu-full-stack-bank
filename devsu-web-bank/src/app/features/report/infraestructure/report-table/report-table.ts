import {Component, input, output} from '@angular/core';
import {Pagination} from '@shared/components/pagination';
import {CurrencyPipe, DatePipe} from '@angular/common';
import {ReportMovementInfoResponse} from '@features/report/domain/models/api/response/report-movement-info.response';

@Component({
  selector: 'app-report-table',
  imports: [Pagination, DatePipe, CurrencyPipe],
  templateUrl: './report-table.html',
  styleUrl: './report-table.css'
})
export class ReportTable {
  readonly downloadPdf = output<void>();

  readonly movements = input.required<ReportMovementInfoResponse[]>();
  readonly totalMovements = input.required<number>();
  readonly loading = input<boolean>(false);
  readonly currentPage = input<number>(0);
  readonly pageSize = input<number>(10);

  readonly pageChange = output<number>();
}
