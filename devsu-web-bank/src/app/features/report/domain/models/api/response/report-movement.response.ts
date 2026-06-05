import {ReportMovementInfoResponse} from '@features/report/domain/models/api/response/report-movement-info.response';

export interface ReportMovementResponse {
  readonly movementsReport: ReportMovementInfoResponse[];
  readonly totalMovementsReport: number;
}
