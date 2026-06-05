package ec.devsu.api.bank.application.port.out.client;

import ec.devsu.api.bank.infraestructure.in.rest.dto.client.request.ClientFilterRequest;
import ec.devsu.api.bank.infraestructure.in.rest.dto.client.request.ClientRequest;
import ec.devsu.api.bank.infraestructure.in.rest.dto.client.response.ClientFilterResponse;

public interface ClientRepositoryPort {
    void create(ClientRequest request);

    ClientFilterResponse getClients(ClientFilterRequest request);
}
