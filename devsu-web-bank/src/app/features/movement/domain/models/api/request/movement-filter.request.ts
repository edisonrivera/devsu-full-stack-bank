import {PageableRequest} from '@shared/apis/common/request/pageable.request';

export interface MovementFilterRequest extends PageableRequest {
  identification?: string;
}
