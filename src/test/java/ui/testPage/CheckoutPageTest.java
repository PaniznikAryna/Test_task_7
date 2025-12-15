package ui.testPage;

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
import ui.api.task.pages.CheckoutPage;

@ExtendWith(AllureJunit5.class)
@Epic("Checkout page")
@DisplayName("Checkout page testing")
public class CheckoutPageTest extends BaseTest {

	@BeforeEach
	void setUp() {
		Browser.openingTheWebsite(dotenv.get("BASE_URL"));
		authorizationPage = new AuthorizationPage();
		authorizationPage.authorization(dotenv.get("VALID_LOGIN"), dotenv.get("PASSWORD"));

		catalogPage = new CatalogPage();
		cartPage = new CartPage();

		checkoutPage = new CheckoutPage();
	}

	@Test
	@DisplayName("Making an order")
	@Description("Checks the user's ability to place an order, enter their details and see the order information")
	void PlacingAnOrder() {
		catalogPage.addItemToCart()
				.goToCartPage();
		cartPage.clickCheckoutButton();

		checkoutPage.sendDataForm(dotenv.get("FIRST_NAME"), dotenv.get("LAST_NAME"), dotenv.get("POSTAL_CODE"))
				.clickContinueButton()
				.shouldSeeInformationAboutItem();
	}

	@Test
	@DisplayName("Checking the success of the order")
	@Description("The user adds an item to the cart, places an order, and sees confirmation of successful checkout")
	void orderConfirmation() {
		catalogPage.addItemToCart()
				.goToCartPage();
		cartPage.clickCheckoutButton();

		checkoutPage.sendDataForm(dotenv.get("FIRST_NAME"), dotenv.get("LAST_NAME"), dotenv.get("POSTAL_CODE"))
				.clickContinueButton()
				.shouldSeeInformationAboutItem()
				.clickFinishButton()
				.shouldSeeCheckoutComplete()
				.shouldSeeThanksForOrder();
	}
}
