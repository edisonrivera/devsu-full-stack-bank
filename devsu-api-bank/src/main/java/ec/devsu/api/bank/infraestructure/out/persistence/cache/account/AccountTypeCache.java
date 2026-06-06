package ec.devsu.api.bank.infraestructure.out.persistence.cache.account;

import ec.devsu.api.bank.domain.enums.AccountTypeEnum;
import ec.devsu.api.bank.infraestructure.out.persistence.repository.AccountTypeJpaRepository;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.EnumMap;
import java.util.List;
import java.util.Map;

@Component
@RequiredArgsConstructor
public class AccountTypeCache {
    private final AccountTypeJpaRepository accountTypeJpaRepository;

    private Map<AccountTypeEnum, Short> accountTypeMap = new EnumMap<>(AccountTypeEnum.class);

    private List<Short> accountTypes;

    @PostConstruct
    private void init() {
        this.accountTypeJpaRepository.getAllAccountTypes()
                .forEach(a -> accountTypeMap.put(a.mnemonic(), a.accountTypeId()));
    }

    public Short getAccountType(final AccountTypeEnum accountTypeEnum) {
        return this.accountTypeMap.get(accountTypeEnum);
    }
}
