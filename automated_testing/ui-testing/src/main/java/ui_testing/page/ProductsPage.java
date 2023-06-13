package ui_testing.page;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;

public class ProductsPage {
    private SelenideElement pageHeadingField = $(".title");
    private SelenideElement firstItemPrice = $(".inventory_item_price");
    private SelenideElement sortItems = $(".product_sort_container");
    private SelenideElement firstItemName = $(".inventory_item_name");
    private SelenideElement firstToCartButton = $(".btn_inventory");
    private ElementsCollection toCartButtons = $$(".btn_inventory");
    private SelenideElement cartItemsCount = $(".shopping_cart_badge");
    private ElementsCollection itemsOnProductPage = $$(".inventory_item_name");


    public String getPageHeadingText() {
        return pageHeadingField.getText();
    }

    public String getFirstItemName() {
        return firstItemName.getText();
    }

    public String getFirstItemPrice() {
        return firstItemPrice.getText();
    }

    public void clickSortItems(Integer index) {
        sortItems.selectOption(index);
    }



    public void clickAllToCartButtons() {
        for (var i = 0; i < toCartButtons.size(); i++) {
            SelenideElement button = toCartButtons.get(i);
            button.click();
        }
    }

    public Integer getButtonsStatus() {
        var counter = 0;

        for (var i = 0; i < toCartButtons.size(); i++) {
            SelenideElement button = toCartButtons.get(i);
            if (button.getText() != "Remove") {
                counter++;
            }
        }
        return counter;

    }

}
