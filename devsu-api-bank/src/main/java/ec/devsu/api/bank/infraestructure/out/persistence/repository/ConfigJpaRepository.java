package ec.devsu.api.bank.infraestructure.out.persistence.repository;

import ec.devsu.api.bank.infraestructure.out.persistence.entity.ConfigurationEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ConfigJpaRepository extends JpaRepository<ConfigurationEntity, Short> {
    @Query("SELECT configValue FROM ConfigurationEntity WHERE code = :code")
    Optional<String> getConfigValue(String code);
}
