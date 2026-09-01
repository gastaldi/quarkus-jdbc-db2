package io.quarkiverse.jdbc.db2.it;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.is;

import org.junit.jupiter.api.Test;

import io.quarkus.test.junit.QuarkusTest;

@QuarkusTest
public class JdbcDb2ResourceTest {

    @Test
    public void testHelloEndpoint() {
        given()
                .when().get("/jdbc-db2")
                .then()
                .statusCode(200)
                .body(is("Hello jdbc-db2"));
    }
}
