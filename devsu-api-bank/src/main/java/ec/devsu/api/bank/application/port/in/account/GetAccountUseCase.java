package ec.devsu.api.bank.application.port.in.account;

import ec.devsu.api.bank.infraestructure.in.rest.dto.account.request.AccountFilterRequest;
import ec.devsu.api.bank.infraestructure.in.rest.dto.account.response.AccountFilterResponse;

public interface GetAccountUseCase {
    AccountFilterResponse getAccounts(AccountFilterRequest request);
}
