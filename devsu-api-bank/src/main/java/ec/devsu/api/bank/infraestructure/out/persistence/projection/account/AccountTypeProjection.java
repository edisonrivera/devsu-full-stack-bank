package ec.devsu.api.bank.infraestructure.out.persistence.projection.account;

import ec.devsu.api.bank.domain.enums.AccountTypeEnum;

public record AccountTypeProjection(Short accountTypeId, AccountTypeEnum mnemonic) {
}
