package O02_apitests.O01_registertests;

import O02_apitests.lib.BaseApiTest;
import O02_apitests.models.ErrorResponseData;
import io.restassured.response.ValidatableResponse;
import org.apache.http.HttpStatus;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static io.restassured.RestAssured.given;
import static org.junit.jupiter.api.Assertions.*;

public class InvalidRegistrationTests extends BaseApiTest {

    @Test
    public void anErrorShouldBeReturnedWhenNoNameIsPassed() {
        registerRequestData.setName("");
        ValidatableResponse response =
                given()
                    .spec(rsRegisterUser)
                    .and().body(registerRequestData)
                .when()
                    .post()
                .then()
                    .log().everything();

        String expectedMessage = "Name is required";
        assertAll("",
            () -> assertEquals(HttpStatus.SC_BAD_REQUEST, response.extract().statusCode(), "Status code should be 400"),
            () -> assertTrue(response.extract().body().as(ErrorResponseData.class).getMessage().contains(expectedMessage),
                    "Message should contain '" + expectedMessage + "'")
        );
    }

    @Test
    public void anErrorShouldBeReturnedWhenNoEmailIsPassed() {
        registerRequestData.setEmail("");
        ValidatableResponse response =
                given()
                        .spec(rsRegisterUser)
                        .and().body(registerRequestData)
                        .when()
                        .post()
                        .then()
                        .log().everything();

        String expectedMessage = "Email is required";
        assertAll("",
                () -> assertEquals(HttpStatus.SC_BAD_REQUEST, response.extract().statusCode(), "Status code should be 400"),
                () -> assertTrue(response.extract().body().as(ErrorResponseData.class).getMessage().contains(expectedMessage),
                        "Message should contain '" + expectedMessage + "'")
        );
    }

    @Test
    public void anErrorShouldBeReturnedWhenNoPasswordIsPassed() {
        registerRequestData.setPassword("");
        ValidatableResponse response =
                given()
                        .spec(rsRegisterUser)
                        .and().body(registerRequestData)
                        .when()
                        .post()
                        .then()
                        .log().everything();

        String expectedMessage = "Password is required";
        assertAll("",
                () -> assertEquals(HttpStatus.SC_BAD_REQUEST, response.extract().statusCode(), "Status code should be 400"),
                () -> assertTrue(response.extract().body().as(ErrorResponseData.class).getMessage().contains(expectedMessage),
                        "Message should contain '" + expectedMessage + "'")
        );
    }

    @ParameterizedTest
    @ValueSource(strings = {
            "test",
            "test@",
            "test@.",
            "test@.com",
            "test@com",
            "test.com",
            "test@com.",
    })
    public void anErrorShouldBeReturnedWhenAnInvalidEmailIsPassed(String email) {
        registerRequestData.setEmail(email);
        ValidatableResponse response =
                given()
                        .spec(rsRegisterUser)
                        .and().body(registerRequestData)
                        .when()
                        .post()
                        .then()
                        .log().everything();

        String expectedMessage = "Email is not valid";
        assertAll("",
                () -> assertEquals(HttpStatus.SC_BAD_REQUEST, response.extract().statusCode(), "Status code should be 400"),
                () -> assertTrue(response.extract().body().as(ErrorResponseData.class).getMessage().contains(expectedMessage),
                        "Message should contain '" + expectedMessage + "'")
        );
    }

}
