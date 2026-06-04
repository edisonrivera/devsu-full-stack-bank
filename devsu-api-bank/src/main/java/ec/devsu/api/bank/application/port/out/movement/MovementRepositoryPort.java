package ec.devsu.api.bank.application.port.out.movement;

import ec.devsu.api.bank.infraestructure.in.rest.dto.movement.request.MovementRequest;

public interface MovementRepositoryPort {
    void create(MovementRequest request);
}
