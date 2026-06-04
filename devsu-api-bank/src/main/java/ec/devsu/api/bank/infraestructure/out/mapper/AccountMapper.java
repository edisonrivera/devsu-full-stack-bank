package ec.devsu.api.bank.infraestructure.out.mapper;

import ec.devsu.api.bank.infraestructure.in.rest.dto.account.request.AccountRequest;
import ec.devsu.api.bank.infraestructure.out.persistence.entity.AccountEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface AccountMapper {
    @Mapping(target = "status", constant = "true")
    @Mapping(target = "accountNumber", source = "accountNumber")
    @Mapping(target = "clientId", source = "accountRequest.clientId")
    AccountEntity toAccountEntity(AccountRequest accountRequest, String accountNumber);
}
