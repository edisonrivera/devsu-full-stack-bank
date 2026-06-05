package ec.devsu.api.bank.infraestructure.out.pdf;


import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import ec.devsu.api.bank.domain.exception.GenerateFileException;
import ec.devsu.api.bank.infraestructure.out.persistence.projection.movement.MovementReportInfoProjection;
import lombok.extern.slf4j.Slf4j;

import java.io.ByteArrayOutputStream;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Slf4j
public class PdfReportBuilder {
    private static final DateTimeFormatter DATE_FMT = DateTimeFormatter.ofPattern("dd/MM/yyyy");

    private static final float[] COL_WIDTHS = {10f, 20f, 5f, 5f, 5f, 7f, 10f, 5f};

    private static final String[] HEADERS = {
            "Cliente", "Cuenta", "Tipo", "Saldo inicial",
            "Fecha", "Valor", "Saldo disponible", "Estado"
    };

    private static final BaseColor COLOR_HEADER = new BaseColor(61, 61, 184);
    private static final BaseColor COLOR_CREDIT = new BaseColor(232, 245, 233);
    private static final BaseColor COLOR_DEBIT = new BaseColor(255, 235, 238);
    private static final BaseColor COLOR_ROW_ALT = new BaseColor(248, 248, 252);

    private static final Font FONT_TITLE = new Font(Font.FontFamily.HELVETICA, 14, Font.BOLD);
    private static final Font FONT_SUBTITLE = new Font(Font.FontFamily.HELVETICA, 9, Font.NORMAL, BaseColor.DARK_GRAY);
    private static final Font FONT_HEADER = new Font(Font.FontFamily.HELVETICA, 8, Font.BOLD, BaseColor.WHITE);
    private static final Font FONT_CELL = new Font(Font.FontFamily.HELVETICA, 7, Font.NORMAL);
    private static final Font FONT_FOOTER = new Font(Font.FontFamily.HELVETICA, 7, Font.ITALIC, BaseColor.GRAY);

    private PdfReportBuilder() {
    }

    public static byte[] build(final List<MovementReportInfoProjection> movements, final String fecha) {
        try (var out = new ByteArrayOutputStream()) {
            final Document doc = new Document(PageSize.A4.rotate(), 20, 20, 30, 30);
            PdfWriter.getInstance(doc, out);
            doc.open();

            addTitle(doc, fecha);
            addTable(doc, movements);
            addFooter(doc, movements.size());

            doc.close();
            return out.toByteArray();

        } catch (Exception e) {
            log.error("Error to generate PDF", e);
            throw new GenerateFileException("Error generando PDF");
        }
    }

    private static void addTitle(final Document doc, final String fecha) throws DocumentException {
        final Paragraph title = new Paragraph("Reporte de Movimientos", FONT_TITLE);
        title.setAlignment(Element.ALIGN_CENTER);
        title.setSpacingAfter(4);
        doc.add(title);

        final Paragraph subtitle = new Paragraph("Período: %s".formatted(fecha), FONT_SUBTITLE);
        subtitle.setAlignment(Element.ALIGN_CENTER);
        subtitle.setSpacingAfter(14);
        doc.add(subtitle);
    }

    private static void addTable(final Document doc, final List<MovementReportInfoProjection> movements) throws DocumentException {
        final PdfPTable table = new PdfPTable(COL_WIDTHS.length);
        table.setWidthPercentage(100);
        table.setWidths(COL_WIDTHS);
        table.setSpacingBefore(0);

        addHeaders(table);

        for (int i = 0; i < movements.size(); i++) {
            addRow(table, movements.get(i), i % 2 != 0);
        }

        doc.add(table);
    }

    private static void addFooter(final Document doc, final int total) throws DocumentException {
        final String text = "Total de movimientos: %s | Generado: %s"
                .formatted(total, LocalDateTime.now().format(DateTimeFormatter.ofPattern("dd/MM/yyyy hh:mm:ss a")));
        Paragraph footer = new Paragraph(text, FONT_FOOTER);
        footer.setAlignment(Element.ALIGN_RIGHT);
        footer.setSpacingBefore(8);
        doc.add(footer);
    }

    private static void addHeaders(final PdfPTable table) {
        for (String h : HEADERS) {
            PdfPCell cell = new PdfPCell(new Phrase(h, FONT_HEADER));
            cell.setBackgroundColor(COLOR_HEADER);
            cell.setHorizontalAlignment(Element.ALIGN_CENTER);
            cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
            cell.setPadding(6);
            table.addCell(cell);
        }
    }

    private static void addRow(final PdfPTable table, final MovementReportInfoProjection m, final boolean alt) {
        final BaseColor rowColor = alt ? COLOR_ROW_ALT : BaseColor.WHITE;

        addCell(table, m.personName(), rowColor, Element.ALIGN_LEFT);
        addCell(table, m.accountNumber(), rowColor, Element.ALIGN_CENTER);
        addCell(table, m.accountType(), rowColor, Element.ALIGN_CENTER);
        addCell(table, formatMoney(m.amountInitial()), rowColor, Element.ALIGN_RIGHT);
        addCell(table, m.createdAt().format(DATE_FMT), rowColor, Element.ALIGN_CENTER);
        addAmountCell(table, m.amount());
        addCell(table, formatMoney(m.amountBalance()), rowColor, Element.ALIGN_RIGHT);
        addCell(table, String.valueOf(m.status()), rowColor, Element.ALIGN_CENTER);
    }

    private static void addCell(final PdfPTable table, final String text, final BaseColor bg, final int alignment) {
        final PdfPCell cell = new PdfPCell(new Phrase(text, FONT_CELL));
        cell.setBackgroundColor(bg);
        cell.setHorizontalAlignment(alignment);
        cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
        cell.setPadding(4);
        table.addCell(cell);
    }

    private static void addAmountCell(final PdfPTable table, final BigDecimal amount) {
        final boolean isCredit = amount.compareTo(BigDecimal.ZERO) >= 0;
        final String text = (isCredit ? "+" : "") + formatMoney(amount);

        final PdfPCell cell = new PdfPCell(new Phrase(text, FONT_CELL));
        cell.setBackgroundColor(isCredit ? COLOR_CREDIT : COLOR_DEBIT);
        cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
        cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
        cell.setPadding(4);
        table.addCell(cell);
    }

    private static String formatMoney(final BigDecimal value) {
        return String.format("$%,.2f", value);
    }
}