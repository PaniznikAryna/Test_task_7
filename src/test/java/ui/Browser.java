package ui;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.WebDriverRunner;
import com.codeborne.selenide.logevents.SelenideLogger;
import io.qameta.allure.Step;
import io.qameta.allure.selenide.AllureSelenide;
import org.openqa.selenium.WebDriver;

public class Browser {

	@Step("Init browser")
	public static void init() {
		Configuration.browser = "chrome";
		Configuration.browserSize = "1920x1080";
		Configuration.screenshots = true;
		Configuration.savePageSource = false;
		SelenideLogger.addListener("AllureSelenide", new AllureSelenide().screenshots(true).savePageSource(false));
	}

	@Step("Open website by url")
	public static void openingTheWebsite(String url) {
		Selenide.open(url);
	}

	@Step("Close browser")
	public static void close() {
		Selenide.closeWebDriver();
	}

	@Step("Get web driver")
	public static WebDriver getDriver() {
		return WebDriverRunner.getWebDriver();
	}
}
