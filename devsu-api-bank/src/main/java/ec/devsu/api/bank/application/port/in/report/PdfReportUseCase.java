package ec.devsu.api.bank.application.port.in.report;

public interface PdfReportUseCase {
    byte[] generatePdf(String date);
}
