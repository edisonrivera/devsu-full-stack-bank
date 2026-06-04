package ec.devsu.api.bank.infraestructure.out.persistence.validation;

import ec.devsu.api.bank.domain.enums.ConfigEnum;
import ec.devsu.api.bank.domain.enums.MovementTypeEnum;
import ec.devsu.api.bank.domain.exception.InvalidDataException;
import ec.devsu.api.bank.infraestructure.out.persistence.cache.config.ConfigCache;
import ec.devsu.api.bank.infraestructure.out.persistence.cache.movement.MovementTypeCache;
import ec.devsu.api.bank.infraestructure.out.persistence.repository.MovementJpaRepository;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.UUID;

@Component
@RequiredArgsConstructor
public class MovementValidation {
    private final MovementTypeCache movementTypeCache;
    private final ConfigCache configCache;
    private final MovementJpaRepository movementJpaRepository;

    private Short movementTypeId;
    private BigDecimal dailyDebitLimit;

    @PostConstruct
    public void init() {
        this.movementTypeId = this.movementTypeCache.getMovementTypeId(MovementTypeEnum.DEB);
        this.dailyDebitLimit = new BigDecimal(this.configCache.getConfigValue(ConfigEnum.DAILY_AMOUNT));
    }


    public void hasEnoughBalance(final BigDecimal amount, final BigDecimal balance,
                                 final MovementTypeEnum movementType) {
        if (MovementTypeEnum.CRE == movementType) {
            return;
        }

        if (balance.subtract(amount.abs()).compareTo(BigDecimal.ZERO) < 0) {
            throw new InvalidDataException("Saldo no disponible");
        }
    }

    public void validateDailyDebitLimit(final UUID accountId, final BigDecimal amount) {
        var today = LocalDate.now();
        var startOfDay = today.atStartOfDay();
        var endOfDay = today.plusDays(1).atStartOfDay();

        var currentDailyDebitAmount = this.movementJpaRepository.getDailyDebit(accountId, this.movementTypeId,
                        startOfDay, endOfDay)
                .orElse(BigDecimal.ZERO);

        if (currentDailyDebitAmount.add(amount).abs().compareTo(this.dailyDebitLimit) > 0) {
            throw new InvalidDataException("Cupo diario Excedido");
        }
    }
}
