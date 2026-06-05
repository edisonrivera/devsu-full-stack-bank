import {HttpResourceRef} from '@angular/common/http';
import {Signal} from '@angular/core';
import {AccountFilterRequest} from '@features/account/domain/models/api/request/account-filter.request';
import {AccountFilterResponse} from '@features/account/domain/models/api/response/account-filter.response';

export abstract class AccountPort {
  abstract getAccounts(request: Signal<AccountFilterRequest | undefined>): HttpResourceRef<AccountFilterResponse>;
}
