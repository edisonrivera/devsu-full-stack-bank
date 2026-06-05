package ec.devsu.api.bank.infraestructure.in.rest.dto.account.request;

import ec.devsu.api.bank.infraestructure.in.rest.dto.common.request.PageableRequest;
import jakarta.validation.constraints.Pattern;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
public class AccountFilterRequest extends PageableRequest implements Serializable {
    @Pattern(regexp = "[a-zA-Z0-9]{5,13}", message = "Identificación es inválida")
    private String identification;
}
