package ec.devsu.api.bank.infraestructure.in.rest.controller;


import ec.devsu.api.bank.domain.enums.GenreEnum;
import ec.devsu.api.bank.infraestructure.in.rest.dto.client.request.ClientRequest;
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

@Order(1)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class ClientControllerTest extends BaseTestContainers {
    @LocalServerPort
    private int port;

    @BeforeEach
    void setup() {
        RestAssured.port = port;
    }


    @Test
    @Order(1)
    void register() {
        final ClientRequest clientRequest = new ClientRequest("Edison Rivera", GenreEnum.M, (short) 18, "1752774305",
                "Santigo Roldos", "0961731484", "Test123123!$");

        RestAssured.given().contentType(MediaType.APPLICATION_JSON_VALUE)
                .body(clientRequest)
                .log().body()
                .when().post("/v1/api/clients")
                .then()
                .log().body()
                .statusCode(HttpStatus.CREATED.value())
                .extract().response();
    }
}
