package ec.devsu.api.bank.infraestructure.in.rest.dto.error;


import java.time.Instant;

public record ErrorResponse(String message, Integer status, Instant timestamp) {
}
