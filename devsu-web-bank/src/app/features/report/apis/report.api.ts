import {Service, Signal} from '@angular/core';
import {httpResource, HttpResourceRef} from '@angular/common/http';
import {environment} from '@env/environment';
import {ReportPort} from '@features/report/domain/ports/report.port';
import {ReportFilterRequest} from '@features/report/domain/models/api/request/report-filter.request';
import {ReportMovementResponse} from '@features/report/domain/models/api/response/report-movement.response';

@Service()
export class ReportApi implements ReportPort {
  private readonly baseUrl = `${environment.apiUrl}/devsu-api-bank/v1/api/reportes`;

  getReports(request: Signal<ReportFilterRequest | undefined>): HttpResourceRef<ReportMovementResponse> {
    return httpResource<ReportMovementResponse>(() =>
        request()
          ? {
            url: this.baseUrl,
            method: 'GET',
            params: {
              fecha: request()!.fecha,
              pageNo: request()!.pageNo,
              pageSize: request()!.pageSize,
            },
          }
          : undefined,
      {defaultValue: {movementsReport: [], totalMovementsReport: 0}}
    );
  }

  downloadPdf(date: string) {
    const url = `${this.baseUrl}/pdf?fecha=${encodeURIComponent(date)}`;
    const a = document.createElement('a');
    a.href = url;
    a.download = `reporte-${date.replace(/\//g, '-')}.pdf`;
    a.click();
  }
}
