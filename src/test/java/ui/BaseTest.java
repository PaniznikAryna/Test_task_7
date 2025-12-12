package ui;

import io.github.cdimascio.dotenv.Dotenv;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import ui.api.task.pages.AuthorizationPage;
import ui.api.task.pages.CartPage;
import ui.api.task.pages.CatalogPage;
import ui.api.task.pages.CheckoutPage;

public class BaseTest {
	protected static Dotenv dotenv;
	protected AuthorizationPage authorizationPage;
	protected CatalogPage catalogPage;
	protected CartPage cartPage;
	protected CheckoutPage checkoutPage;

	@BeforeAll
	static void setup() {
		dotenv = Dotenv.load();
		Browser.init();
	}

	@AfterAll
	static void tearDown() {
		Browser.close();
	}
}
