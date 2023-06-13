package ui_testing;


import com.codeborne.selenide.Configuration;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ui_testing.page.LoginPage;
import ui_testing.page.ProductsPage;


import static com.codeborne.selenide.Selenide.open;
import static org.assertj.core.api.Assertions.*;

public class LoginPageTest {
    @BeforeEach
    public void openLoginPage(){
        open("/");
    }
    @BeforeAll
    public static void ConfigureSelenide() {
        Configuration.browser = "chrome";
        Configuration.browserSize = "1920x1080";
        Configuration.holdBrowserOpen = true;
        Configuration.baseUrl = "https://www.saucedemo.com";
    }
    @Test
    public void givenCorrectCredentials_whenLogin_thenShouldSeeProductsTitle(){
        LoginPage loginPage = new LoginPage();

        loginPage.enterUsername("standard_user");
        loginPage.enterPassword("secret_sauce");
        loginPage.clickLoginButton();

        ProductsPage productsPage = new ProductsPage();
        assertThat(productsPage.getPageHeadingText()).isEqualTo("Products");
    }
    @Test
    public void givenIncorrectCredentials_whenLogin_thenShouldSeeErrorMessage(){
        LoginPage loginPage = new LoginPage();

        loginPage.enterUsername("standard_user");
        loginPage.enterPassword("secret-sauce");
        loginPage.clickLoginButton();

        assertThat(loginPage.getErrorMessage()).isEqualTo("Epic sadface: Username and password do not match any user in this service");


    }
    @Test
    public void givenLockedOut_whenLogin_thenShouldSeeLockedOutErrorMessage(){
        LoginPage loginPage = new LoginPage();

        loginPage.enterUsername("locked_out_user");
        loginPage.enterPassword("secret_sauce");
        loginPage.clickLoginButton();

        assertThat(loginPage.getErrorMessage()).isEqualTo("Epic sadface: Sorry, this user has been locked out.");
    }
    @Test
    public void givenOnlyPassword_whenLogin_thenShouldReturnUsernameRequired (){
        LoginPage loginPage = new LoginPage();


        loginPage.enterPassword("secret_sauce");
        loginPage.clickLoginButton();

        assertThat(loginPage.getErrorMessage()).isEqualTo("Epic sadface: Username is required");

    }
    @Test
    public void givenOnlyUsername_whenLogin_thenShouldReturnPasswordRequired (){
        LoginPage loginPage = new LoginPage();

        loginPage.enterUsername("locked_out_user");
        loginPage.clickLoginButton();

        assertThat(loginPage.getErrorMessage()).isEqualTo("Epic sadface: Password is required");

    }
}
