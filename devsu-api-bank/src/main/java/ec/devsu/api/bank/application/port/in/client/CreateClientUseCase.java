package ec.devsu.api.bank.application.port.in.client;

import ec.devsu.api.bank.infraestructure.in.rest.dto.client.request.ClientRequest;

public interface CreateClientUseCase {
    void create(ClientRequest request);
}
