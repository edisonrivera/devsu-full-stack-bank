package ec.devsu.api.bank.infraestructure.in.rest.controller;

import ec.devsu.api.bank.application.port.in.account.CreateAccountUseCase;
import ec.devsu.api.bank.infraestructure.in.rest.dto.account.request.AccountRequest;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/v1/api/accounts")
@AllArgsConstructor
public class AccountController {
    private final CreateAccountUseCase createAccountUseCase;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void register(@RequestBody @Valid final AccountRequest request) {
        this.createAccountUseCase.create(request);
    }
}
