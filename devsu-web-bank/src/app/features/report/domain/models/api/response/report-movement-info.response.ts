export interface ReportMovementInfoResponse {
  createdAt: Date;
  personName: string;
  accountNumber: string;
  accountType: string;
  amountInitial: number;
  status: boolean;
  amount: number;
  amountBalance: number;
}
