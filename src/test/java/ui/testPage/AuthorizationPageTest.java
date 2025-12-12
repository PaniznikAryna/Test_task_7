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

import java.util.Objects;

import static com.codeborne.selenide.WebDriverRunner.url;
import static org.junit.jupiter.api.Assertions.assertTrue;

@Epic("Authorization page")
@ExtendWith(AllureJunit5.class)
@DisplayName("Authorization page testing")
public class AuthorizationPageTest extends BaseTest {

	@BeforeEach
	void setUp() {
		Browser.openingTheWebsite(dotenv.get("BASE_URL"));
		authorizationPage = new AuthorizationPage();
	}

	@Test
	@DisplayName("User authorization with valid data")
	@Description("The user enters a valid username and password and logs into the account")
	void authorization() {
		authorizationPage.authorization(dotenv.get("VALID_LOGIN"), dotenv.get("PASSWORD"));
		assertTrue(Objects.requireNonNull(url()).contains("/inventory"), "Expected to go to the inventory page, but the current URL: " + url());
	}

	@Test
	@DisplayName("Authorization of a user with invalid data")
	@Description("The user enters an invalid username and password and attempts to log in to the account. The user sees a toast with an error")
	void authorizationFailed() {
		authorizationPage.authorization(dotenv.get("INVALID_LOGIN"), dotenv.get("PASSWORD"))
				.shouldSeeErrorMessage();
	}
}
