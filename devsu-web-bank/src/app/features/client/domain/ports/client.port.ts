import {HttpResourceRef} from '@angular/common/http';
import {ClientResponse} from '@features/client/domain/models/api/response/client.response';
import {ClientFilterRequest} from '@features/client/domain/models/api/request/client-filter.request';
import {Signal} from '@angular/core';
import {Observable} from 'rxjs';

export abstract class ClientPort {
  abstract getClients(request: Signal<ClientFilterRequest | undefined>): HttpResourceRef<ClientResponse>;

  abstract createClient(request: ClientCreateRequest): Observable<void>;

  abstract delete(clientId: string): Observable<void>;
}
