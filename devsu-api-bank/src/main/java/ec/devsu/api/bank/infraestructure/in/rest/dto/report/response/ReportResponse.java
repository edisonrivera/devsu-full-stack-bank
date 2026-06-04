package ec.devsu.api.bank.infraestructure.in.rest.dto.report.response;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public record ReportResponse(
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
