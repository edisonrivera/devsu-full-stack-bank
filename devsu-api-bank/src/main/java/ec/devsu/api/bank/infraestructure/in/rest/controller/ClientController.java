package ec.devsu.api.bank.infraestructure.in.rest.controller;

import ec.devsu.api.bank.application.port.in.client.CreateClientUseCase;
import ec.devsu.api.bank.application.port.in.client.GetClientUseCase;
import ec.devsu.api.bank.infraestructure.in.rest.dto.client.request.ClientFilterRequest;
import ec.devsu.api.bank.infraestructure.in.rest.dto.client.request.ClientRequest;
import ec.devsu.api.bank.infraestructure.in.rest.dto.client.response.ClientFilterResponse;
import ec.devsu.api.bank.infraestructure.out.persistence.projection.client.ClientInfoProjection;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/v1/api/clients")
@CrossOrigin("http://localhost:4200")
@AllArgsConstructor
public class ClientController {
    private final CreateClientUseCase createClientUseCase;
    private final GetClientUseCase getClientUseCase;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void register(@RequestBody @Valid final ClientRequest request) {
        this.createClientUseCase.create(request);
    }


    @PostMapping("/filter")
    public ClientFilterResponse get(@RequestBody @Valid final ClientFilterRequest request) {
        return this.getClientUseCase.getClients(request);
    }

}
