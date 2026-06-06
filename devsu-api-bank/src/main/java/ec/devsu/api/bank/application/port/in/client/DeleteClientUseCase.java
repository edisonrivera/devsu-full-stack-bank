package ec.devsu.api.bank.application.port.in.client;

import java.util.UUID;

public interface DeleteClientUseCase {
    void delete(UUID clientId);
}
