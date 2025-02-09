package resources;

import java.util.ArrayList;
import java.util.List;

import addPlacePOJO.AddPlacePojo;
import addPlacePOJO.Location;




public class Payload {
	public AddPlacePojo getAddPlacePayload(String name, String phone_number, String address){
		AddPlacePojo payload = new AddPlacePojo();
		Location location = new Location();
		location.setLat(-32.383494);
		location.setLng(33.427362);
		payload.setLocation(location);
		payload.setAccuracy(50);
		payload.setName(name);
		payload.setPhone_number(phone_number);
		payload.setAddress(address);
		List<String> types = new ArrayList<>();
		types.add("Shoe park");types.add("shop");
		payload.setTypes(types);
		payload.setWebsite("http://google.com");
		payload.setLanguage("French-IN");
		return payload;
	}
	
	public String  getDeletePlacePayload(String placeID) {
		return "{\r\n"
				+ "    \"place_id\":\""+placeID+"\"\r\n"
				+ "}\r\n"
				+ "";
		
	}
}
