package ui_testing.page;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;


import java.util.Arrays;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;

public class CartPage {
    ElementsCollection cartItemNames = $$(".inventory_item_name");
    ElementsCollection cartItemsDescription = $$(".inventory_item_desc");
    ElementsCollection cartItemsPrice = $$(".inventory_item_price");
    ElementsCollection cartRemoveButton = $$(".cart_button");
    ElementsCollection removedCartItem = $$(".removed_cart_item");
    SelenideElement goToCartButton = $(".shopping_cart_link");
    SelenideElement goToCheckoutButton = $("#checkout");


    public String[] getCartItemNames() {
        String output[] = new String[cartItemsPrice.size()];
        for (var i = 0; i < cartItemNames.size(); i++) {
            SelenideElement element = cartItemNames.get(i);
            output[i] = element.getText();
        }
        return output;
    }

    public String[] getCartItemsDescription() {
        String output[] = new String[cartItemsPrice.size()];
        for (var i = 0; i < cartItemsDescription.size(); i++) {
            SelenideElement element = cartItemsDescription.get(i);
            output[i] = element.getText();
        }
        return output;
    }

    public String[] getCartItemsPrice() {
        String output[] = new String[cartItemsPrice.size()];
        for (var i = 0; i < cartItemsPrice.size(); i++) {
            SelenideElement element = cartItemsPrice.get(i);
            output[i] = element.getText();
        }
        return output;
    }

    public void removeAllItemsFromCart() {
        for (var i = 0; i < cartRemoveButton.size(); i++) {
            SelenideElement button = cartRemoveButton.get(i);
            button.click();
        }

    }

    public void clickGoToCartButton() {
        goToCartButton.click();
    }

    public Integer[] getNullValuesFor() {
        Integer output[] = {};
        for (var i = 0; i < removedCartItem.size(); i++) {
            SelenideElement element = removedCartItem.get(i);
            if (element.getText() == null) {
                output[i] = 1;
            }
        }
        return output;
    }

    public void clickGoToCheckOut() {
        goToCheckoutButton.click();
    }
}
