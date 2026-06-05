package ec.devsu.api.bank.application.port.out.account;

import ec.devsu.api.bank.infraestructure.in.rest.dto.account.request.AccountFilterRequest;
import ec.devsu.api.bank.infraestructure.in.rest.dto.account.request.AccountRequest;
import ec.devsu.api.bank.infraestructure.in.rest.dto.account.response.AccountFilterResponse;

public interface AccountRepositoryPort {
    void create(AccountRequest request);

    AccountFilterResponse getAccounts(AccountFilterRequest request);
}
