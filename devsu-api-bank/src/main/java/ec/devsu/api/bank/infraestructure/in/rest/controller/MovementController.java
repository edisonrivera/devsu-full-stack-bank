package ec.devsu.api.bank.infraestructure.in.rest.controller;

import ec.devsu.api.bank.application.port.in.movement.CreateMovementUseCase;
import ec.devsu.api.bank.application.port.in.movement.GetMovementUseCase;
import ec.devsu.api.bank.infraestructure.in.rest.dto.movement.request.MovementFilterRequest;
import ec.devsu.api.bank.infraestructure.in.rest.dto.movement.request.MovementRequest;
import ec.devsu.api.bank.infraestructure.in.rest.dto.movement.response.MovementFilterResponse;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/v1/api/movements")
@CrossOrigin("http://localhost:4200")
@AllArgsConstructor
public class MovementController {
    private final CreateMovementUseCase createMovementUseCase;
    private final GetMovementUseCase getMovementUseCase;

    @PostMapping
    public void create(@RequestBody @Valid final MovementRequest request) {
        this.createMovementUseCase.create(request);
    }

    @PostMapping("/filter")
    public MovementFilterResponse filter(@RequestBody @Valid final MovementFilterRequest request) {
        return this.getMovementUseCase.getMovements(request);
    }
}
