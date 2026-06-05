package ec.devsu.api.bank.infraestructure.out.persistence.projection.movement;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public record MovementInfoProjection(String identification, LocalDateTime createdAt, String description,
                                     String accountType, BigDecimal amount, BigDecimal amountInitial,
                                     BigDecimal amountBalance) {
}
