package ec.devsu.api.bank.application.usecase.report;

import ec.devsu.api.bank.application.port.in.report.GenerateReportUseCase;
import ec.devsu.api.bank.application.port.in.report.PdfReportUseCase;
import ec.devsu.api.bank.application.port.out.report.ReportRepositoryPort;
import ec.devsu.api.bank.infraestructure.in.rest.dto.common.request.PageableRequest;
import ec.devsu.api.bank.infraestructure.in.rest.dto.movement.response.MovementReportResponse;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class ReportUseCase implements GenerateReportUseCase, PdfReportUseCase {
    private final ReportRepositoryPort repositoryPort;

    @Override
    public MovementReportResponse getReport(final String fecha, final PageableRequest page) {
        return this.repositoryPort.getReport(fecha, page);
    }

    @Override
    public byte[] generatePdf(String date) {
        return this.repositoryPort.generatePdf(date);
    }
}
