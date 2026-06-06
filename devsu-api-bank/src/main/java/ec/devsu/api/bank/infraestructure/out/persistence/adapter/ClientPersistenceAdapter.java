package ec.devsu.api.bank.infraestructure.out.persistence.adapter;

import ec.devsu.api.bank.application.port.out.client.ClientRepositoryPort;
import ec.devsu.api.bank.domain.exception.NotFoundDataException;
import ec.devsu.api.bank.infraestructure.in.rest.dto.client.request.ClientFilterRequest;
import ec.devsu.api.bank.infraestructure.in.rest.dto.client.request.ClientRequest;
import ec.devsu.api.bank.infraestructure.in.rest.dto.client.response.ClientFilterResponse;
import ec.devsu.api.bank.infraestructure.out.persistence.entity.ClientEntity;
import ec.devsu.api.bank.infraestructure.out.persistence.repository.ClientJpaRepository;
import ec.devsu.api.bank.infraestructure.out.persistence.repository.PersonJpaRepository;
import ec.devsu.api.bank.infraestructure.out.persistence.validation.AccountValidation;
import ec.devsu.api.bank.infraestructure.out.persistence.validation.ClientValidation;
import ec.devsu.api.bank.infraestructure.out.persistence.validation.PersonValidation;
import lombok.AllArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@Service
@AllArgsConstructor
public class ClientPersistenceAdapter implements ClientRepositoryPort {
    private final PersonPersistenceAdapter personPersistenceAdapter;
    private final ClientJpaRepository clientJpaRepository;
    private final PersonJpaRepository personJpaRepository;
    private final PersonValidation personValidation;
    private final PasswordEncoder passwordEncoder;
    private final ClientValidation clientValidation;
    private final AccountValidation accountValidation;

    @Transactional
    @Override
    public void create(final ClientRequest request) {
        this.personValidation.validateExitsIdentification(request.identification());

        final UUID personIdentifier = this.personPersistenceAdapter.save(request);
        final ClientEntity clientEntity = this.mapClient(personIdentifier, request.password());
        this.clientJpaRepository.save(clientEntity);
    }

    @Override
    public ClientFilterResponse getClients(final ClientFilterRequest request) {
        final var clientes = this.clientJpaRepository.getClients(request.getIdentification(), request.pageable());

        if (clientes.isEmpty()) {
            throw new NotFoundDataException("No existen clientes");
        }

        return new ClientFilterResponse(clientes.getContent(), clientes.getTotalElements());
    }

    @Override
    public void delete(final UUID clientId) {
        this.clientValidation.validateExitsId(clientId);
        this.accountValidation.isAllAccountEmpty(clientId);
        this.personJpaRepository.deleteById(this.clientValidation.getPersonId(clientId));
    }

    public ClientEntity mapClient(final UUID personId, final String password) {
        final ClientEntity clientEntity = new ClientEntity();
        clientEntity.setPersonId(personId);
        clientEntity.setPassword(passwordEncoder.encode(password));
        clientEntity.setStatus(true);
        return clientEntity;
    }
}
