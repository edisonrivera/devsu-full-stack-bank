package ec.devsu.api.bank.infraestructure.out.persistence.repository;

import ec.devsu.api.bank.infraestructure.out.persistence.entity.ClientEntity;
import ec.devsu.api.bank.infraestructure.out.persistence.projection.client.ClientInfoProjection;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.ListCrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface ClientJpaRepository extends ListCrudRepository<ClientEntity, UUID> {
    @Query("""
            SELECT p.personId, p.name, g.genreId, g.description, p.age, p.identification, p.address, p.phone,
                        c.clientId, c.status FROM ClientEntity c
            INNER JOIN PersonEntity p ON c.personId = p.personId
            INNER JOIN GenreEntity g ON p.genreId = g.genreId
            WHERE :identification IS NULL OR p.identification = :identification
            """)
    Page<ClientInfoProjection> getClients(String identification, Pageable pageable);

    @Query("""
          SELECT c.clientId FROM ClientEntity c
          INNER JOIN PersonEntity p ON p.personId = c.personId
          WHERE p.identification = :identification
          """)
    Optional<UUID> getClientIdByIdentification(String identification);

    @Query("SELECT personId FROM ClientEntity WHERE clientId = :clientId")
    Optional<UUID> getPersonIdByClientId(UUID clientId);
}
