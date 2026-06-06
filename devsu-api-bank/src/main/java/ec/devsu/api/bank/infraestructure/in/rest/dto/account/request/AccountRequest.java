package ec.devsu.api.bank.infraestructure.in.rest.dto.account.request;

import ec.devsu.api.bank.domain.enums.AccountTypeEnum;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;

import java.math.BigDecimal;

public record AccountRequest(
        @NotNull(message = "Tipo de cuenta es requerido")
        AccountTypeEnum accountType,

        @NotNull(message = "Monto es requerido")
        @DecimalMin(value = "0.0", message = "Monto debe ser mayor o igual a 0")
        BigDecimal amount,

        @NotBlank(message = "Identificación es requerida")
        @Pattern(regexp = "[a-zA-Z0-9]{5,13}", message = "Identificación es inválida")
        String identification
) {
}
