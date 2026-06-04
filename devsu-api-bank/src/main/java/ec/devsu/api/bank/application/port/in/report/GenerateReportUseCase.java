package ec.devsu.api.bank.application.port.in.report;

import ec.devsu.api.bank.infraestructure.in.rest.dto.report.response.ReportResponse;

import java.util.List;

public interface GenerateReportUseCase {
    List<ReportResponse> getReport(String fecha);
}
