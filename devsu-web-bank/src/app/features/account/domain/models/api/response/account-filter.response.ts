import {AccountInfoResponse} from "@features/account/domain/models/api/response/account-info.response";

export interface AccountFilterResponse {
    accounts: AccountInfoResponse[];
    totalAccounts: number;
}
