package ec.devsu.api.bank.infraestructure.out.persistence.validation;

import ec.devsu.api.bank.domain.enums.GenreEnum;
import ec.devsu.api.bank.infraestructure.out.persistence.cache.genre.GenreCache;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class GenreValidation {
    private final GenreCache genreCache;

    public Short validateGenre(final GenreEnum genreEnum) {
        return this.genreCache.getGenreId(genreEnum);
    }
}
