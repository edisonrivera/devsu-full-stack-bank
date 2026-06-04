package ec.devsu.api.bank.infraestructure.in.rest;

import ec.devsu.api.bank.domain.exception.InvalidDataException;
import ec.devsu.api.bank.domain.exception.NotFoundDataException;
import ec.devsu.api.bank.infraestructure.in.rest.dto.error.ErrorResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;
import org.springframework.web.servlet.resource.NoResourceFoundException;

import java.time.Instant;

@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(NotFoundDataException.class)
    public ResponseEntity<ErrorResponse> handleNotFound(final NotFoundDataException ex) {
        log.warn("Resource not found: {}", ex.getMessage());
        return this.build(HttpStatus.NOT_FOUND, ex.getMessage());
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorResponse> handleValidation(final MethodArgumentNotValidException ex) {
        final var message = ex.getBindingResult().getFieldErrors().stream()
                .findFirst()
                .map(FieldError::getDefaultMessage)
                .orElse("Validation failed");

        log.warn("Validation failed: {}", message);
        return this.build(HttpStatus.BAD_REQUEST, message);
    }

    @ExceptionHandler(InvalidDataException.class)
    public ResponseEntity<ErrorResponse> handleMissingParam(final InvalidDataException ex) {
        log.warn("Invalid information: {}", ex.getMessage());
        return this.build(HttpStatus.BAD_REQUEST, ex.getMessage());
    }

    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
    public ResponseEntity<ErrorResponse> handleTypeMismatch(final MethodArgumentTypeMismatchException ex) {
        log.warn("Type mismatch: {}", ex.getName());
        return this.build(HttpStatus.BAD_REQUEST, "%s%s".formatted(ex.getName(), "has invalid type"));
    }

    @ExceptionHandler(NoResourceFoundException.class)
    public ResponseEntity<ErrorResponse> handleNoResource(final NoResourceFoundException ex) {
        log.warn("No resource: {}", ex.getResourcePath());
        return this.build(HttpStatus.NOT_FOUND, "Resource not found");
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponse> handleGeneric(final Exception ex) {
        log.error("Unexpected error", ex);
        return this.build(HttpStatus.INTERNAL_SERVER_ERROR, "Internal server error");
    }

    private ResponseEntity<ErrorResponse> build(final HttpStatus status, final String message) {
        final var body = new ErrorResponse(message, status.value(), Instant.now());
        return ResponseEntity.status(status).body(body);
    }
}