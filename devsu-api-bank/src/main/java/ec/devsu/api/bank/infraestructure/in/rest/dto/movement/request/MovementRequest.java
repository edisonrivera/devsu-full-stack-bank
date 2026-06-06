package ec.devsu.api.bank.infraestructure.in.rest.dto.movement.request;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;

import java.math.BigDecimal;

public record MovementRequest(
        @Pattern(regexp = "[A-Z0-9\\-_]{50}", message = "Número de cuenta es inválido")
        @NotNull(message = "Cuenta es requerida")
        String accountNumber,

        @NotNull(message = "Monto es requerido")
        BigDecimal amount
) {
}
