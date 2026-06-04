package ec.devsu.api.bank.infraestructure.out.persistence.projection.account;

import java.math.BigDecimal;

public record AccountProjection(BigDecimal amount, Boolean status) {
}
