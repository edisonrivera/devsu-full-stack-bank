package ec.devsu.api.bank.application.port.out.person;

import ec.devsu.api.bank.infraestructure.in.rest.dto.client.request.ClientRequest;

import java.util.UUID;

public interface PersonRepositoryPort {
    UUID save(ClientRequest request);
}
