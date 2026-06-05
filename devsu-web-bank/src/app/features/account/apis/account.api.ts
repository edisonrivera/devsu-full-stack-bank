import {Service, Signal} from '@angular/core';
import {environment} from '@env/environment';
import {httpResource, HttpResourceRef} from '@angular/common/http';
import {AccountPort} from '@features/account/domain/ports/account.port';
import {AccountFilterRequest} from '@features/account/domain/models/api/request/account-filter.request';
import {AccountFilterResponse} from '@features/account/domain/models/api/response/account-filter.response';

@Service()
export class AccountApi implements AccountPort {
  private readonly baseUrl = `${environment.apiUrl}/devsu-api-bank/v1/api/accounts`;

  getAccounts(request: Signal<AccountFilterRequest | undefined>): HttpResourceRef<AccountFilterResponse> {
    return httpResource<AccountFilterResponse>(() =>
      request() ?
        ({
          url: `${this.baseUrl}/filter`,
          method: 'POST',
          body: request()
        }) : undefined, {defaultValue: {accounts: [], totalAccounts: 0}});
  }
}
