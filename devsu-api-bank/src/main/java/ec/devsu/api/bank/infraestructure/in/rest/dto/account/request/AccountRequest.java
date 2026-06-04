package ec.devsu.api.bank.infraestructure.in.rest.dto.account.request;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;
import java.util.UUID;

public record AccountRequest(
        @NotNull(message = "Tipo de cuenta es requerido")
        Short accountTypeId,

        @NotNull(message = "Monto es requerido")
        @DecimalMin(value = "0.0", message = "Monto debe ser mayor o igual a 0")
        BigDecimal amount,

        @NotNull(message = "Cliente es requerido")
        UUID clientId
) {
}
