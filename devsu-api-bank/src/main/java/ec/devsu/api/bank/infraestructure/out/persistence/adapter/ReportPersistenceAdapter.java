package ec.devsu.api.bank.infraestructure.out.persistence.adapter;

import ec.devsu.api.bank.application.port.out.report.ReportRepositoryPort;
import ec.devsu.api.bank.domain.exception.NotFoundDataException;
import ec.devsu.api.bank.infraestructure.in.rest.dto.common.request.PageableRequest;
import ec.devsu.api.bank.infraestructure.in.rest.dto.movement.response.MovementReportResponse;
import ec.devsu.api.bank.infraestructure.in.rest.dto.report.request.DateRange;
import ec.devsu.api.bank.infraestructure.out.pdf.PdfReportBuilder;
import ec.devsu.api.bank.infraestructure.out.persistence.projection.movement.MovementReportInfoProjection;
import ec.devsu.api.bank.infraestructure.out.persistence.repository.MovementJpaRepository;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class ReportPersistenceAdapter implements ReportRepositoryPort {
    private final MovementJpaRepository movementJpaRepository;

    @Override
    public MovementReportResponse getReport(final String fecha, final PageableRequest page) {
        final var range = DateRange.parse(fecha);

        final Page<MovementReportInfoProjection> movements = this.movementJpaRepository.reportMovements(range.start(),
                range.end(), page.pageable());

        if (movements.isEmpty()) {
            throw new NotFoundDataException("No se encontraron movimientos");
        }

        return new MovementReportResponse(movements.getContent(), movements.getTotalElements());
    }

    @Override
    public byte[] generatePdf(final String date) {
        final var range = DateRange.parse(date);

        List<MovementReportInfoProjection> movements = this.movementJpaRepository.reportMovements(range.start(), range.end());
        if (movements.isEmpty()) {
            throw new NotFoundDataException("No se encontraron movimientos para el rango indicado");
        }
        return PdfReportBuilder.build(movements, date);
    }
}
