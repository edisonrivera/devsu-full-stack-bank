package ec.devsu.api.bank.infraestructure.in.rest.dto.client.request;

import ec.devsu.api.bank.domain.enums.GenreEnum;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;

public record ClientRequest(
        @NotBlank(message = "Nombre es requerido")
        @Pattern(regexp = "[a-zA-ZáéíóúÁÉÍÓÚüÜñÑ ]{5,200}", message = "Nombre es inválido")
        String name,

        @NotNull(message = "Género es requerido")
        GenreEnum genre,

        @NotNull(message = "Edad es requerida")
        @Min(value = 18, message = "Edad debe ser mayor o igual a {value}")
        @Max(value = 255, message = "Edad debe ser menor o igual a {value}")
        Short age,

        @NotBlank(message = "Identificación es requerida")
        @Pattern(regexp = "[a-zA-Z0-9]{5,13}", message = "Identificación es inválida")
        String identification,

        @NotBlank(message = "Dirección es requerida")
        @Pattern(regexp = "[a-zA-ZáéíóúÁÉÍÓÚüÜñÑ0-9()\\/-_.,\\[\\]@ *&$#+]{10,500}", message = "Dirección es inválida")
        String address,

        @NotBlank(message = "Teléfono es requerido")
        @Pattern(regexp = "[0-9]{10}", message = "Teléfono es inválido")
        String phone,

        @NotBlank(message = "Contraseña es requerida")
        @Pattern(
                regexp = "^(?=.*[0-9])(?=.*[A-Z])(?=.*[!@#$%^&*()\\-_=+\\[\\]{};:'\",.<>?/\\\\|`~])[A-Za-z0-9!@#$%^&*()\\-_=+\\[\\]{};:'\",.<>?/\\\\|`~]{8,72}$",
                message = "Contraseña debe tener 8-72 caracteres, una mayúscula, un número y un carácter especial"
        )
        String password
) {
}
