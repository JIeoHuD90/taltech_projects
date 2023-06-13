package ui_testing;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ui_testing.page.ProductDescriptionPage;

import static com.codeborne.selenide.Selenide.open;
import static org.assertj.core.api.Assertions.*;

public class ProductDescriptionPageTests {
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
    public void checkThatAllProductDescriptionPagesHave_ProductName_Price_Description_toCartButton() {
        ProductDescriptionPage productDescriptionPage = new ProductDescriptionPage();
        for (int i = 0; i < 6; i++) {
            open(String.format("/inventory-item.html?id=%s", i));
            assertThat(productDescriptionPage.getItemName()).isNotEmpty();
            assertThat(productDescriptionPage.getItemDescription()).isNotEmpty();
            assertThat(productDescriptionPage.getItemCost()).isNotEmpty();
            assertThat(productDescriptionPage.getButtonStatus()).isNotEqualTo("Remove");
            productDescriptionPage.clickToCartButton();
            assertThat(productDescriptionPage.getButtonStatus()).isEqualTo("Remove");
        }
        assertThat(productDescriptionPage.getCartItemCount()).isEqualTo("6");
    }
}
