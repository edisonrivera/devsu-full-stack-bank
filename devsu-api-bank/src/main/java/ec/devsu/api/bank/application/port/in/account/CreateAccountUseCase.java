package ec.devsu.api.bank.application.port.in.account;

import ec.devsu.api.bank.infraestructure.in.rest.dto.account.request.AccountRequest;

public interface CreateAccountUseCase {
    void create(AccountRequest request);
}
