package ec.devsu.api.bank.infraestructure.out.persistence.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.Instant;
import java.time.LocalDateTime;
import java.util.UUID;

@Getter
@Setter
@Entity
@Table(name = "Movement", schema = "dbo")
public class MovementEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "movement_id", nullable = false)
    private UUID movementId;

    @Column(name = "created_at", nullable = false)
    private LocalDateTime createdAt;

    @Column(name = "movement_type_id", nullable = false)
    private Short movementTypeId;

    private BigDecimal amount;

    @Column(name = "amount_initial", nullable = false)
    private BigDecimal amountInitial;

    @Column(name = "account_id", nullable = false)
    private UUID accountId;

    @Column(name = "amount_balance", nullable = false)
    private BigDecimal amountBalance;
}
