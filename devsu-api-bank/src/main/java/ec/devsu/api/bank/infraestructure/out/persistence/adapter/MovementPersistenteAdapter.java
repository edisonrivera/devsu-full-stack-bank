package ec.devsu.api.bank.infraestructure.out.persistence.adapter;

import ec.devsu.api.bank.application.port.out.movement.MovementRepositoryPort;
import ec.devsu.api.bank.domain.enums.MovementTypeEnum;
import ec.devsu.api.bank.domain.exception.InvalidDataException;
import ec.devsu.api.bank.domain.exception.NotFoundDataException;
import ec.devsu.api.bank.infraestructure.in.rest.dto.movement.request.MovementFilterRequest;
import ec.devsu.api.bank.infraestructure.in.rest.dto.movement.request.MovementRequest;
import ec.devsu.api.bank.infraestructure.in.rest.dto.movement.response.MovementFilterResponse;
import ec.devsu.api.bank.infraestructure.out.persistence.cache.movement.MovementTypeCache;
import ec.devsu.api.bank.infraestructure.out.persistence.entity.MovementEntity;
import ec.devsu.api.bank.infraestructure.out.persistence.repository.AccountJpaRepository;
import ec.devsu.api.bank.infraestructure.out.persistence.repository.MovementJpaRepository;
import ec.devsu.api.bank.infraestructure.out.persistence.validation.AccountValidation;
import ec.devsu.api.bank.infraestructure.out.persistence.validation.MovementValidation;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

@Service
@AllArgsConstructor
public class MovementPersistenteAdapter implements MovementRepositoryPort {
    private final MovementJpaRepository movementJpaRepository;
    private final AccountJpaRepository accountJpaRepository;
    private final MovementTypeCache movementTypeCache;
    private final AccountValidation accountValidation;
    private final MovementValidation movementValidation;

    @Transactional
    @Override
    public void create(final MovementRequest request) {
        final var amount = request.amount();
        final var accountId = request.accountId();
        final var accountInfo = this.accountValidation.findAccount(accountId);
        final var movementType = this.getMovementType(amount);

        this.accountValidation.isActiveAccount(accountInfo.status());
        this.validateAmount(amount);
        this.validateBalanceAmount(movementType, amount, accountInfo.amount(), accountId);

        this.movementJpaRepository.save(this.toEntity(accountId, movementType, amount, accountInfo.amount()));
        this.accountJpaRepository.updateBalance(this.getNewBalance(amount, accountInfo.amount()), accountId);
    }

    @Override
    public MovementFilterResponse getMovements(final MovementFilterRequest request) {
        final var movements = this.movementJpaRepository.getMovements(request.getIdentification(), request.pageable());

        if (movements.isEmpty()) {
            throw new NotFoundDataException("No se encontraron movimientos");
        }

        return new MovementFilterResponse(movements.getContent(), movements.getTotalElements());
    }

    private MovementEntity toEntity(final UUID accountId, final MovementTypeEnum movementType, final BigDecimal amount,
                                    final BigDecimal balance) {
        final MovementEntity movementEntity = new MovementEntity();

        movementEntity.setCreatedAt(LocalDateTime.now());
        movementEntity.setMovementTypeId(this.getMovementTypeId(movementType));
        movementEntity.setAmount(amount);
        movementEntity.setAmountInitial(balance);
        movementEntity.setAccountId(accountId);
        movementEntity.setAmountBalance(this.getNewBalance(amount, balance));

        return movementEntity;
    }

    private void validateAmount(final BigDecimal amount) {
        if (amount.compareTo(BigDecimal.ZERO) == 0) {
            throw new InvalidDataException("El monto no puede ser 0");
        }
    }

    private MovementTypeEnum getMovementType(final BigDecimal amount) {
        return amount.compareTo(BigDecimal.ZERO) >= 0 ? MovementTypeEnum.CRE : MovementTypeEnum.DEB;
    }

    private void validateBalanceAmount(final MovementTypeEnum movementType, final BigDecimal amount,
                                       final BigDecimal balance, final UUID accountId) {
        if (MovementTypeEnum.DEB == movementType) {
            this.movementValidation.hasEnoughBalance(amount, balance, movementType);
            this.movementValidation.validateDailyDebitLimit(accountId, amount);
        }
    }

    private Short getMovementTypeId(final MovementTypeEnum movementType) {
        return this.movementTypeCache.getMovementTypeId(movementType);
    }

    private BigDecimal getNewBalance(final BigDecimal amount, final BigDecimal balance) {
        return balance.add(amount);
    }
}
