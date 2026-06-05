package ec.devsu.api.bank.infraestructure.in.rest.dto.account.response;

import ec.devsu.api.bank.infraestructure.out.persistence.projection.account.AccountInfoProjection;

import java.util.List;

public record AccountFilterResponse(List<AccountInfoProjection> accounts, long totalAccounts) {
}
