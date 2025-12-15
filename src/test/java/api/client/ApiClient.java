package api.client;

import io.restassured.RestAssured;
import io.restassured.specification.RequestSpecification;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public abstract class ApiClient<T> {

	private final String baseUrl;

	protected ApiClient() {
		this.baseUrl = loadBaseUrl();
	}

	private String loadBaseUrl() {
		try (InputStream input = getClass().getClassLoader().getResourceAsStream("application.properties")) {
			Properties props = new Properties();
			props.load(input);
			return props.getProperty("api.base.url");
		} catch (IOException e) {
			throw new RuntimeException("Couldn't load baseUrl from application.properties", e);
		}
	}

	protected RequestSpecification request() {
		return RestAssured.given()
				.baseUri(baseUrl)
				.contentType("application/json");
	}

	public abstract T getClient();
}
