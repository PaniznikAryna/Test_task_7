package api.client;

import io.restassured.response.Response;

public class PetClient extends ApiClient<PetClient> {

	@Override
	public PetClient getClient() {
		return this;
	}

	public Response addPet(Object body) {
		return request()
				.body(body)
				.post("/pet");
	}

	public Response updatePet(Object body) {
		return request()
				.body(body)
				.put("/pet");
	}

	public Response findPetByStatus(String status) {
		return request()
				.queryParam("status", status)
				.get("/pet/findByStatus");
	}

	public Response deletePetById(long id) {
		return request()
				.pathParam("id", id)
				.delete("/pet/{id}");
	}
}
