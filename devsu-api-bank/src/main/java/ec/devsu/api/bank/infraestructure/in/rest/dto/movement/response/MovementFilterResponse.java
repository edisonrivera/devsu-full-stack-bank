package ec.devsu.api.bank.infraestructure.in.rest.dto.movement.response;

import ec.devsu.api.bank.infraestructure.out.persistence.projection.movement.MovementInfoProjection;

import java.util.List;

public record MovementFilterResponse(List<MovementInfoProjection> movements, long totalMovements) {
}
