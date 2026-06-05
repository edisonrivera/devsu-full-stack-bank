import {ClientInfoResponse} from '@features/client/domain/models/api/response/client-info.response';

export interface ClientResponse {
  readonly clients: ClientInfoResponse[];
  readonly totalClients: number;
}
