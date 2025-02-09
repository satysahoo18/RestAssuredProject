package stepDefinations;

import java.io.IOException;


import io.cucumber.java.Before;
import resources.Utils;

public class Hooks {

	@Before("@DeletePlace")
	public void beforeDeleteSenario() throws IOException {
	
		StepDefinitions s = new StepDefinitions();
		if(StepDefinitions.placeID==null) {
		s.add_place_payload_with("satya", "7008794289", "Ahmedabad");
		s.user_calls_with_http_request("AddPlaceAPI", "post");
		Utils utils = new Utils();
		StepDefinitions.placeID = utils.getResponseKey(s.response,"place_id");
		}
	}
}
