package ec.devsu.api.bank.application.usecase.account;

import ec.devsu.api.bank.application.port.in.account.CreateAccountUseCase;
import ec.devsu.api.bank.application.port.out.account.AccountRepositoryPort;
import ec.devsu.api.bank.infraestructure.in.rest.dto.account.request.AccountRequest;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class AccountUseCase implements CreateAccountUseCase {
    private final AccountRepositoryPort accountRepositoryPort;

    @Override
    public void create(final AccountRequest request) {
        this.accountRepositoryPort.create(request);
    }
}
