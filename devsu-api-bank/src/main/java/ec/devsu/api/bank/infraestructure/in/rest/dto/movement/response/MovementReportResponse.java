package ec.devsu.api.bank.infraestructure.in.rest.dto.movement.response;

import ec.devsu.api.bank.infraestructure.out.persistence.projection.movement.MovementReportInfoProjection;

import java.util.List;

public record MovementReportResponse(List<MovementReportInfoProjection> movementsReport, long totalMovementsReport) {
}
