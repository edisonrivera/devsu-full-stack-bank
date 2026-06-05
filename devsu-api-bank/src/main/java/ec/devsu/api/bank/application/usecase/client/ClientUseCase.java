package ec.devsu.api.bank.application.usecase.client;

import ec.devsu.api.bank.application.port.in.client.CreateClientUseCase;
import ec.devsu.api.bank.application.port.in.client.GetClientUseCase;
import ec.devsu.api.bank.application.port.out.client.ClientRepositoryPort;
import ec.devsu.api.bank.infraestructure.in.rest.dto.client.request.ClientFilterRequest;
import ec.devsu.api.bank.infraestructure.in.rest.dto.client.request.ClientRequest;
import ec.devsu.api.bank.infraestructure.in.rest.dto.client.response.ClientFilterResponse;
import ec.devsu.api.bank.infraestructure.out.persistence.projection.client.ClientInfoProjection;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class ClientUseCase implements CreateClientUseCase, GetClientUseCase {
    private final ClientRepositoryPort clientRepositoryPort;

    @Override
    public void create(final ClientRequest request) {
        this.clientRepositoryPort.create(request);
    }

    @Override
    public ClientFilterResponse getClients(final ClientFilterRequest request) {
        return this.clientRepositoryPort.getClients(request);
    }
}
