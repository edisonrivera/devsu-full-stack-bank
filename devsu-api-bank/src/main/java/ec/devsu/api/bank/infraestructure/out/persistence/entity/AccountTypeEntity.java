package ec.devsu.api.bank.infraestructure.out.persistence.entity;

import ec.devsu.api.bank.domain.enums.AccountTypeEnum;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "AccountType", schema = "dbo")
public class AccountTypeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "account_type_id", columnDefinition = "tinyint not null")
    private Short accountTypeId;

    private String description;

    @Enumerated(EnumType.STRING)
    private AccountTypeEnum mnemonic;
}
