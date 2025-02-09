package stepDefinations;

import static io.restassured.RestAssured.given;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import resources.APIresources;
import resources.Payload;
import resources.Utils;

import static org.junit.Assert.assertEquals;

import java.io.IOException;

public class StepDefinitions extends Utils{
	
	RequestSpecification request;
	ResponseSpecification res;
	Response response;
	static Payload p;
	static String placeID;
	@Given("Add Place Payload with {string} {string} {string}")
	public void add_place_payload_with(String  name, String phone_number, String address) throws IOException {
		 p= new Payload();
		request = given().spec(getRequestSpecification()).body(p.getAddPlacePayload(name, phone_number,address)); 
	}
	
	/*@Given("Add Place Payload")
	public void add_place_payload() throws IOException {
		 
	}*/
	
	
	@When("user calls {string} with {string} http request")
	public void user_calls_with_http_request(String resource, String method) {
		APIresources resourceAPI = APIresources.valueOf(resource);
		if(method.equalsIgnoreCase("post"))
	     response =  request.when().post(resourceAPI.getResource()).then()
	    		 .spec(getResponseSpecification()).extract().response(); 
		if(method.equalsIgnoreCase("get"))
			response =  request.when().get(resourceAPI.getResource()).then()
   		 .spec(getResponseSpecification()).extract().response(); 
		if(method.equalsIgnoreCase("delete"))
			response =  request.when().delete(resourceAPI.getResource()).then()
   		 .spec(getResponseSpecification()).extract().response(); 
	}
	
	
	@Then("the API call is success with status code {int}")
	public void the_api_call_is_success_with_status_code(int responseCode) {
		assertEquals(response.getStatusCode(),responseCode);
	}
	
	
	@Then("{string} in response body is {string}")
	public void in_response_body_is(String keyValue, String expectedValue) {
		
		assertEquals(getResponseKey(response,keyValue), expectedValue);
	}
	

	@Then("verify the place_Id created with {string} using {string} with {string} http request")
	public void verify_the_place_id_created_with_using_with_http_request(String expectedPlace, String resource, String method) throws IOException {
    placeID = getResponseKey(response, "place_id");
    request = given().spec(getRequestSpecification()).queryParam("place_id", placeID);
    user_calls_with_http_request(resource,method);
    String actualPlace = getResponseKey(response,"name");
    assertEquals(actualPlace, expectedPlace);
    
}
	
	@Given("Delete Place Payload")
	public void delete_place_payload() throws IOException {
	    // Write code here that turns the phrase above into concrete actions
		 request = given().spec(getRequestSpecification()).body(p.getDeletePlacePayload(placeID));
	}

}
