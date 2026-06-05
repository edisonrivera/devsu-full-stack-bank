package ec.devsu.api.bank.infraestructure.in.rest.controller;

import ec.devsu.api.bank.application.port.in.account.CreateAccountUseCase;
import ec.devsu.api.bank.application.port.in.account.GetAccountUseCase;
import ec.devsu.api.bank.infraestructure.in.rest.dto.account.request.AccountFilterRequest;
import ec.devsu.api.bank.infraestructure.in.rest.dto.account.request.AccountRequest;
import ec.devsu.api.bank.infraestructure.in.rest.dto.account.response.AccountFilterResponse;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/v1/api/accounts")
@CrossOrigin("http://localhost:4200")
@AllArgsConstructor
public class AccountController {
    private final CreateAccountUseCase createAccountUseCase;
    private final GetAccountUseCase getAccountUseCase;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void register(@RequestBody @Valid final AccountRequest request) {
        this.createAccountUseCase.create(request);
    }

    @PostMapping("/filter")
    public AccountFilterResponse get(@RequestBody @Valid final AccountFilterRequest request) {
        return this.getAccountUseCase.getAccounts(request);
    }
}
