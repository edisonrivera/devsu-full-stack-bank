package ec.devsu.api.bank.infraestructure.in.rest.controller;

import ec.devsu.api.bank.application.port.in.movement.CreateMovementUseCase;
import ec.devsu.api.bank.infraestructure.in.rest.dto.movement.request.MovementRequest;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/v1/api/movements")
@AllArgsConstructor
public class MovementController {
    private final CreateMovementUseCase createMovementUseCase;

    @PostMapping
    public void create(@RequestBody @Valid final MovementRequest request) {
        this.createMovementUseCase.create(request);
    }
}
