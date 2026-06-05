import {Component, input, output} from '@angular/core';
import {Pagination} from '@shared/components/pagination';
import {AccountInfoResponse} from '@features/account/domain/models/api/response/account-info.response';

@Component({
  selector: 'app-account-table',
  imports: [Pagination],
  templateUrl: './account-table.html'
})
export class AccountTable {
  readonly accounts = input.required<AccountInfoResponse[]>();
  readonly totalAccounts = input.required<number>();
  readonly loading = input<boolean>(false);
  readonly currentPage = input<number>(0);
  readonly pageSize = input<number>(10);

  readonly pageChange = output<number>();
}
