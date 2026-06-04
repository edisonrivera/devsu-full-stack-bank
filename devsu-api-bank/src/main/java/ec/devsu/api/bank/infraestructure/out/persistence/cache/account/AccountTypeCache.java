package ec.devsu.api.bank.infraestructure.out.persistence.cache.account;

import ec.devsu.api.bank.infraestructure.out.persistence.repository.AccountTypeJpaRepository;
import jakarta.annotation.PostConstruct;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@AllArgsConstructor
public class AccountTypeCache {
    private final AccountTypeJpaRepository accountTypeJpaRepository;

    private List<Short> accountTypes;


    @PostConstruct
    private void init() {
        this.accountTypes = this.accountTypeJpaRepository.getAllAccountTypes();
    }

    public List<Short> getAccountTypes() {
        return this.accountTypes;
    }
}
