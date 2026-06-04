package ec.devsu.api.bank.infraestructure.out.persistence.repository;

import ec.devsu.api.bank.infraestructure.out.persistence.entity.AccountTypeEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AccountTypeJpaRepository extends JpaRepository<AccountTypeEntity, Short> {
    @Query("SELECT accountTypeId FROM AccountTypeEntity")
    List<Short> getAllAccountTypes();
}
