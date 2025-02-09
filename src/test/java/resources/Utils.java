package resources;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.util.Properties;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;

public class Utils {
	static RequestSpecification req;
	public RequestSpecification getRequestSpecification() throws IOException {
		if(req==null) {
		PrintStream log = new PrintStream(new  FileOutputStream("log.txt"));
		req = new RequestSpecBuilder().setBaseUri(getGlobalValue("baseURL"))
				
				.addHeader("Content-type","application/json").addQueryParam("key", "qaclick123")
				.addFilter(RequestLoggingFilter.logRequestTo(log))
				.addFilter(ResponseLoggingFilter.logResponseTo(log))
				.build();
		}
		return req;
	}
	
	public ResponseSpecification getResponseSpecification() {
		ResponseSpecification res = new ResponseSpecBuilder().expectContentType(ContentType.JSON).
				 expectHeader("Server","Apache/2.4.52 (Ubuntu)").build();
		return res;
	}
	
	public  String getGlobalValue(String key) throws IOException {
		Properties prop = new Properties();
		FileInputStream fis = new FileInputStream(System.getProperty("user.dir")+ "\\src\\test\\java\\resources\\global.properties");
	    prop.load(fis);	
	    return prop.getProperty(key);
	}
	
	public  String getResponseKey(Response response, String key) {
		JsonPath js = new JsonPath(response.asString());
		return js.getString(key);
	}
}
