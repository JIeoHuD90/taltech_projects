package ui_testing;

import com.codeborne.selenide.Configuration;
import ui_testing.page.CartPage;
import ui_testing.page.LoginPage;

import static com.codeborne.selenide.Selenide.open;

public class BaseTest {
    public void openLoginPage() {
        open("/");
        LoginPage loginPage = new LoginPage();
        loginPage.enterUsername("standard_user");
        loginPage.enterPassword("secret_sauce");
        loginPage.clickLoginButton();
    }

    public static void ConfigureSelenide() {
        Configuration.browser = "chrome";
        Configuration.browserSize = "1920x1080";
        Configuration.holdBrowserOpen = true;
        Configuration.baseUrl = "https://www.saucedemo.com";

    }
}
