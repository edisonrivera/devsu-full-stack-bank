import { Component, computed, input, output } from '@angular/core';

export interface PageItem {
  page: number;
  ellipsisBefore: boolean;
}

@Component({
  selector: 'app-pagination',
  templateUrl: './pagination.html',
  styleUrl: './pagination.css',
})
export class Pagination {
  readonly totalItems  = input.required<number>();
  readonly pageSize    = input<number>(10);
  readonly currentPage = input<number>(0);

  readonly pageChange = output<number>();

  readonly totalPages = computed(() =>
    Math.ceil(this.totalItems() / this.pageSize())
  );

  readonly pageItems = computed<PageItem[]>(() => {
    const total   = this.totalPages();
    const current = this.currentPage();

    const pages: number[] =
      total <= 7
        ? Array.from({ length: total }, (_, i) => i)
        : [...new Set(
          [0, total - 1, current - 1, current, current + 1]
            .filter(p => p >= 0 && p < total)
        )].sort((a, b) => a - b);

    return pages.map((page, i) => ({
      page,
      ellipsisBefore: i > 0 && page -(pages[i - 1] ?? 0) > 1,
    }));
  });

  goTo(page: number): void {
    if (page < 0 || page >= this.totalPages() || page === this.currentPage()) return;
    this.pageChange.emit(page);
  }
}
