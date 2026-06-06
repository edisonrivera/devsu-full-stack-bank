package ec.devsu.api.bank.infraestructure.in.rest.controller;

import ec.devsu.api.bank.domain.enums.AccountTypeEnum;
import ec.devsu.api.bank.infraestructure.in.rest.dto.account.request.AccountRequest;
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

@Order(2)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class AccountControllerTest extends BaseTestContainers {
    @LocalServerPort
    private int port;

    @BeforeEach
    void setup() {
        RestAssured.port = port;
    }


    @Test
    @Order(1)
    void registerAccount() {
        final AccountRequest accountRequest = new AccountRequest(AccountTypeEnum.AHORR, new BigDecimal("100"), "1752774305");

        RestAssured.given().contentType(MediaType.APPLICATION_JSON_VALUE)
                .body(accountRequest)
                .when().post("/v1/api/accounts")
                .then()
                .statusCode(HttpStatus.CREATED.value());
    }
}
