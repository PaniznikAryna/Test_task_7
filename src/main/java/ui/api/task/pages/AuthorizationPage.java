package ui.api.task.pages;


import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$x;

public class AuthorizationPage {
	public static final String ERROR_MESSAGE = "//*[@id=\"login_button_container\"]/div/form/div[3]";
	public static final String ID_USER_NAME_INPUT = "#user-name";
	public static final String ID_PASSWORD_INPUT = "#password";
	public static final String ID_LOGIN_BUTTON = "#login-button";

	private final SelenideElement errorMessage = $x(ERROR_MESSAGE);

	@Step("Entering your username and password in the authorization form")
	public AuthorizationPage authorization(String login, String password) {
		$(ID_USER_NAME_INPUT).sendKeys(login);
		$(ID_PASSWORD_INPUT).sendKeys(password);
		$(ID_LOGIN_BUTTON).click();
		return this;
	}

	@Step("An authorization error message appears")
	public AuthorizationPage shouldSeeErrorMessage() {
		errorMessage.shouldBe(visible);
		return this;
	}
}
