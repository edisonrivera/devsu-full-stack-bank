import {Component, computed, inject, signal} from '@angular/core';
import {MovementFilterRequest} from '@features/movement/domain/models/api/request/movement-filter.request';
import {ErrorResponse} from '@shared/apis/common/response/error.response';
import {MovementTable} from '@features/movement/infraestructure/movement-table/movement-table';
import {MovementPort} from '@features/movement/domain/ports/movement.port';
import {toObservable, toSignal} from '@angular/core/rxjs-interop';
import {debounceTime, distinctUntilChanged, map} from 'rxjs';
import {IdentificationConstants} from '@shared/constants/identification-constants';
import {PageConstants} from '@shared/constants/page-constants';

@Component({
  selector: 'app-movement',
  imports: [MovementTable],
  templateUrl: './movement.html',
  styleUrl: './movement.css',
})
export class Movement {
  readonly #movementPort = inject(MovementPort);

  protected readonly CONFIG = IdentificationConstants;
  protected readonly CONFIG_PAGE = PageConstants;

  readonly searchQuery     = signal('');
  readonly currentPage     = signal(0);
  readonly validationError = computed(() => {
    const q = this.searchQuery().trim();
    if (!q) return null;
    return this.CONFIG.PATTERN.test(q) ? null : 'Identificación inválida (5–13 caracteres alfanuméricos)';
  });

  readonly #debouncedQuery = toSignal(
    toObservable(this.searchQuery).pipe(
      debounceTime(500),
      distinctUntilChanged(),
      map(q => q.trim()),
      map(q => (!q || this.CONFIG.PATTERN.test(q) ? q : null)),
    ),
    { initialValue: '' }
  );

  readonly #filter = computed<MovementFilterRequest | undefined>(() => {
    const query = this.#debouncedQuery();
    if (query === null) return undefined;
    return {
      page:     this.currentPage(),
      pageSize: this.CONFIG_PAGE.PAGE_SIZE_DEFAULT,
      ...(query ? { identification: query } : {}),
    };
  });

  readonly #resource        = this.#movementPort.getMovements(this.#filter);
  protected readonly movements      = computed(() => this.#resource.hasValue() ? (this.#resource.value()?.movements      ?? []) : []);
  protected readonly totalMovements = computed(() => this.#resource.hasValue() ? (this.#resource.value()?.totalMovements ?? 0)  : 0);
  protected readonly loading        = computed(() => this.#resource.isLoading());
  protected readonly error          = computed(() => {
    const raw = this.#resource.error();
    if (!raw) return null;
    const body = (raw as any)?.error ?? raw;
    return (body as ErrorResponse)?.message ?? 'Error inesperado';
  });

  onSearch(query: string): void {
    this.currentPage.set(0);
    this.searchQuery.set(query);
  }

  onPageChange(page: number): void {
    this.currentPage.set(page);
  }
}
