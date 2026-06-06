package ec.devsu.api.bank.infraestructure.in.rest.controller;

import ec.devsu.api.bank.infraestructure.in.rest.dto.movement.request.MovementRequest;
import ec.devsu.api.bank.testcontainer.BaseTestContainers;
import io.restassured.RestAssured;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;

import java.math.BigDecimal;

@Order(3)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class MovementControllerTest extends BaseTestContainers {
    @LocalServerPort
    private int port;

    @BeforeEach
    void setup() {
        RestAssured.port = port;
    }


    @Test
    @Order(1)
    void register() {
        final MovementRequest movementRequest = new MovementRequest("123123123123", new BigDecimal("100"));

        RestAssured.given().contentType(MediaType.APPLICATION_JSON_VALUE)
                .body(movementRequest)
                .when().post("/v1/api/movements")
                .then()
                .statusCode(HttpStatus.BAD_REQUEST.value());
    }
}
