package ec.devsu.api.bank.infraestructure.out.persistence.projection.movement;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public record MovementReportInfoProjection(
        LocalDateTime createdAt,
        String personName,
        String accountNumber,
        String accountType,
        BigDecimal amountInitial,
        Boolean status,
        BigDecimal amount,
        BigDecimal amountBalance
) {
}
