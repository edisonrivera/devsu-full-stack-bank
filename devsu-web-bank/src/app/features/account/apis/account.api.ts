import {inject, Service, Signal} from '@angular/core';
import {environment} from '@env/environment';
import {HttpClient, httpResource, HttpResourceRef} from '@angular/common/http';
import {AccountPort} from '@features/account/domain/ports/account.port';
import {AccountFilterRequest} from '@features/account/domain/models/api/request/account-filter.request';
import {AccountFilterResponse} from '@features/account/domain/models/api/response/account-filter.response';
import {Observable} from 'rxjs';

@Service()
export class AccountApi implements AccountPort {
  private readonly baseUrl = `${environment.apiUrl}/devsu-api-bank/v1/api/accounts`;
  private readonly http = inject(HttpClient);

  getAccounts(request: Signal<AccountFilterRequest | undefined>): HttpResourceRef<AccountFilterResponse> {
    return httpResource<AccountFilterResponse>(() =>
      request() ?
        ({
          url: `${this.baseUrl}/filter`,
          method: 'POST',
          body: request()
        }) : undefined, {defaultValue: {accounts: [], totalAccounts: 0}});
  }

  createAccount(request: AccountCreateRequest): Observable<void> {
    return this.http.post<void>(this.baseUrl, request);
  }

  deleteAccount(accountId: string): Observable<void> {
    return this.http.delete<void>(`${this.baseUrl}/${accountId}`);
  }
}
