package ec.devsu.api.bank.infraestructure.out.persistence.validation;

import ec.devsu.api.bank.domain.exception.InvalidDataException;
import ec.devsu.api.bank.infraestructure.out.persistence.repository.PersonJpaRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class PersonValidation {
    private final PersonJpaRepository personJpaRepository;

    public void validateExitsIdentification(String identification) {
        if (this.personJpaRepository.existsByIdentification(identification)) {
            throw new InvalidDataException("La identificación ya existe");
        }
    }
}
