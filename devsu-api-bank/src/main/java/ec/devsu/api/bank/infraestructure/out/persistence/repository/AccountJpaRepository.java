package ec.devsu.api.bank.infraestructure.out.persistence.repository;

import ec.devsu.api.bank.infraestructure.out.persistence.entity.AccountEntity;
import ec.devsu.api.bank.infraestructure.out.persistence.projection.account.AccountInfoProjection;
import ec.devsu.api.bank.infraestructure.out.persistence.projection.account.AccountProjection;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
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

    @Query("""
            SELECT a.accountId, a.accountNumber, a.accountTypeId, aty.description, a.amount, a.status
            FROM AccountEntity a
            INNER JOIN AccountTypeEntity aty ON a.accountTypeId = aty.accountTypeId
            INNER JOIN ClientEntity c ON c.clientId = a.clientId
            INNER JOIN PersonEntity p ON p.personId = c.personId
            WHERE :identification IS NULL OR p.identification = :identification
            """)
    Page<AccountInfoProjection> getAccounts(String identification, Pageable pageable);
}
