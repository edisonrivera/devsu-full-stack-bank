package ec.devsu.api.bank.application.port.out.client;

import ec.devsu.api.bank.infraestructure.in.rest.dto.client.request.ClientRequest;

public interface ClientRepositoryPort {
    void create(ClientRequest request);
}
