package ec.devsu.api.bank.infraestructure.out.persistence.adapter;

import ec.devsu.api.bank.application.port.out.client.ClientRepositoryPort;
import ec.devsu.api.bank.infraestructure.in.rest.dto.client.request.ClientRequest;
import ec.devsu.api.bank.infraestructure.out.persistence.entity.ClientEntity;
import ec.devsu.api.bank.infraestructure.out.persistence.repository.ClientJpaRepository;
import ec.devsu.api.bank.infraestructure.out.persistence.validation.GenreValidation;
import ec.devsu.api.bank.infraestructure.out.persistence.validation.PersonValidation;
import org.springframework.transaction.annotation.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@AllArgsConstructor
public class ClientPersistenceAdapter implements ClientRepositoryPort {
    private final PersonPersistenceAdapter personPersistenceAdapter;
    private final ClientJpaRepository clientJpaRepository;
    private final GenreValidation genreValidation;
    private final PersonValidation personValidation;
    private final PasswordEncoder passwordEncoder;

    @Transactional
    @Override
    public void create(final ClientRequest request) {
        this.genreValidation.validateGenre(request.genre());
        this.personValidation.validateExitsIdentification(request.identification());

        final UUID personIdentifier = this.personPersistenceAdapter.save(request);
        final ClientEntity clientEntity = this.mapClient(personIdentifier, request.password());
        this.clientJpaRepository.save(clientEntity);
    }

    public ClientEntity mapClient(final UUID personId, final String password) {
        final ClientEntity clientEntity = new ClientEntity();
        clientEntity.setPersonId(personId);
        clientEntity.setPassword(passwordEncoder.encode(password));
        clientEntity.setStatus(true);
        return clientEntity;
    }
}
