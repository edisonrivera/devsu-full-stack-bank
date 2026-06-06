package ec.devsu.api.bank.testcontainer;

import io.restassured.RestAssured;
import jakarta.annotation.PostConstruct;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.testcontainers.junit.jupiter.Testcontainers;
import org.testcontainers.mssqlserver.MSSQLServerContainer;
import org.testcontainers.utility.DockerImageName;

@Testcontainers(disabledWithoutDocker = true)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class BaseTestContainers {
    @LocalServerPort
    private int port;

    static final MSSQLServerContainer mssqlServerContainer;

    static {
        mssqlServerContainer = new MSSQLServerContainer(DockerImageName.parse("mcr.microsoft.com/mssql/server:2022-latest"))
                .acceptLicense()
                .withPassword("TestContainers!$")
                .withInitScript("init.sql")
                .withReuse(true);

        mssqlServerContainer.start();
    }

    @PostConstruct
    private void init() {
        RestAssured.baseURI = "http://localhost:" + port;
    }


    @DynamicPropertySource
    static void registerResourceServerIssuerProperty(DynamicPropertyRegistry registry) {
        registry.add("spring.datasource.url", mssqlServerContainer::getJdbcUrl);
        registry.add("spring.datasource.username", mssqlServerContainer::getUsername);
        registry.add("spring.datasource.password", mssqlServerContainer::getPassword);
        registry.add("spring.datasource.driver-class-name", () -> "com.microsoft.sqlserver.jdbc.SQLServerDriver");
    }
}