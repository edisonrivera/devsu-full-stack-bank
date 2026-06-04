package ec.devsu.api.bank.application.usecase.movement;

import ec.devsu.api.bank.application.port.in.movement.CreateMovementUseCase;
import ec.devsu.api.bank.application.port.out.movement.MovementRepositoryPort;
import ec.devsu.api.bank.infraestructure.in.rest.dto.movement.request.MovementRequest;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class MovementUseCase implements CreateMovementUseCase {
    private final MovementRepositoryPort movementRepositoryPort;

    @Override
    public void create(final MovementRequest request) {
        this.movementRepositoryPort.create(request);
    }
}
