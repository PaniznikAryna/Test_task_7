package api;

import api.client.PetClient;
import api.dto.PetRequest;
import api.dto.PetResponse;
import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Story;
import io.qameta.allure.junit5.AllureJunit5;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import java.util.List;

import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;
import static org.assertj.core.api.Assertions.assertThat;

@Epic("API tests")
@Feature("Swagger with API for PetStore")
@DisplayName("Api tests for PetStore")
@ExtendWith(AllureJunit5.class)
public class PetTest extends BaseTest {

	private final PetClient petClient = new PetClient();

	private PetRequest buildPetRequest() {
		return PetRequest.builder()
				.id(1)
				.category(PetRequest.Category.builder()
						.id(1)
						.name("cat")
						.build())
				.name("Kayden")
				.photoUrls(List.of("http://photo.png"))
				.tags(List.of(PetRequest.Tag.builder()
						.id(1)
						.name("fluffy")
						.build()))
				.status("available")
				.build();
	}

	@Test
	@Story("Adding a new pet")
	@DisplayName("Adding a pet to the request body")
	@Description("Verifies that when adding a new pet, the 200 status code and the correctness of the stored information will be returned")
	void addPetShouldReturn200() {
		PetRequest petRequest = buildPetRequest();

		PetResponse response = petClient.addPet(petRequest)
				.then()
				.statusCode(200)
				.body(matchesJsonSchemaInClasspath("schemas/pet-schema.json"))
				.extract()
				.as(PetResponse.class);

		assertThat(response.getId()).isEqualTo(1);
		assertThat(response.getName()).isEqualTo("Kayden");
		assertThat(response.getStatus()).isEqualTo("available");
	}

	@Test
	@Story("Updating pet data")
	@DisplayName("Updating pet information based on the request body")
	@Description("Verifies that when the pet is updated, the 200 status code and the correctness of the stored information will be returned")
	void updatePetsInfoShouldReturn200() {
		PetRequest petRequest = buildPetRequest();

		PetResponse response = petClient.updatePet(petRequest)
				.then()
				.statusCode(200)
				.body(matchesJsonSchemaInClasspath("schemas/pet-schema.json"))
				.extract()
				.as(PetResponse.class);

		assertThat(response.getId()).isEqualTo(1);
		assertThat(response.getName()).isEqualTo("Kayden");
		assertThat(response.getStatus()).isEqualTo("available");
	}

	@Test
	@Story("Pet Filtering")
	@DisplayName("Pet search by status")
	@Description("Checks that when searching by status, pets with only this status and the status code 200 are returned")
	void findPetByStatusShouldReturn200() {
		List<PetResponse> pets = petClient.findPetByStatus("available")
				.then()
				.statusCode(200)
				.body(matchesJsonSchemaInClasspath("schemas/pet-list-schema.json"))
				.extract()
				.body()
				.jsonPath()
				.getList(".", PetResponse.class);

		assertThat(pets).allMatch(p -> "available".equals(p.getStatus()));
	}

	@Test
	@Story("Pet Delete")
	@DisplayName("Deleting a pet by id")
	@Description("Checks that when the pet is deleted, the status code 200 is returned.")
	void deletePetByIdShouldReturn200() {
		String message = petClient.deletePetById(1)
				.then()
				.statusCode(200)
				.extract()
				.path("message");

		assertThat(message).isEqualTo("1");
	}
}
