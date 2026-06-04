package ec.devsu.api.bank.infraestructure.in.rest.controller;

import ec.devsu.api.bank.application.port.in.report.GenerateReportUseCase;
import ec.devsu.api.bank.infraestructure.in.rest.dto.report.response.ReportResponse;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/v1/api/reportes")
@AllArgsConstructor
public class ReportController {
    private final GenerateReportUseCase generateReportUseCase;

    @GetMapping
    public List<ReportResponse> getReport(@RequestParam final String fecha) {
        return this.generateReportUseCase.getReport(fecha);
    }
}
