package ec.devsu.api.bank.application.port.in.report;

import ec.devsu.api.bank.infraestructure.in.rest.dto.common.request.PageableRequest;
import ec.devsu.api.bank.infraestructure.in.rest.dto.movement.response.MovementReportResponse;

public interface GenerateReportUseCase {
    MovementReportResponse getReport(String fecha, PageableRequest page);
}
