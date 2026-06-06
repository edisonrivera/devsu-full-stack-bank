import {inject, Service, Signal} from '@angular/core';
import {ClientPort} from '@features/client/domain/ports/client.port';
import {HttpClient, httpResource, HttpResourceRef} from '@angular/common/http';
import {ClientResponse} from '@features/client/domain/models/api/response/client.response';
import {environment} from '@env/environment';
import {ClientFilterRequest} from '@features/client/domain/models/api/request/client-filter.request';
import {Observable} from 'rxjs';

@Service()
export class ClientApi implements ClientPort {
  private readonly http = inject(HttpClient);

  private readonly baseUrl = `${environment.apiUrl}/devsu-api-bank/v1/api/clients`;

  getClients(request: Signal<ClientFilterRequest | undefined>): HttpResourceRef<ClientResponse> {
    return httpResource<ClientResponse>(() =>
      request() ?
        ({
          url: `${this.baseUrl}/filter`,
          method: 'POST',
          body: request()
        }) : undefined, {defaultValue: {clients: [], totalClients: 0}});
  }

  createClient(request: ClientCreateRequest): Observable<void> {
    return this.http.post<void>(this.baseUrl, request);
  }

  delete = (clientId: string): Observable<void> => this.http.delete<void>(`${this.baseUrl}/${clientId}`);
}
