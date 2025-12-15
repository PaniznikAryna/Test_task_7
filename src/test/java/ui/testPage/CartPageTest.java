package ui.testPage;

import com.codeborne.selenide.CollectionCondition;
import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import io.qameta.allure.junit5.AllureJunit5;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import ui.BaseTest;
import ui.Browser;
import ui.api.task.pages.AuthorizationPage;
import ui.api.task.pages.CartPage;
import ui.api.task.pages.CatalogPage;

@Epic("Cart page")
@ExtendWith(AllureJunit5.class)
@DisplayName("Cart page testing")
public class CartPageTest extends BaseTest {

	@BeforeEach
	void setUp() {
		Browser.openingTheWebsite(dotenv.get("BASE_URL"));
		authorizationPage = new AuthorizationPage();
		authorizationPage.authorization(dotenv.get("VALID_LOGIN"), dotenv.get("PASSWORD"));

		catalogPage = new CatalogPage();
		cartPage = new CartPage();
	}

	@Test
	@DisplayName("Adding and removing an item to the cart")
	@Description("Checking that the basket is empty, adding 2 items, deleting 1")
	void addAndRemoveItemsForCart() {
		catalogPage.goToCartPage();
		cartPage.getCartItems().shouldHave(CollectionCondition.size(0));

		cartPage.goToCatalogPage()
				.addItemToCart()
				.addItemToCart()
				.goToCartPage();
		cartPage.getCartItems().shouldHave(CollectionCondition.size(2));

		cartPage.removeItem()
				.getCartItems().shouldHave(CollectionCondition.size(1));
	}
}
