package ec.devsu.api.bank.application.usecase.movement;

import ec.devsu.api.bank.application.port.in.movement.CreateMovementUseCase;
import ec.devsu.api.bank.application.port.in.movement.GetMovementUseCase;
import ec.devsu.api.bank.application.port.out.movement.MovementRepositoryPort;
import ec.devsu.api.bank.infraestructure.in.rest.dto.movement.request.MovementFilterRequest;
import ec.devsu.api.bank.infraestructure.in.rest.dto.movement.request.MovementRequest;
import ec.devsu.api.bank.infraestructure.in.rest.dto.movement.response.MovementFilterResponse;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class MovementUseCase implements CreateMovementUseCase, GetMovementUseCase {
    private final MovementRepositoryPort movementRepositoryPort;

    @Override
    public void create(final MovementRequest request) {
        this.movementRepositoryPort.create(request);
    }

    @Override
    public MovementFilterResponse getMovements(final MovementFilterRequest request) {
        return this.movementRepositoryPort.getMovements(request);
    }
}
