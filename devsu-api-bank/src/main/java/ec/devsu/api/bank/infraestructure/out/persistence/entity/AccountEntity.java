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
import java.util.UUID;

@Getter
@Setter
@Entity
@Table(name = "Account", schema = "dbo")
public class AccountEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "account_id", nullable = false)
    private UUID accountId;

    @Column(name = "account_number", nullable = false)
    private String accountNumber;

    @Column(name = "account_type_id", nullable = false)
    private Short accountTypeId;

    private BigDecimal amount;

    private Boolean status;

    @Column(name = "client_id", nullable = false)
    private UUID clientId;
}
