package ec.devsu.api.bank.infraestructure.out.persistence.validation;

import ec.devsu.api.bank.domain.exception.NotFoundDataException;
import ec.devsu.api.bank.infraestructure.out.persistence.cache.account.AccountTypeCache;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class AccountTypeValidation {
    private final AccountTypeCache accountTypeCache;

    public void validateAccountType(final Short accountType) {
        if (!this.accountTypeCache.getAccountTypes().contains(accountType)) {
            throw new NotFoundDataException("Tipo de cuenta no encontrado");
        }
    }
}
