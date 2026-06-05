package ec.devsu.api.bank.infraestructure.in.rest.controller;

import ec.devsu.api.bank.application.port.in.report.GenerateReportUseCase;
import ec.devsu.api.bank.application.port.in.report.PdfReportUseCase;
import ec.devsu.api.bank.infraestructure.in.rest.dto.common.request.PageableRequest;
import ec.devsu.api.bank.infraestructure.in.rest.dto.movement.response.MovementReportResponse;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/v1/api/reportes")
@CrossOrigin("http://localhost:4200")
@AllArgsConstructor
public class ReportController {
    private final GenerateReportUseCase generateReportUseCase;
    private final PdfReportUseCase pdfReportUseCase;

    @GetMapping
    public MovementReportResponse getReport(@RequestParam final String fecha, @ModelAttribute @Valid final PageableRequest page) {
        return this.generateReportUseCase.getReport(fecha, page);
    }

    @GetMapping("/pdf")
    public ResponseEntity<byte[]> getPdf(@RequestParam String fecha) {
        byte[] pdf = this.pdfReportUseCase.generatePdf(fecha);
        String filename = "reporte-%s%s".formatted(fecha.replace("/", "-"), ".pdf");
        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + filename + "\"")
                .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_PDF_VALUE)
                .body(pdf);
    }
}
