import {MovementInfoResponse} from '@features/movement/domain/models/api/response/movement-info.response';

export interface MovementFilterResponse {
  movements: MovementInfoResponse[];
  totalMovements: number;
}
