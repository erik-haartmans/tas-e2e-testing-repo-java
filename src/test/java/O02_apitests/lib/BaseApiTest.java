package O02_apitests.lib;

import O02_apitests.helpers.ApiHelper;
import O02_apitests.models.RegisterUserRequestData;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import org.junit.jupiter.api.BeforeEach;

import java.util.HashMap;
import java.util.Map;

public class BaseApiTest {

    private final String BASE_URL = "https://tas.bff.staging.polteq-testing.com";

    protected RequestSpecification rsRegisterUser;
    protected RequestSpecification rsLogin;
    protected RegisterUserRequestData registerRequestData;

    @BeforeEach
    public void beforeEachTest() {
        // create default request specifications
        Map<String, String> headers = new HashMap<>();

        rsRegisterUser = ApiHelper.createRequestSpecification(BASE_URL, headers);
        rsRegisterUser.basePath("/register-user").contentType(ContentType.JSON);

        rsLogin = ApiHelper.createRequestSpecification(BASE_URL, headers);
        rsLogin.basePath("/login").contentType(ContentType.JSON);

        // create a valid RegisterRequestData object
        registerRequestData = ApiHelper.createValidRegisterRequestData();
    }

}
