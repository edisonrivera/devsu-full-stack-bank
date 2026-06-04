package ec.devsu.api.bank.infraestructure.out.persistence.adapter;

import ec.devsu.api.bank.application.port.out.report.ReportRepositoryPort;
import ec.devsu.api.bank.domain.exception.NotFoundDataException;
import ec.devsu.api.bank.infraestructure.in.rest.dto.report.request.DateRange;
import ec.devsu.api.bank.infraestructure.in.rest.dto.report.response.ReportResponse;
import ec.devsu.api.bank.infraestructure.out.persistence.repository.MovementJpaRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class ReportPersistenceAdapter implements ReportRepositoryPort {
    private final MovementJpaRepository movementJpaRepository;

    @Override
    public List<ReportResponse> getReport(final String fecha) {
        final var range = DateRange.parse(fecha);

        final List<ReportResponse> movements = this.movementJpaRepository.reportMovements(range.start(), range.end());

        if (movements.isEmpty()) {
            throw new NotFoundDataException("No se encontraron movimientos");
        }

        return movements;
    }
}
