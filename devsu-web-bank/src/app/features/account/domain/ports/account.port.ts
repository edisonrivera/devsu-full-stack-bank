import {HttpResourceRef} from '@angular/common/http';
import {Signal} from '@angular/core';
import {AccountFilterRequest} from '@features/account/domain/models/api/request/account-filter.request';
import {AccountFilterResponse} from '@features/account/domain/models/api/response/account-filter.response';
import {Observable} from 'rxjs';

export abstract class AccountPort {
  abstract getAccounts(request: Signal<AccountFilterRequest | undefined>): HttpResourceRef<AccountFilterResponse>;

  abstract createAccount(request: AccountCreateRequest): Observable<void>;

  abstract deleteAccount(accountId: string): Observable<void>;
}
