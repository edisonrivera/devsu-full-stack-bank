package ec.devsu.api.bank.infraestructure.out.persistence.adapter;

import ec.devsu.api.bank.application.port.out.person.PersonRepositoryPort;
import ec.devsu.api.bank.infraestructure.in.rest.dto.client.request.ClientRequest;
import ec.devsu.api.bank.infraestructure.out.mapper.PersonMapper;
import ec.devsu.api.bank.infraestructure.out.persistence.repository.PersonJpaRepository;
import ec.devsu.api.bank.infraestructure.out.persistence.validation.GenreValidation;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@AllArgsConstructor
public class PersonPersistenceAdapter implements PersonRepositoryPort {
    private final PersonJpaRepository personJpaRepository;
    private final PersonMapper personMapper;
    private final GenreValidation genreValidation;

    @Override
    public UUID save(final ClientRequest request) {
        final Short genreId = this.genreValidation.validateGenre(request.genre());

        var personSaved = this.personJpaRepository.save(this.personMapper.toPersonEntity(request, genreId));
        return personSaved.getPersonId();
    }
}
