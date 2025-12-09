package ui;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.logevents.SelenideLogger;
import io.qameta.allure.Step;
import io.qameta.allure.selenide.AllureSelenide;
import org.junit.jupiter.api.BeforeAll;

public class BaseTest {
	@BeforeAll
	static void setup() {
		Configuration.browser = "chrome";
		Configuration.browserSize = "1920x1080";
		Configuration.screenshots = true;
		Configuration.savePageSource = false;
		SelenideLogger.addListener("AllureSelenide", new AllureSelenide().screenshots(true).savePageSource(false));
	}

	@Step("Open website by url")
	public void openingTheWebsite(String url) {
		Selenide.open(url);
	}
}