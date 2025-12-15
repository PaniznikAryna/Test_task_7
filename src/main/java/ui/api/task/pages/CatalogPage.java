package ui.api.task.pages;

import com.codeborne.selenide.ElementsCollection;
import io.qameta.allure.Step;

import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$x;
import static com.codeborne.selenide.Selenide.$$;

public class CatalogPage {
	public static final String TEXT_ADD_TO_CART_BUTTON = "Add to cart";
	public static final String SELECT_SORT_FOR_ITEMS = "//*[@id=\"header_container\"]/div[2]/div/span/select";
	public static final String ID_CART_ICON = "#shopping_cart_container";
	private static final String ITEM_NAMES = ".inventory_item_name";
	private static final String ITEM_PRICES = ".inventory_item_price";

	@Step("Adding an item to the cart")
	public CatalogPage addItemToCart() {
		$(byText(TEXT_ADD_TO_CART_BUTTON)).click();
		return this;
	}

	@Step("Sorting items")
	public CatalogPage sortItems(String sort) {
		$x(SELECT_SORT_FOR_ITEMS).selectOption(sort);
		return this;
	}

	@Step("Go to the Cart page")
	public CatalogPage goToCartPage() {
		$(ID_CART_ICON).click();
		return this;
	}

	@Step("Get all item names")
	public ElementsCollection getItemNames() {
		return $$(ITEM_NAMES);
	}

	@Step("Get all item prices")
	public ElementsCollection getItemPrices() {
		return $$(ITEM_PRICES);
	}
}
