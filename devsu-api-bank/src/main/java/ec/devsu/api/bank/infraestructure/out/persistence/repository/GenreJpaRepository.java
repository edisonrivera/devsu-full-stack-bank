package ec.devsu.api.bank.infraestructure.out.persistence.repository;

import ec.devsu.api.bank.infraestructure.out.persistence.entity.GenreEntity;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.ListCrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface GenreJpaRepository extends ListCrudRepository<GenreEntity, Short> {
    @Query("SELECT genreId FROM GenreEntity")
    List<Short> getAllGenres();
}
