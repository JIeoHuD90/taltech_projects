package api_testing.framework;

import api_testing.framework.authentication.AuthenticationApi;
import api_testing.framework.authentication.AuthenticationCredentials;
import api_testing.framework.authentication.AuthenticationResponse;
import io.restassured.response.Response;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class AuthenticationTests {

    @Test
    public void postAuthenticationWithCorrectCredentialsShouldNOTReturnBadCredentials() {
        AuthenticationCredentials credentials = AuthenticationCredentials.getCredentials();
        credentials.setUsername("admin");
        credentials.setPassword("password123");

        AuthenticationResponse authenticationResponse = AuthenticationApi
                .sendCredentials(credentials)
                .as(AuthenticationResponse.class);
        assertThat(authenticationResponse.getReason()).isNotEqualTo("Bad credentials");


    }
    @Test
    public void postAuthenticationWithCorrectCredentialsShouldReturnBadCredentials() {
        AuthenticationCredentials credentials = AuthenticationCredentials.getCredentials();
        credentials.setUsername("admi");
        credentials.setPassword("password12");

        AuthenticationResponse authenticationResponse = AuthenticationApi
                .sendCredentials(credentials)
                .as(AuthenticationResponse.class);
        assertThat(authenticationResponse.getReason()).isEqualTo("Bad credentials");

    }

    @Test
    public void postAuthenticationWithCorrectCredentialsShouldReturnToken() {
        AuthenticationCredentials credentials = AuthenticationCredentials.getCredentials();
        credentials.setUsername("admin");
        credentials.setPassword("password123");

        AuthenticationResponse authenticationResponse = AuthenticationApi
                .sendCredentials(credentials)
                .as(AuthenticationResponse.class);

        assertThat(authenticationResponse.getToken()).isNotNull();
    }

}
