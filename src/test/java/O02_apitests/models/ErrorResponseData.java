package O02_apitests.models;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ErrorResponseData {
    private String message;
    private int statusCode;
}

