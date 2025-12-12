package api;

import io.qameta.allure.restassured.AllureRestAssured;
import org.junit.jupiter.api.BeforeAll;
import io.restassured.RestAssured;

public class BaseTest {
	@BeforeAll
	static void setup() {
		RestAssured.filters(new AllureRestAssured());
	}
}
