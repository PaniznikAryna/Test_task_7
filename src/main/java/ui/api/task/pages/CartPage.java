package ui.api.task.pages;

import com.codeborne.selenide.ElementsCollection;
import io.qameta.allure.Step;

import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;

public class CartPage {

	public static final String TEXT_REMOVE_ITEM = "Remove";
	public static final String ID_CHECKOUT_BUTTON = "#checkout";
	public static final String ID_CONTINUE_SHOPPING_BUTTON = "#continue-shopping";
	private static final String CART_ITEMS = ".cart_item";

	@Step("Removing an item from cart")
	public CartPage removeItem() {
		$(byText(TEXT_REMOVE_ITEM)).click();
		return this;
	}

	@Step("Clicking on the Checkout button")
	public CartPage clickCheckoutButton() {
		$(ID_CHECKOUT_BUTTON).click();
		return this;
	}

	@Step("Go to the Catalog page")
	public CatalogPage goToCatalogPage() {
		$(ID_CONTINUE_SHOPPING_BUTTON).click();
		return new CatalogPage();
	}

	@Step("Get all items in cart")
	public ElementsCollection getCartItems() {
		return $$(CART_ITEMS);
	}
}
