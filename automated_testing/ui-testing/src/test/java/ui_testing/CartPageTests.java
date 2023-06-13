package ui_testing;

import org.junit.jupiter.api.*;
import ui_testing.page.CartPage;
import ui_testing.page.ProductsPage;

import static com.codeborne.selenide.Selenide.open;
import static org.assertj.core.api.Assertions.*;

public class CartPageTests {

    @BeforeEach
    public void login() {
        BaseTest baseTest = new BaseTest();
        baseTest.openLoginPage();

    }

    @BeforeAll
    public static void configureSelenide() {
        BaseTest baseTest = new BaseTest();
        baseTest.ConfigureSelenide();

    }


    @Test
    void cartPage_ShouldContain_ProductName() {
        ProductsPage productsPage = new ProductsPage();
        productsPage.clickAllToCartButtons();
        open("/cart.html");
        CartPage cartPage = new CartPage();
        for (var i = 0; i < cartPage.getCartItemNames().length; i++) {
            assertThat(cartPage.getCartItemNames()[i]).isNotEmpty();
        }
        cartPage.removeAllItemsFromCart();
    }

    @Test
    void cartPage_ShouldContain_ProductDescription() {
        ProductsPage productsPage = new ProductsPage();
        productsPage.clickAllToCartButtons();
        open("/cart.html");
        CartPage cartPage = new CartPage();

        for (var i = 0; i < cartPage.getCartItemsDescription().length; i++) {
            assertThat(cartPage.getCartItemsDescription()[i]).isNotEmpty();
        }
    }

    @Test
    void cartPage_ShouldContain_ProductPrice() {
        CartPage cartPage = new CartPage();
        ProductsPage productsPage = new ProductsPage();
        productsPage.clickAllToCartButtons();
        open("/cart.html");

        for (var i = 0; i < cartPage.getCartItemsPrice().length; i++) {
            assertThat(cartPage.getCartItemsPrice()[i]).isNotEmpty();
        }
    }

    @Test
    void removingAllFromCart_ShouldReturnEmptyClass_Removed_Cart_Item() {
        CartPage cartPage = new CartPage();
        ProductsPage productsPage = new ProductsPage();
        productsPage.clickAllToCartButtons();
        open("/cart.html");
        cartPage.removeAllItemsFromCart();
        for (var i = 0; i < cartPage.getNullValuesFor().length; i++) {
            assertThat(cartPage.getNullValuesFor()[i]).isEqualTo(1);
        }
    }

}
