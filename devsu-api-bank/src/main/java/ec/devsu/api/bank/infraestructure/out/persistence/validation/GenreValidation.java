package ec.devsu.api.bank.infraestructure.out.persistence.validation;

import ec.devsu.api.bank.domain.exception.InvalidDataException;
import ec.devsu.api.bank.infraestructure.out.persistence.cache.genre.GenreCache;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class GenreValidation {
    private final GenreCache genreCache;

    public void validateGenre(final Short genreId) {
        if (!this.genreCache.getAllGenres().contains(genreId)) {
            throw new InvalidDataException("Género no encontrado");
        }
    }
}
