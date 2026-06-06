package ec.devsu.api.bank.infraestructure.out.persistence.validation;

import ec.devsu.api.bank.domain.exception.NotFoundDataException;
import ec.devsu.api.bank.infraestructure.out.persistence.repository.ClientJpaRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
@AllArgsConstructor
public class ClientValidation {
    private final ClientJpaRepository clientJpaRepository;

    public void validateExitsId(final UUID clienteId) {
        if (!this.clientJpaRepository.existsById(clienteId)) {
            throw new NotFoundDataException("Cliente no encontrado");
        }
    }

    public UUID getClienteId(final String identification) {
        return this.clientJpaRepository.getClientIdByIdentification(identification)
                .orElseThrow(() -> new NotFoundDataException("Cliente no encontrado"));
    }

    public UUID getPersonId(final UUID clientId) {
        return this.clientJpaRepository.getPersonIdByClientId(clientId)
                .orElseThrow(() ->  new NotFoundDataException("Cliente no encontrado"));
    }
}
