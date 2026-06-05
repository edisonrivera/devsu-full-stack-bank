package ec.devsu.api.bank.infraestructure.out.persistence.cache.genre;

import ec.devsu.api.bank.domain.enums.GenreEnum;
import ec.devsu.api.bank.infraestructure.out.persistence.repository.GenreJpaRepository;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.EnumMap;
import java.util.Map;

@Component
@RequiredArgsConstructor
public class GenreCache {
    private final GenreJpaRepository genreJpaRepository;

    private Map<GenreEnum, Short> genreMap = new EnumMap<>(GenreEnum.class);

    @PostConstruct
    public void init() {
        this.genreJpaRepository.getAllGenres()
                .forEach(g -> this.genreMap.put(g.mnemonic(), g.genreId()));
    }

    public Short getGenreId(final GenreEnum genreEnum) {
        return this.genreMap.get(genreEnum);
    }
}
