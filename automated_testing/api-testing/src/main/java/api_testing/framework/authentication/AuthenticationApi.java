package api_testing.framework.authentication;

import io.restassured.http.ContentType;
import io.restassured.response.Response;
import static io.restassured.RestAssured.given;

public class AuthenticationApi {

    private static final String BASE_URL = "https://restful-booker.herokuapp.com";
    private static final String AUTH_API = BASE_URL + "/auth";

    public static Response sendCredentials(AuthenticationCredentials credentials) {
        return given()
                .auth()
                .preemptive()
                .basic(credentials.getUsername(), credentials.getPassword())
                .body(credentials)
                .contentType(ContentType.JSON)
                .when()
                .post(AUTH_API);
    }

    public static String fetchToken() {
        AuthenticationCredentials credentials = AuthenticationCredentials.getCredentials();
        credentials.setUsername("admin");
        credentials.setPassword("password123");

        AuthenticationResponse authenticationResponse = AuthenticationApi
                .sendCredentials(credentials)
                .as(AuthenticationResponse.class);

        return authenticationResponse.getToken();
    }

}
