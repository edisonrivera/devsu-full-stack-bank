import {Component, computed, inject, signal} from '@angular/core';
import {ErrorResponse} from '@shared/apis/common/response/error.response';
import {FormsModule} from '@angular/forms';
import {ReportTable} from '@features/report/infraestructure/report-table/report-table';
import {ReportPort} from '@features/report/domain/ports/report.port';
import {ReportFilterRequest} from '@features/report/domain/models/api/request/report-filter.request';

const PAGE_SIZE = 10;

@Component({
  selector: 'app-report',
  imports: [
    FormsModule,
    ReportTable
  ],
  templateUrl: './report.html',
  styleUrl: './report.css',
})
export class Report {
  readonly #reportPort = inject(ReportPort);

  readonly dateFrom = signal('');
  readonly dateTo = signal('');
  readonly currentPage = signal(0);

  readonly #filter = computed<ReportFilterRequest | undefined>(() => {
    const from = this.dateFrom();
    const to = this.dateTo();
    if (!from || !to) return undefined;

    const fmt = (iso: string) => {
      const [y, m, d] = iso.split('-');
      return `${d}/${m}/${y}`;
    };

    return {
      fecha: `${fmt(from)}-${fmt(to)}`,
      pageNo: this.currentPage(),
      pageSize: PAGE_SIZE,
    };
  });

  readonly #resource = this.#reportPort.getReports(this.#filter);
  protected readonly movements = computed(() => this.#resource.hasValue() ? (this.#resource.value()?.movementsReport ?? []) : []);
  protected readonly totalMovements = computed(() => this.#resource.hasValue() ? (this.#resource.value()?.totalMovementsReport ?? 0) : 0);
  protected readonly loading = computed(() => this.#resource.isLoading());
  protected readonly error = computed(() => {
    const raw = this.#resource.error();
    if (!raw) return null;
    const body = (raw as any)?.error ?? raw;
    return (body as ErrorResponse)?.message ?? 'Error inesperado';
  });

  protected onDateChange(field: 'from' | 'to', value: string) {
    this.currentPage.set(0);
    field === 'from' ? this.dateFrom.set(value) : this.dateTo.set(value);
  }

  protected onClear() {
    this.dateFrom.set('');
    this.dateTo.set('');
    this.currentPage.set(0);
  }

  protected onPageChange(page: number) {
    this.currentPage.set(page);
  }

  protected onDownloadPdf() {
    const from = this.dateFrom();
    const to = this.dateTo();
    if (!from || !to) return;

    const fmt = (iso: string) => {
      const [y, m, d] = iso.split('-');
      return `${d}/${m}/${y}`;
    };
    const date = `${fmt(from)}-${fmt(to)}`;

    this.#reportPort.downloadPdf(date);
  }
}
