package ui.api.task.pages;

import io.qameta.allure.Step;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.$;

public class CheckoutPage {
	public static final String ID_FIRST_NAME_INPUT = "#first-name";
	public static final String ID_LAST_NAME_INPUT = "#last-name";
	public static final String ID_POSTAL_CODE_INPUT = "#postal-code";
	public static final String ID_CONTINUE_BUTTON = "#continue";
	public static final String ID_FINISH_BUTTON = "#finish";
	public static final String TEXT_PAYMENT_INFORMATION = "Payment Information:";
	public static final String TEXT_THANK_YOU_FOR_YOUR_ORDER = "Thank you for your order!";
	public static final String TEXT_CHECKOUT_COMPLETE = "Checkout: Complete!";

	@Step("Filling out the form for sending an item")
	public CheckoutPage sendDataForm(String firstName, String lastName, String postalCode){
		$(ID_FIRST_NAME_INPUT).sendKeys(firstName);
		$(ID_LAST_NAME_INPUT).sendKeys(lastName);
		$(ID_POSTAL_CODE_INPUT).sendKeys(postalCode);
		return this;
	}

	@Step("Clicking on the Continue button")
	public CheckoutPage clickContinueButton(){
		$(ID_CONTINUE_BUTTON).click();
		return this;
	}

	@Step("Clicking on the Finish button")
	public CheckoutPage clickFinishButton(){
		$(ID_FINISH_BUTTON).click();
		return this;
	}

	@Step("Checking the visibility of the text Payment Information")
	public CheckoutPage shouldSeeInformationAboutItem(){
		$(byText(TEXT_PAYMENT_INFORMATION)).shouldBe(visible);
		return this;
	}

	@Step("Checking the visibility of the text Thank you for your order!")
	public CheckoutPage shouldSeeThanksForOrder(){
		$(byText(TEXT_THANK_YOU_FOR_YOUR_ORDER)).shouldBe(visible);
		return this;
	}

	@Step("Checking the visibility of the text Checkout: Complete!")
	public CheckoutPage shouldSeeCheckoutComplete(){
		$(byText(TEXT_CHECKOUT_COMPLETE)).shouldBe(visible);
		return this;
	}
}
