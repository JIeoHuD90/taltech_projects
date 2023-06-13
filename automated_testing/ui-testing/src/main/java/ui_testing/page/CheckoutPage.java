package ui_testing.page;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;

public class CheckoutPage {
    SelenideElement firstNameField = $("#first-name");
    SelenideElement lastNameField = $("#last-name");
    SelenideElement zipCodeField = $("#postal-code");
    SelenideElement errorMessageField = $("h3");
    SelenideElement continueButton = $("#continue");
    ElementsCollection checkoutCartItems = $$(".inventory_item_name");
    ElementsCollection checkoutCartItemsDescription = $$(".inventory_item_desc");
    ElementsCollection checkoutCartItemsPrice = $$(".inventory_item_price");
    SelenideElement finishButton = $("#finish");
    SelenideElement checkoutMessage = $(".complete-header");

    public void enterFirstName(String firstName) {
        firstNameField.setValue(firstName);
    }

    public void enterLastName(String lastName) {
        lastNameField.setValue(lastName);
    }

    public void enterZipCode(String zipCode) {
        zipCodeField.setValue(zipCode);
    }

    public String getErrorMessage() {
        return errorMessageField.getText();
    }

    public void pushContinueButton() {
        continueButton.click();
    }

    public String[] getCheckoutCartItemNames() {
        String output[] = {};
        for (var i = 0; i < checkoutCartItems.size(); i++) {
            SelenideElement element = checkoutCartItems.get(i);
            output[i] = element.getText();
        }
        return output;
    }

    public String[] getCheckoutCartItemsDescription() {
        String output[] = {};
        for (var i = 0; i < checkoutCartItemsDescription.size(); i++) {
            SelenideElement element = checkoutCartItemsDescription.get(i);
            output[i] = element.getText();
        }
        return output;
    }

    public String[] getCheckoutCartItemsPrice() {
        String output[] = {};
        for (var i = 0; i < checkoutCartItemsPrice.size(); i++) {
            SelenideElement element = checkoutCartItemsPrice.get(i);
            output[i] = element.getText();
        }
        return output;
    }

    public void pressFinishButton() {
        finishButton.click();
    }

    public String getCheckoutMessage() {
        return checkoutMessage.getText();
    }
}
