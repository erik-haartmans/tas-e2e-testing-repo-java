package O01_uitests.helpers;

import O01_uitests.lib.pages.RegisterPageData;
import helpers.TestHelper;

public class UiTestHelper {

    public static RegisterPageData createValidRegisterPageData() {
        // create valid and unique registration data
        String uuid = TestHelper.getRandomUUIDAsString();
        return new RegisterPageData(
                "Test User " + uuid,
                "testuser" + uuid + "@test.com",
                uuid,
                uuid,
                "0612345678",
                true
        );
    }

}
