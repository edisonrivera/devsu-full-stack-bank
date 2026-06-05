import {HttpResourceRef} from '@angular/common/http';
import {Signal} from '@angular/core';
import {ReportMovementResponse} from '@features/report/domain/models/api/response/report-movement.response';
import {ReportFilterRequest} from '@features/report/domain/models/api/request/report-filter.request';

export abstract class ReportPort {
  abstract getReports(request: Signal<ReportFilterRequest | undefined>): HttpResourceRef<ReportMovementResponse>;
  abstract downloadPdf(date: string): void;
}
