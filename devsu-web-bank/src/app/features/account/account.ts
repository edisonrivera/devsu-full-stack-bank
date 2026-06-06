import {Component, computed, inject, signal} from '@angular/core';
import {ErrorResponse} from '@shared/apis/common/response/error.response';
import {AccountPort} from '@features/account/domain/ports/account.port';
import {AccountFilterRequest} from '@features/account/domain/models/api/request/account-filter.request';
import {AccountTable} from '@features/account/infraestructure/account-table/account-table';
import {toObservable, toSignal} from '@angular/core/rxjs-interop';
import {debounceTime, distinctUntilChanged, map} from 'rxjs';
import {IdentificationConstants} from '@shared/constants/identification-constants';
import {PageConstants} from '@shared/constants/page-constants';
import {AccountCreate} from '@features/account/infraestructure/account-create/account-create';

@Component({
  selector: 'app-account',
  imports: [AccountTable, AccountCreate],
  templateUrl: './account.html',
})
export class Account {
  readonly #accountPort = inject(AccountPort);

  protected readonly CONFIG = IdentificationConstants;
  protected readonly CONFIG_PAGE = PageConstants;

  readonly deleteError = signal<string | null>(null);
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

  readonly #filter = computed<AccountFilterRequest | undefined>(() => {
    const query = this.#debouncedQuery();
    if (query === null) return undefined;
    return {
      page: this.currentPage(),
      pageSize: this.CONFIG_PAGE.PAGE_SIZE_DEFAULT,
      ...(query ? {identification: query} : {}),
    };
  });

  readonly #resource = this.#accountPort.getAccounts(this.#filter);
  protected readonly accounts = computed(() => this.#resource.hasValue() ? (this.#resource.value()?.accounts ?? []) : []);
  protected readonly totalAccounts = computed(() => this.#resource.hasValue() ? (this.#resource.value()?.totalAccounts ?? 0) : 0);
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

  deleteAccount(accountId: string): void {
    this.deleteError.set(null);
    this.#accountPort.deleteAccount(accountId).subscribe({
      next: () => this.#resource.reload(),
      error: (err: any) => {
        const body = err?.error ?? err;
        this.deleteError.set((body as ErrorResponse)?.message ?? 'Error al eliminar la cuenta');
      },
    });
  }
}
