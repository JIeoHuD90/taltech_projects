package api_testing.framework.authentication;
import lombok.Data;
import org.apache.http.HttpStatus;


@Data
public class AuthenticationResponse {
    private String token;
    private String reason;

}
