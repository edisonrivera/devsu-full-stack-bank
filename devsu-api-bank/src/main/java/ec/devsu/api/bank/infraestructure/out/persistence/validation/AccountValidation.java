package ec.devsu.api.bank.infraestructure.out.persistence.validation;

import ec.devsu.api.bank.domain.exception.InvalidDataException;
import ec.devsu.api.bank.domain.exception.NotFoundDataException;
import ec.devsu.api.bank.infraestructure.out.persistence.projection.account.AccountProjection;
import ec.devsu.api.bank.infraestructure.out.persistence.repository.AccountJpaRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.UUID;

@Component
@AllArgsConstructor
public class AccountValidation {
    private final AccountJpaRepository accountJpaRepository;

    public AccountProjection findAccount(final String accountNumber) {
        return this.accountJpaRepository.findByAccountNumber(accountNumber)
                .orElseThrow(() -> new NotFoundDataException("Account not found"));
    }

    public void isActiveAccount(final Boolean status) {
        if (Boolean.FALSE.equals(status)) {
            throw new InvalidDataException("La cuenta no está activa");
        }
    }

    public void isEmptyAccount(final BigDecimal amount) {
        if (amount.compareTo(BigDecimal.ZERO) != 0) {
            throw new InvalidDataException("La cuenta no debe tener balance mayor a 0");
        }
    }

    public void isAllAccountEmpty(final UUID clientId) {
        this.isEmptyAccount(this.accountJpaRepository.getTotalBalance(clientId).orElse(BigDecimal.ZERO));
    }
}
