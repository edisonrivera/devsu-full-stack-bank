package ec.devsu.api.bank.application.usecase.client;

import ec.devsu.api.bank.application.port.in.client.CreateClientUseCase;
import ec.devsu.api.bank.application.port.out.client.ClientRepositoryPort;
import ec.devsu.api.bank.infraestructure.in.rest.dto.client.request.ClientRequest;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class ClientUseCase implements CreateClientUseCase {
    private final ClientRepositoryPort clientRepositoryPort;

    @Override
    public void create(final ClientRequest request) {
        this.clientRepositoryPort.create(request);
    }
}
