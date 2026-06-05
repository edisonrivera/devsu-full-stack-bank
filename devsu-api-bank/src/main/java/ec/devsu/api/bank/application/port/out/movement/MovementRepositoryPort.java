package ec.devsu.api.bank.application.port.out.movement;

import ec.devsu.api.bank.infraestructure.in.rest.dto.movement.request.MovementFilterRequest;
import ec.devsu.api.bank.infraestructure.in.rest.dto.movement.request.MovementRequest;
import ec.devsu.api.bank.infraestructure.in.rest.dto.movement.response.MovementFilterResponse;

public interface MovementRepositoryPort {
    void create(MovementRequest request);

    MovementFilterResponse getMovements(MovementFilterRequest request);
}
