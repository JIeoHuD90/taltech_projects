package ui_testing;

import com.codeborne.selenide.Configuration;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ui_testing.page.LoginPage;
import ui_testing.page.ProductsPage;

import static com.codeborne.selenide.Selenide.open;

public class ProductsPageTest {
    @BeforeEach
    public void openLoginPage() {
        open("/");
        LoginPage loginPage = new LoginPage();
        loginPage.enterUsername("standard_user");
        loginPage.enterPassword("secret_sauce");
        loginPage.clickLoginButton();
    }

    @BeforeAll
    public static void ConfigureSelenide() {
        Configuration.browser = "chrome";
        Configuration.browserSize = "1920x1080";
        Configuration.holdBrowserOpen = true;
        Configuration.baseUrl = "https://www.saucedemo.com";

    }

    @Test
    public void getFirstItemPrice_ShouldReturnCorrectPrice() {
        ProductsPage productsPage = new ProductsPage();
        Assertions.assertThat(productsPage.getFirstItemPrice()).isEqualTo("$29.99");
    }

    @Test
    public void sortItemsByPrice_LowestToHighest_ShouldReturnLowestPrice() {
        ProductsPage productsPage = new ProductsPage();
        productsPage.clickSortItems(2);
        Assertions.assertThat(productsPage.getFirstItemPrice()).isEqualTo("$7.99");
    }

    @Test
    public void sortItemsByPrice_HighestToLowest_ShouldReturnHighestPrice() {
        ProductsPage productsPage = new ProductsPage();
        productsPage.clickSortItems(3);
        Assertions.assertThat(productsPage.getFirstItemPrice()).isEqualTo("$49.99");
    }

    @Test
    public void sortItemAlphabetical_AtoZ_ShouldReturnBackpack() {
        ProductsPage productsPage = new ProductsPage();
        productsPage.clickSortItems(0);
        Assertions.assertThat(productsPage.getFirstItemName()).isEqualTo("Sauce Labs Backpack");
    }

    @Test
    public void sortItemAlphabetical_ZtoA_ShouldReturnRedTshirt() {
        ProductsPage productsPage = new ProductsPage();
        productsPage.clickSortItems(1);
        Assertions.assertThat(productsPage.getFirstItemName()).isEqualTo("Test.allTheThings() T-Shirt (Red)");
    }



    @Test
    public void clickAllRemoveButtons_ShouldReturn_MissingCartCounter() {
        ProductsPage productsPage = new ProductsPage();
        productsPage.clickAllToCartButtons();
        productsPage.clickAllToCartButtons();

        Assertions.assertThat(productsPage.getButtonsStatus()).isEqualTo(6);
    }


}
