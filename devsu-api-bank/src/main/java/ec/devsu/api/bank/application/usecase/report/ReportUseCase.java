package ec.devsu.api.bank.application.usecase.report;

import ec.devsu.api.bank.application.port.in.report.GenerateReportUseCase;
import ec.devsu.api.bank.application.port.out.report.ReportRepositoryPort;
import ec.devsu.api.bank.infraestructure.in.rest.dto.report.response.ReportResponse;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class ReportUseCase implements GenerateReportUseCase {
    private final ReportRepositoryPort repositoryPort;

    @Override
    public List<ReportResponse> getReport(final String fecha) {
        return this.repositoryPort.getReport(fecha);
    }
}
