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
        LoginRequestData loginRequestData = new LoginRequestData("a", "a");

        given()
                .spec(requestSpecificationBff)
                .and().contentType(ContentType.JSON)
                .and().body(loginRequestData)
        .when()
                .post("/login")
        .then()
                .log().everything()
                .and().statusCode(HttpStatus.SC_FORBIDDEN)
                .and().body("message", equalTo("Could not login with these credentials"));
    }
}
