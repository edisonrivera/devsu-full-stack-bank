package ec.devsu.api.bank.application.port.out.report;

import ec.devsu.api.bank.infraestructure.in.rest.dto.report.response.ReportResponse;

import java.util.List;

public interface ReportRepositoryPort {
    List<ReportResponse> getReport(String fecha);
}
