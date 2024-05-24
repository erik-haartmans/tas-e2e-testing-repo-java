package O02_apitests;

import O02_apitests.lib.BaseApiTest;
import O02_apitests.models.LoginRequestData;
import io.restassured.http.ContentType;
import org.apache.http.HttpStatus;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

public class LoginTests extends BaseApiTest {

    @Test
    public void itShouldBePossibleToLoginWithValidCredentials() {
        given()
                .spec(rsRegisterUser)
                .and().body(registerRequestData)
                .when()
                .post()
                .then()
                .log().everything();

        LoginRequestData loginRequestData = new LoginRequestData(
                registerRequestData.getEmail(),
                registerRequestData.getPassword());

        given()
                .spec(rsLogin)
                .and().contentType(ContentType.JSON)
                .and().body(loginRequestData)
        .when()
                .post()
        .then()
                .log().everything()
                .and().statusCode(HttpStatus.SC_OK);
    }
}
