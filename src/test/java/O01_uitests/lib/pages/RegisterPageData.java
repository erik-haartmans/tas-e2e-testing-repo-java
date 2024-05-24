package O01_uitests.lib.pages;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class RegisterPageData {
    private String name;
    private String email;
    private String password;
    private String passwordCheck;
    private String phoneNumber;
    private boolean terms;
}
