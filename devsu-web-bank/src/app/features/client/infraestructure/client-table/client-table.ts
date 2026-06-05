import { Component, input, output } from '@angular/core';
import { ClientInfoResponse } from '@features/client/domain/models/api/response/client-info.response';
import {Pagination} from '@shared/components/pagination';

@Component({
  selector: 'app-client-table',
  imports: [Pagination],
  templateUrl: './client-table.html',
  styleUrl: './client-table.css',
})
export class ClientTable {
  readonly clients      = input.required<ClientInfoResponse[]>();
  readonly totalClients = input.required<number>();
  readonly loading      = input<boolean>(false);
  readonly currentPage  = input<number>(0);
  readonly pageSize     = input<number>(10);

  readonly pageChange = output<number>();
}
