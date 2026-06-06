package ec.devsu.api.bank.application.port.out.account;

import ec.devsu.api.bank.infraestructure.in.rest.dto.account.request.AccountFilterRequest;
import ec.devsu.api.bank.infraestructure.in.rest.dto.account.request.AccountRequest;
import ec.devsu.api.bank.infraestructure.in.rest.dto.account.response.AccountFilterResponse;

import java.util.UUID;

public interface AccountRepositoryPort {
    void create(AccountRequest request);

    AccountFilterResponse getAccounts(AccountFilterRequest request);

    void delete(UUID accountId);
}
