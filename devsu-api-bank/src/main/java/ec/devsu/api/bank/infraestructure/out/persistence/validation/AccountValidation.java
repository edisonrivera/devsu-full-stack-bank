package ec.devsu.api.bank.infraestructure.out.persistence.validation;

import ec.devsu.api.bank.domain.exception.InvalidDataException;
import ec.devsu.api.bank.domain.exception.NotFoundDataException;
import ec.devsu.api.bank.infraestructure.out.persistence.projection.account.AccountProjection;
import ec.devsu.api.bank.infraestructure.out.persistence.repository.AccountJpaRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
@AllArgsConstructor
public class AccountValidation {
    private final AccountJpaRepository accountJpaRepository;

    public AccountProjection findAccount(final UUID accountId) {
        return this.accountJpaRepository.findByAccountId(accountId)
                .orElseThrow(() -> new NotFoundDataException("Account not found"));
    }

    public void isActiveAccount(final Boolean status) {
        if (Boolean.FALSE.equals(status)) {
            throw new InvalidDataException("La cuenta no está activa");
        }
    }
}
