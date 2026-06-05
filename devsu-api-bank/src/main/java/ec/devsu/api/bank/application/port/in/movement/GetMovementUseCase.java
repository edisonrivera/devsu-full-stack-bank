package ec.devsu.api.bank.application.port.in.movement;

import ec.devsu.api.bank.infraestructure.in.rest.dto.movement.request.MovementFilterRequest;
import ec.devsu.api.bank.infraestructure.in.rest.dto.movement.response.MovementFilterResponse;

public interface GetMovementUseCase {
    MovementFilterResponse getMovements(MovementFilterRequest request);
}
