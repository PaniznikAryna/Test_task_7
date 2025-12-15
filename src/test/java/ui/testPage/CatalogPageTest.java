package ui.testPage;

import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import io.qameta.allure.junit5.AllureJunit5;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import ui.BaseTest;
import ui.Browser;
import ui.api.task.pages.AuthorizationPage;
import ui.api.task.pages.CatalogPage;
import ui.assertions.SortAssertions;

@ExtendWith(AllureJunit5.class)
@Epic("Catalog page")
@DisplayName("Catalog page testing")
@Tag("UI")
public class CatalogPageTest extends BaseTest {

	@BeforeEach
	void setUp() {
		Browser.openingTheWebsite(dotenv.get("BASE_URL"));
		authorizationPage = new AuthorizationPage();
		authorizationPage.authorization(dotenv.get("VALID_LOGIN"), dotenv.get("PASSWORD"));

		catalogPage = new CatalogPage();
	}

	@Test
	@DisplayName("Sort items by name ascending (A→Z)")
	@Description("Verify that items are sorted alphabetically ascending")
	void sortByNameAscending() {
		catalogPage.sortItems("Name (A to Z)");
		SortAssertions.assertSortedAscendingByName(catalogPage.getItemNames());
	}

	@Test
	@DisplayName("Sort items by name descending (Z→A)")
	@Description("Verify that items are sorted alphabetically descending")
	void sortByNameDescending() {
		catalogPage.sortItems("Name (Z to A)");
		SortAssertions.assertSortedDescendingByName(catalogPage.getItemNames());
	}

	@Test
	@DisplayName("Sort items by price ascending (low→high)")
	@Description("Verify that items are sorted by price ascending")
	void sortByPriceAscending() {
		catalogPage.sortItems("Price (low to high)");
		SortAssertions.assertSortedAscendingByPrice(catalogPage.getItemPrices());
	}

	@Test
	@DisplayName("Sort items by price descending (high→low)")
	@Description("Verify that items are sorted by price descending")
	void sortByPriceDescending() {
		catalogPage.sortItems("Price (high to low)");
		SortAssertions.assertSortedDescendingByPrice(catalogPage.getItemPrices());
	}
}
