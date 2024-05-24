package O02_apitests.models;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class LoginRequestData {
    private String email;
    private String password;
}
