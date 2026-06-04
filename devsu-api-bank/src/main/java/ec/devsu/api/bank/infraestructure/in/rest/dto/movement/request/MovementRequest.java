package ec.devsu.api.bank.infraestructure.in.rest.dto.movement.request;

import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;
import java.util.UUID;

public record MovementRequest(
        @NotNull(message = "Cuenta es requerida")
        UUID accountId,

        @NotNull(message = "Monto es requerido")
        BigDecimal amount
) {
}
