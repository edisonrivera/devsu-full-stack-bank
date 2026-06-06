package ec.devsu.api.bank.infraestructure.out.persistence.adapter;

import ec.devsu.api.bank.application.port.out.account.AccountRepositoryPort;
import ec.devsu.api.bank.domain.exception.NotFoundDataException;
import ec.devsu.api.bank.infraestructure.in.rest.dto.account.request.AccountFilterRequest;
import ec.devsu.api.bank.infraestructure.in.rest.dto.account.request.AccountRequest;
import ec.devsu.api.bank.infraestructure.in.rest.dto.account.response.AccountFilterResponse;
import ec.devsu.api.bank.infraestructure.out.mapper.AccountMapper;
import ec.devsu.api.bank.infraestructure.out.persistence.entity.AccountEntity;
import ec.devsu.api.bank.infraestructure.out.persistence.repository.AccountJpaRepository;
import ec.devsu.api.bank.infraestructure.out.persistence.util.AccountUtil;
import ec.devsu.api.bank.infraestructure.out.persistence.validation.AccountTypeValidation;
import ec.devsu.api.bank.infraestructure.out.persistence.validation.AccountValidation;
import ec.devsu.api.bank.infraestructure.out.persistence.validation.ClientValidation;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@AllArgsConstructor
public class AccountPersistenceAdapter implements AccountRepositoryPort {
    private final AccountJpaRepository accountJpaRepository;
    private final ClientValidation clientValidation;
    private final AccountTypeValidation accountTypeValidation;
    private final AccountMapper accountMapper;
    private final AccountUtil accountUtil;
    private final AccountValidation accountValidation;

    @Override
    public void create(final AccountRequest request) {
        final UUID clientId =  this.clientValidation.getClienteId(request.identification());
        final Short accountTypeId = this.accountTypeValidation.validateAccountType(request.accountType());

        this.accountJpaRepository.save(this.accountMapper.toAccountEntity(request,
                this.accountUtil.generateAccountNumber(), accountTypeId, clientId));
    }

    @Override
    public AccountFilterResponse getAccounts(final AccountFilterRequest request) {
        final var accounts = this.accountJpaRepository.getAccounts(request.getIdentification(), request.pageable());

        if (accounts.isEmpty()) {
            throw new NotFoundDataException("No existen cuentas");
        }

        return new AccountFilterResponse(accounts.getContent(), accounts.getTotalElements());
    }

    @Override
    public void delete(final UUID accountId) {
        final AccountEntity account = this.accountJpaRepository.findById(accountId)
                .orElseThrow(() -> new NotFoundDataException("No existen la cuenta"));

        this.accountValidation.isEmptyAccount(account.getAmount());
        this.accountJpaRepository.delete(account);
    }
}
