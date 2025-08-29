package com.jwtvalidator.integration;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;

import io.restassured.RestAssured;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class JwtValidationRestAssuredTests {

    @LocalServerPort
    private int port;

    @BeforeEach
    void setUp() {
        RestAssured.port = port;
    }

    @Test
    public void shouldReturnOkForValidToken() {
        String requestBody = "{\"jwt\":\"eyJhbGciOiJIUzI1NiJ9.eyJSb2xlIjoiQWRtaW4iLCJTZWVkIjoiNzg0MSIsIk5hbWUiOiJUb25pbmhvIEFyYXVqbyJ9.QY05sIjtrcJnP533kQNk8QXcaleJ1Q01jWY_ZzIZuAg\"}";

        given()
                .contentType("application/json")
                .body(requestBody)
                .when()
                .post("/api/v1/validate")
                .then()
                .statusCode(200)
                .body(equalTo("verdadeiro"));
    }

    @Test
    public void shouldReturnFalseForMalformedJwt() {
        String requestBody = "{\"jwt\":\"eyJhbGciOiJzI1NiJ9.dfsdfsfryJSr2xrIjoiQWRtaW4iLCJTZrkIjoiNzg0MSIsIk5hbrUiOiJUb25pbmhvIEFyYXVqbyJ9.QY05fsdfsIjtrcJnP533kQNk8QXcaleJ1Q01jWY_ZzIZuAg\"}";

        given()
                .contentType("application/json")
                .body(requestBody)
                .when()
                .post("/api/v1/validate")
                .then()
                .statusCode(400)
                .body(equalTo("falso"));
    }

    @Test
    public void shouldReturnFalseWhenNameContainsNumbers() {
        String requestBody = "{\"jwt\":\"eyJhbGciOiJIUzI1NiJ9.eyJSb2xlIjoiRXh0ZXJuYWwiLCJTZWVkIjoiODgwMzciLCJOYW1lIjoiTTRyaWEgT2xpdmlhIn0.6YD73XWZYQSSMDf6H0i3-kylz1-TY_Yt6h1cV2Ku-Qs\"}";

        given()
                .contentType("application/json")
                .body(requestBody)
                .when()
                .post("/api/v1/validate")
                .then()
                .statusCode(400)
                .body(equalTo("falso"));
    }

    @Test
    public void shouldReturnFalseWhenMoreThanThreeClaims() {
        String requestBody = "{\"jwt\":\"eyJhbGciOiJIUzI1NiJ9.eyJSb2xlIjoiTWVtYmVyIiwiT3JnIjoiQlIiLCJTZWVkIjoiMTQ2MjciLCJOYW1lIjoiVmFsZGlyIEFyYW5oYSJ9.cmrXV_Flm5mfdpfNUVopY_I2zeJUy4EZ4i3Fea98zvY\"}";

        given()
                .contentType("application/json")
                .body(requestBody)
                .when()
                .post("/api/v1/validate")
                .then()
                .statusCode(400)
                .body(equalTo("falso"));
    }

    @Test
    public void shouldReturnFalseWhenSeedIsNotPrime() {
        String requestBody = "{\"jwt\":\"eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJSb2xlIjoiQWRtaW4iLCJTZWVkIjoiNzg0MiIsIk5hbWUiOiJUb25pbmhvIEFyYXVqbyJ9.LOY0q1GZQXmRnixkJrpOD1M681aQ43aQHXIquD0rFzo\"}";

        given()
                .contentType("application/json")
                .body(requestBody)
                .when()
                .post("/api/v1/validate")
                .then()
                .statusCode(400)
                .body(equalTo("falso"));
    }
}
