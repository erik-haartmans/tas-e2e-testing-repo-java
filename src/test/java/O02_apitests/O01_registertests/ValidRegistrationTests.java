package O02_apitests.O01_registertests;

import O02_apitests.lib.BaseApiTest;
import O02_apitests.models.RegisterUserResponseData;
import io.restassured.response.ValidatableResponse;
import org.apache.http.HttpStatus;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.UUID;
import java.util.stream.Stream;

import static io.restassured.RestAssured.given;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class ValidRegistrationTests extends BaseApiTest {

    @Test
    public void itShouldBePossibleToRegisterWithAllDataFilledAndValid() {
        ValidatableResponse response =
                given()
                    .spec(rsRegisterUser)
                    .and().body(registerRequestData)
                .when()
                    .post()
                .then()
                    .log().everything();

        assertAll("",
            () -> assertEquals(HttpStatus.SC_OK, response.extract().statusCode(), "Status code should be 200"),
            () -> assertEquals(
                    registerRequestData.getName(),
                    response.extract().body().as(RegisterUserResponseData.class).getName(),
                    "Name should be equal to " + registerRequestData.getName()),
                () -> assertEquals(
                        registerRequestData.getEmail(),
                        response.extract().body().as(RegisterUserResponseData.class).getEmail(),
                        "Email should be equal to " + registerRequestData.getEmail()),
                () -> assertEquals(
                        registerRequestData.getPassword(),
                        response.extract().body().as(RegisterUserResponseData.class).getPassword(),
                        "Password should be equal to " + registerRequestData.getPassword()),
                () -> assertEquals(
                        registerRequestData.getPhoneNumber(),
                        response.extract().body().as(RegisterUserResponseData.class).getPhoneNumber(),
                        "Phone number should be equal to " + registerRequestData.getPhoneNumber())
        );

    }

    @Test
    public void itShouldBePossibleToRegisterMinimalData() {
        registerRequestData.setPhoneNumber("");
        given()
                .spec(rsRegisterUser)
                .and().body(registerRequestData)
                .when()
                .post()
                .then()
                .log().everything()
                .and().statusCode(HttpStatus.SC_OK);
    }


    /**
     * This method creates a set of unique and valid emails which is used for the next ParameterizedTest
     * @return stream of valid emails
     */
    private static Stream<Arguments> provideValidEmails() {
        String uniqueEmailPart = UUID.randomUUID().toString();
        return Stream.of(
                Arguments.of(uniqueEmailPart.toLowerCase() + "-lc@testingemails.com")
                , Arguments.of(uniqueEmailPart.toUpperCase() + "-UC@testingemails.com")
                , Arguments.of(uniqueEmailPart + "-tlduc@testingemails.COM")
                , Arguments.of(uniqueEmailPart + "-duc@TESTINGEMAILS.com")
                , Arguments.of(uniqueEmailPart + "@TESTINGEMAILS.co")
                , Arguments.of(uniqueEmailPart + "@testingemails.abcdef")
        );
    }

    @ParameterizedTest(name="{index} It should be possible to register with valid email {0}")
    @MethodSource("provideValidEmails")
    public void itShouldBePossibleToRegisterWithValidEmail(String validEmail) {
        registerRequestData.setEmail(validEmail);
        given()
                .spec(rsRegisterUser)
                .and().body(registerRequestData)
                .when()
                .post()
                .then()
                .log().everything()
                .and().statusCode(HttpStatus.SC_OK);
    }

}
