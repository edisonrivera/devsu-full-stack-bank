package ec.devsu.api.bank.application.port.in.client;

import ec.devsu.api.bank.infraestructure.in.rest.dto.client.request.ClientFilterRequest;
import ec.devsu.api.bank.infraestructure.in.rest.dto.client.response.ClientFilterResponse;

public interface GetClientUseCase {
    ClientFilterResponse getClients(ClientFilterRequest request);
}
