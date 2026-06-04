package ec.devsu.api.bank.infraestructure.out.persistence.repository;

import ec.devsu.api.bank.infraestructure.out.persistence.entity.MovementTypeEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MovementTypeJpaRepository extends JpaRepository<MovementTypeEntity, Short> {
}
