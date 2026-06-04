package ec.devsu.api.bank.infraestructure.in.rest.dto.report.request;

import ec.devsu.api.bank.domain.exception.InvalidDataException;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public record DateRange(LocalDateTime start, LocalDateTime end) {
    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("dd/MM/yyyy");

    public static DateRange parse(final String value) {
        final var parts = value.split("-");
        if (parts.length != 2) {
            throw new InvalidDataException("Fecha debe tener el formato dd/MM/yyyy-dd/MM/yyyy");
        }

        try {
            final var start = LocalDate.parse(parts[0].trim(), FORMATTER);
            final var end = LocalDate.parse(parts[1].trim(), FORMATTER);
            return new DateRange(start.atStartOfDay(), end.plusDays(1).atStartOfDay());
        } catch (final DateTimeParseException ex) {
            throw new InvalidDataException("Fecha tiene un formato inválido");
        }
    }
}