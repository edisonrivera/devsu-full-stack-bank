package ec.devsu.api.bank.application.port.in.movement;

import ec.devsu.api.bank.infraestructure.in.rest.dto.movement.request.MovementRequest;

public interface CreateMovementUseCase {
    void create(MovementRequest request);
}
