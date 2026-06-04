package ec.devsu.api.bank.infraestructure.out.persistence.repository;

import ec.devsu.api.bank.infraestructure.out.persistence.entity.AccountEntity;
import ec.devsu.api.bank.infraestructure.out.persistence.projection.account.AccountProjection;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface AccountJpaRepository extends JpaRepository<AccountEntity, UUID> {
    Optional<AccountProjection> findByAccountId(UUID accountId);

    @Modifying
    @Query("UPDATE AccountEntity SET amount = :balance WHERE accountId = :accountId")
    void updateBalance(BigDecimal balance, UUID accountId);
}
