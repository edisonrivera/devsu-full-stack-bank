import {PageableRequest} from '@shared/apis/common/request/pageable.request';

export interface AccountFilterRequest extends PageableRequest {
  accountNumber?: string;
}
