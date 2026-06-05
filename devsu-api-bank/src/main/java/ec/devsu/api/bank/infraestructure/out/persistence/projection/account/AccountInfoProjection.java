package ec.devsu.api.bank.infraestructure.out.persistence.projection.account;

import java.math.BigDecimal;
import java.util.UUID;

public record AccountInfoProjection(UUID accountId, String accountNumber, Short accountTypeId, String description,
                                    BigDecimal amount, Boolean status) {
}
