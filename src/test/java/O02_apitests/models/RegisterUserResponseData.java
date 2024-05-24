package O02_apitests.models;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class RegisterUserResponseData {
    private String name;
    private String email;
    private String password;
    private String phoneNumber;
}
