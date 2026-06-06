package ec.devsu.api.bank.infraestructure.out.persistence.projection.account;

import java.math.BigDecimal;
import java.util.UUID;

public record AccountProjection(UUID accountId, BigDecimal amount, Boolean status) {
}
