import {PageableRequest} from '@shared/apis/common/request/pageable.request';

export interface ClientFilterRequest extends PageableRequest {
  identification?: string;
}
