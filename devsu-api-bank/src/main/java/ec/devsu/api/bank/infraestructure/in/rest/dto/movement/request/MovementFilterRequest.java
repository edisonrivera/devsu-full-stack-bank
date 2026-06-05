package ec.devsu.api.bank.infraestructure.in.rest.dto.movement.request;

import ec.devsu.api.bank.infraestructure.in.rest.dto.common.request.PageableRequest;
import jakarta.validation.constraints.Pattern;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
public class MovementFilterRequest extends PageableRequest implements Serializable {
    @Pattern(regexp = "[a-zA-Z0-9]{5,13}", message = "Identificación es inválida")
    private String identification;
}
