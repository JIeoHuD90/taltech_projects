package ui_testing;

import com.codeborne.selenide.WebDriverRunner;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ui_testing.page.CartPage;
import ui_testing.page.CheckoutPage;

import static com.codeborne.selenide.Selenide.open;
import static org.assertj.core.api.Assertions.*;

public class CheckoutPageTests {
    @BeforeEach
    public void login() {
        BaseTest baseTest = new BaseTest();
        baseTest.openLoginPage();
        CartPage cartPage = new CartPage();
        cartPage.clickGoToCartButton();
        cartPage.clickGoToCheckOut();

    }

    @BeforeAll
    public static void configureSelenide() {
        BaseTest baseTest = new BaseTest();
        baseTest.ConfigureSelenide();

    }

    @Test
    public void whenNoInputProvided_ShouldReturnErrorMessage() {
        CheckoutPage checkoutPage = new CheckoutPage();
        checkoutPage.pushContinueButton();

        assertThat(checkoutPage.getErrorMessage()).isEqualTo("Error: First Name is required");
    }

    @Test
    public void whenNoLastZipCodeProvided_ShouldReturnErrorMessage() {
        CheckoutPage checkoutPage = new CheckoutPage();
        checkoutPage.enterFirstName("First");
        checkoutPage.pushContinueButton();

        assertThat(checkoutPage.getErrorMessage()).isEqualTo("Error: Last Name is required");
    }

    @Test
    public void whenNoZipCodeProvided_ShouldReturnErrorMessage() {
        CheckoutPage checkoutPage = new CheckoutPage();
        checkoutPage.enterFirstName("First");
        checkoutPage.enterLastName("Last");
        checkoutPage.pushContinueButton();

        assertThat(checkoutPage.getErrorMessage()).isEqualTo("Error: Postal Code is required");
    }

    @Test
    public void whenAllDataProvided_ShouldGo_ToNextStepOfCheckout() {
        CheckoutPage checkoutPage = new CheckoutPage();
        checkoutPage.enterFirstName("First");
        checkoutPage.enterLastName("Last");
        checkoutPage.enterZipCode("403401");
        checkoutPage.pushContinueButton();

        assertThat(WebDriverRunner.getWebDriver().getCurrentUrl()).isEqualTo("https://www.saucedemo.com/checkout-step-two.html");

    }

    @Test
    public void check_ThatCartItems_And_CheckoutCartItems_ShouldBeEqual() {
        open("/cart.html");
        CartPage cartPage = new CartPage();
        var cartPageCartItemNames = cartPage.getCartItemNames();
        var cartPageCartItemsDescription = cartPage.getCartItemsDescription();
        var cartPageCartItemsPrice = cartPage.getCartItemsPrice();
        open("/checkout-step-two.html");
        CheckoutPage checkoutPage = new CheckoutPage();
        var checkoutPageCartItemNames = checkoutPage.getCheckoutCartItemNames();
        var checkoutPageCartItemsDescription = checkoutPage.getCheckoutCartItemsDescription();
        var checkoutPageCartItemsPrice = checkoutPage.getCheckoutCartItemsPrice();

        assertThat(cartPageCartItemNames).isEqualTo(checkoutPageCartItemNames);
        assertThat(cartPageCartItemsDescription).isEqualTo(checkoutPageCartItemsDescription);
        assertThat(cartPageCartItemsPrice).isEqualTo(checkoutPageCartItemsPrice);
    }

    @Test
    public void whenPressedFinish_Should_ContainMessage() {
        open("/checkout-step-two.html");
        CheckoutPage checkoutPage = new CheckoutPage();
        checkoutPage.pressFinishButton();

        assertThat(checkoutPage.getCheckoutMessage()).isEqualTo("Thank you for your order!");
    }
}

