package O02_apitests.helpers;

import O02_apitests.models.RegisterUserRequestData;
import helpers.TestHelper;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.specification.RequestSpecification;

import java.util.Map;

public class ApiHelper {

    public static RequestSpecification createRequestSpecification(String apiBasePath, Map<String, String> headers) {
        RequestSpecBuilder requestSpecBuilder = new RequestSpecBuilder();
        requestSpecBuilder.setBaseUri(apiBasePath);
        for (Map.Entry<String, String> header : headers.entrySet()) {
            requestSpecBuilder.addHeader(header.getKey(), header.getValue());
        }
        return requestSpecBuilder.build();
    }

    /**
     * Create a valid RegisterRequestData object with random unique data
     * @return RegisterRequestData object
     */
    public static RegisterUserRequestData createValidRegisterRequestData() {
        String uuid = TestHelper.getRandomUUIDAsString();
        return new RegisterUserRequestData(
                "API Test User " + uuid,
                "apitestuser-" + uuid + "@test.com",
                "password",
                "0612345678"
        );
    }

}
