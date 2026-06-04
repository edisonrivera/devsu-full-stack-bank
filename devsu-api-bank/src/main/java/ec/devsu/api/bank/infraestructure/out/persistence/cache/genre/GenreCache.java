package ec.devsu.api.bank.infraestructure.out.persistence.cache.genre;

import ec.devsu.api.bank.infraestructure.out.persistence.repository.GenreJpaRepository;
import jakarta.annotation.PostConstruct;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@AllArgsConstructor
public class GenreCache {
    private final GenreJpaRepository genreJpaRepository;

    private List<Short> genreIds;

    @PostConstruct
    public void init() {
        this.genreIds = this.genreJpaRepository.getAllGenres();
    }

    public List<Short> getAllGenres() {
        return this.genreIds;
    }
}
