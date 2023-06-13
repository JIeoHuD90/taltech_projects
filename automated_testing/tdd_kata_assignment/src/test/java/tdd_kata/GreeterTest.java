package tdd_kata;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.Arrays;

public class GreeterTest {
    Greeter greeter;

    @BeforeEach
    public void initializeGreeter() {
        greeter = new Greeter();
    }

    @Test
    public void givenNameIsProvided_whenGreetMethodIsCalled_thenGreetingShouldContainName() {
        // Arrange
        String name = "Fluffy Buddy";
        String expectedGreeting = String.format("Hello, %s.", name);

        // Act
        String actualGreeting = greeter.greet(name);

        // Assert
        Assertions.assertThat(actualGreeting).isEqualTo(expectedGreeting);
    }

    @ParameterizedTest
    @ValueSource(strings = {"", " ", "\n", "\t"})
    public void givenNameIsEmpty_whenGreeterCalled_thenGreeterContainsAStdin(String name) {
        String excpectedGreeting = "Hello, my friend.";

        String actualGreeting = greeter.greet(name);

        Assertions.assertThat(actualGreeting).isEqualTo(excpectedGreeting);

    }

    @Test
    public void givenNameShouted_whenGreeterCalled_thenGreetShouldShoutedHello() {
        String name = "DISHONORED";

        String actualGreeting = greeter.greet(name);

        Assertions.assertThat(actualGreeting).startsWith("HELLO");
    }

    @Test
    public void givenNameShouted_whenGreeterCalled_thenGreetShouldEndExclamation() {
        String name = "DISHONORED";

        String actualGreeting = greeter.greet(name);

        Assertions.assertThat(actualGreeting).endsWith("!");
    }

    @Test
    public void givenNameIsArrayOfTwoShouldReturnNameAndName() {
        String[] name = {"Jill", "Jane"};
        String expectedGreeting = "Hello, Jill and Jane.";

        String actualGreeting = greeter.greet(Arrays.toString(name));

        Assertions.assertThat(actualGreeting).isEqualTo(expectedGreeting);
    }

    @Test
    public void givenNameIsArrayOfThreeAndMoreShouldReturnGreetingWithOxfordCommaAnd() {
        String[] name = {"Amy", "Brian", "Charlotte", "Dean"};
        String expectedGreeting = "Hello, Amy, Brian, Charlotte, and Dean.";

        String actualGreeting = greeter.greet(Arrays.toString(name));

        Assertions.assertThat(actualGreeting).isEqualTo(expectedGreeting);
    }

    @Test
    public void givenNameIsArrayOfThreeAndMoreMixedWithShoutedShouldReturnGreetingWithOxfordCommaAnd() {
        String[] name = {"Amy", "BRIAN", "Charlotte"};
        String expectedGreeting = "Hello, Amy and Charlotte. AND HELLO, BRIAN!";

        String actualGreeting = greeter.greet(Arrays.toString(name));

        Assertions.assertThat(actualGreeting).isEqualTo(expectedGreeting);
    }

    @Test
    public void givenNameWithCommasGreeterShouldReturnAsSeparateInput() {
        String[] name = {"Bob", "Charlie, Dianne"};
        String expectedGreeting = "Hello, Bob, Charlie, and Dianne.";

        String actualGreeting = greeter.greet(Arrays.toString(name));

        Assertions.assertThat(actualGreeting).isEqualTo(expectedGreeting);
    }

    @Test
    public void doubleQuotesShouldReturnSameAsTwoNamesButInTheEndOfTheString() {
        String[] name = {"Bob", "\"Charlie, Dianne\""};
        String expectedGreeting = "Hello, Bob and Charlie, Dianne.";

        String  actualGreeting =  greeter.greet(Arrays.toString(name));

        Assertions.assertThat(actualGreeting).isEqualTo(expectedGreeting);
    }


}
