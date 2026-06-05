package ec.devsu.api.bank.application.port.out.report;

import ec.devsu.api.bank.infraestructure.in.rest.dto.common.request.PageableRequest;
import ec.devsu.api.bank.infraestructure.in.rest.dto.movement.response.MovementReportResponse;

public interface ReportRepositoryPort {
    MovementReportResponse getReport(String fecha, PageableRequest page);

    byte[] generatePdf(String date);
}
