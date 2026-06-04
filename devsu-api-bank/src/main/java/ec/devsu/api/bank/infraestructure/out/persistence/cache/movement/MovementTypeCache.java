package ec.devsu.api.bank.infraestructure.out.persistence.cache.movement;

import ec.devsu.api.bank.domain.enums.MovementTypeEnum;
import ec.devsu.api.bank.infraestructure.out.persistence.entity.MovementTypeEntity;
import ec.devsu.api.bank.infraestructure.out.persistence.repository.MovementTypeJpaRepository;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.EnumMap;
import java.util.List;
import java.util.Map;

@Component
@RequiredArgsConstructor
public class MovementTypeCache {
    private final MovementTypeJpaRepository movementTypeJpaRepository;

    private Map<MovementTypeEnum, Short> movementTypeMap = new EnumMap<>(MovementTypeEnum.class);

    @PostConstruct
    public void init() {
        final List<MovementTypeEntity> movementTypeEntities = movementTypeJpaRepository.findAll();

        movementTypeEntities.forEach(movementTypeEntity -> {
            this.movementTypeMap.put(movementTypeEntity.getMnemonic(), movementTypeEntity.getMovementTypeId());
        });
    }

    public Short getMovementTypeId(MovementTypeEnum mnemonic) {
        return this.movementTypeMap.get(mnemonic);
    }
}
