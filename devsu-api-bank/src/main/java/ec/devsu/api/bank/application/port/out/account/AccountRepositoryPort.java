package ec.devsu.api.bank.application.port.out.account;

import ec.devsu.api.bank.infraestructure.in.rest.dto.account.request.AccountRequest;

public interface AccountRepositoryPort {
    void create(AccountRequest request);
}
