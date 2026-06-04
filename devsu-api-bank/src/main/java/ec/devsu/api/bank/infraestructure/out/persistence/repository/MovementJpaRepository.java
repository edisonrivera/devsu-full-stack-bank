package ec.devsu.api.bank.infraestructure.out.persistence.repository;

import ec.devsu.api.bank.infraestructure.in.rest.dto.report.response.ReportResponse;
import ec.devsu.api.bank.infraestructure.out.persistence.entity.MovementEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface MovementJpaRepository extends JpaRepository<MovementEntity, Integer> {

    @Query("""
            SELECT SUM(m.amount) FROM MovementEntity m
            WHERE m.accountId = :accountId
            AND m.movementTypeId = :movementTypeId
            AND m.createdAt >= :startOfDay
            AND m.createdAt < :endOfDay
            """)
    Optional<BigDecimal> getDailyDebit(UUID accountId, Short movementTypeId, LocalDateTime startOfDay,
                                       LocalDateTime endOfDay);

    @Query("""
            SELECT m.createdAt, p.name, a.accountNumber, aty.description, m.amountInitial, a.status, m.amount, 
                        m.amountBalance  
            FROM MovementEntity m
            INNER JOIN AccountEntity a ON a.accountId = m.accountId
            INNER JOIN AccountTypeEntity aty ON a.accountTypeId = aty.accountTypeId
            INNER JOIN ClientEntity c ON c.clientId = a.clientId
            INNER JOIN PersonEntity p ON p.personId = c.personId
            WHERE m.createdAt >= :start AND m.createdAt < :end
            ORDER BY m.createdAt DESC
            """)
    List<ReportResponse> reportMovements(LocalDateTime start, LocalDateTime end);
}
