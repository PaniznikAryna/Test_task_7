package api;

import io.qameta.allure.*;
import io.qameta.allure.junit5.AllureJunit5;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.everyItem;

@Epic("API tests")
@Feature("Swagger with API for PetStore")
@DisplayName("Api tests for PetStore")
@ExtendWith(AllureJunit5.class)
public class PetTest extends BaseTest {
public static final String PET_REQUEST_BODY = """
				{
				  "id": 1,
				  "category": {
				    "id": 1,
				    "name": "cat"
				  },
				  "name": "Kayden",
				  "photoUrls": ["http://photo.png"],
				  "tags": [
				    {
				      "id": 1,
				      "name": "fluffy"
				    }
				  ],
				  "status": "available"
				}
				""";

	@Test
	@Story("Adding a new pet")
	@DisplayName("Adding a pet to the request body")
	@Description("Verifies that when adding a new pet, the 200 status code and the correctness of the stored information will be returned")
	void addPetShouldReturn200() {
		given()
				.contentType("application/json")
				.body(PET_REQUEST_BODY)
				.when()
				.post("/pet")
				.then()
				.statusCode(200)
				.body("id", equalTo(1))
				.body("name", equalTo("Kayden"))
				.body("status", equalTo("available"))
				.extract().asString();;
	}

	@Test
	@Story("Updating pet data")
	@DisplayName("Updating pet information based on the request body")
	@Description("Verifies that when the pet is updated, the 200 status code and the correctness of the stored information will be returned")
	void updatePetsInfoShouldReturn200(){
		given()
				.contentType("application/json")
				.body(PET_REQUEST_BODY)
				.when()
				.put("/pet")
				.then()
				.statusCode(200)
				.body("id", equalTo(1))
				.body("name", equalTo("Kayden"))
				.body("status", equalTo("available"));
	}

	@Test
	@Story("Pet Filtering")
	@DisplayName("Pet search by status")
	@Description("Checks that when searching by status, pets with only this status and the status code 200 are returned")
	void findPetByStatusShouldReturn200(){
		given()
				.queryParam("status","available")
				.when()
				.get("/pet/findByStatus")
				.then()
				.statusCode(200)
				.body("status", everyItem(equalTo("available")));
	}

	@Test
	@Story("Pet Delete")
	@DisplayName("Deleting a pet by id")
	@Description("Checks that when the pet is deleted, the status code 200 is returned.")
	void deletePetByIdShouldReturn200(){
		given()
				.pathParam("id", 1)
				.when()
				.delete("/pet/{id}")
				.then()
				.statusCode(200);
	}
}
