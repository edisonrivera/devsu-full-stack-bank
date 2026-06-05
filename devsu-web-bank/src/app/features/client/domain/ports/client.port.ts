import {HttpResourceRef} from '@angular/common/http';
import {ClientResponse} from '@features/client/domain/models/api/response/client.response';

export abstract class ClientPort {
  abstract getClients(): HttpResourceRef<ClientResponse[]>;
}
