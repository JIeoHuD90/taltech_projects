package api_testing.framework.authentication;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.github.javafaker.Faker;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonAutoDetect
@Builder
public class AuthenticationCredentials {
    private String username;
    private String password;

    public static AuthenticationCredentials getCredentials() {
        Faker faker = new Faker();

        return AuthenticationCredentials.builder()
                .username(faker.name().username())
                .password(faker.hobbit().character())
                .build();
    }
}
