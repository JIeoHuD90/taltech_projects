package ui_testing.page;

import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Selenide.$;

public class ProductDescriptionPage {
    SelenideElement itemName = $(".inventory_details_name");
    SelenideElement itemDescription = $(".inventory_details_desc");
    SelenideElement itemCost = $(".inventory_details_price");
    SelenideElement toCartButton = $(".btn_inventory");
    SelenideElement cartItemCount=$(".shopping_cart_badge");


    public String getItemName() {
        return itemName.getText();

    }

    public String getItemDescription() {
        return itemDescription.getText();
    }

    public String getItemCost() {
        return itemCost.getText();
    }

    public void clickToCartButton() {
        toCartButton.click();
    }
    public String  getButtonStatus(){
        return toCartButton.getText();
    }
    public String getCartItemCount(){
        return cartItemCount.getText();
    }
}
