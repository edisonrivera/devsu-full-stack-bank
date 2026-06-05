package ec.devsu.api.bank.infraestructure.out.persistence.adapter;

import ec.devsu.api.bank.application.port.out.account.AccountRepositoryPort;
import ec.devsu.api.bank.domain.exception.NotFoundDataException;
import ec.devsu.api.bank.infraestructure.in.rest.dto.account.request.AccountFilterRequest;
import ec.devsu.api.bank.infraestructure.in.rest.dto.account.request.AccountRequest;
import ec.devsu.api.bank.infraestructure.in.rest.dto.account.response.AccountFilterResponse;
import ec.devsu.api.bank.infraestructure.out.mapper.AccountMapper;
import ec.devsu.api.bank.infraestructure.out.persistence.repository.AccountJpaRepository;
import ec.devsu.api.bank.infraestructure.out.persistence.util.AccountUtil;
import ec.devsu.api.bank.infraestructure.out.persistence.validation.AccountTypeValidation;
import ec.devsu.api.bank.infraestructure.out.persistence.validation.ClientValidation;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class AccountPersistenceAdapter implements AccountRepositoryPort {
    private final AccountJpaRepository accountJpaRepository;
    private final ClientValidation clientValidation;
    private final AccountTypeValidation accountTypeValidation;
    private final AccountMapper accountMapper;
    private final AccountUtil accountUtil;

    @Override
    public void create(final AccountRequest request) {
        this.clientValidation.validateExitsId(request.clientId());
        this.accountTypeValidation.validateAccountType(request.accountTypeId());
        this.accountJpaRepository.save(this.accountMapper.toAccountEntity(request,
                this.accountUtil.generateAccountNumber()));
    }

    @Override
    public AccountFilterResponse getAccounts(final AccountFilterRequest request) {
        final var accounts = this.accountJpaRepository.getAccounts(request.getIdentification(), request.pageable());

        if (accounts.isEmpty()) {
            throw new NotFoundDataException("No existen cuentas");
        }

        return new AccountFilterResponse(accounts.getContent(), accounts.getTotalElements());
    }
}
