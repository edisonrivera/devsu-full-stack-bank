import {Component, computed, inject, signal} from '@angular/core';
import {ClientTable} from '@features/client/infraestructure/client-table/client-table';
import {ClientPort} from '@features/client/domain/ports/client.port';
import {ClientFilterRequest} from '@features/client/domain/models/api/request/client-filter.request';
import {ErrorResponse} from '@shared/apis/common/response/error.response';
import {toObservable, toSignal} from '@angular/core/rxjs-interop';
import {debounceTime, distinctUntilChanged, map} from 'rxjs';
import {IdentificationConstants} from '@shared/constants/identification-constants';
import {PageConstants} from '@shared/constants/page-constants';
import {ClientCreate} from '@features/client/infraestructure/client-create/client-create';

@Component({
  selector: 'app-client',
  imports: [ClientTable, ClientCreate],
  templateUrl: './client.html',
})
export class Client {
  readonly #clientPort = inject(ClientPort);

  protected readonly CONFIG = IdentificationConstants;
  protected readonly CONFIG_PAGE = PageConstants;

  readonly showCreate = signal(false);
  readonly searchQuery = signal('');
  readonly currentPage = signal(0);
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
    {initialValue: ''}
  );

  readonly #filter = computed<ClientFilterRequest | undefined>(() => {
    const query = this.#debouncedQuery();

    if (query === null) return undefined;

    return {
      page: this.currentPage(),
      pageSize: this.CONFIG_PAGE.PAGE_SIZE_DEFAULT,
      ...(query ? {identification: query} : {}),
    };
  });

  readonly #resource = this.#clientPort.getClients(this.#filter);

  protected readonly clients = computed(() => this.#resource.hasValue() ? (this.#resource.value()?.clients ?? []) : []);
  protected readonly totalClients = computed(() => this.#resource.hasValue() ? (this.#resource.value()?.totalClients ?? 0) : 0);
  protected readonly loading = computed(() => this.#resource.isLoading());

  protected readonly error = computed(() => {
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

  onCreated(): void {
    this.showCreate.set(false);
    this.#resource.reload();
    this.searchQuery.set('');
  }
}
