package flights.generator;

import static io.restassured.RestAssured.*;
//import static org.hamcrest.Matchers.*;

import java.util.HashMap;
import java.util.Map;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

//import org.testng.Assert;

public class RestAPITests {
	
	enum Request {
		  GET,
		  POST
		}
	
	private static Map<String,Object> input = new HashMap<>();
	private static Map<String,String> header = new HashMap<>();
	static String body = "";
	
	public static void clearInputMaps() {
		input.clear();
		header.clear();
	}
	
	public static void insertInputMap(String where, Object value) {
		input.put(where, value);
	}
	
	public static void insertHeaderMap(String type, String parameter) {
		header.put(type,parameter);
	}
	
	//Types: get and post
	public static JsonPath makeRequest(Map<String, ?> params, String path, Request type) {
		String body="";
		Map<String, String> header = new HashMap<>();
		header.put("content-type", "application/json");
		return makeRequest(params,path, type, body,header);
	}
	
	public static JsonPath makeRequest(Map<String, ?> params, String path, Request type, String body, Map<String, String> header) {//Add (String body) if you want to receive the body
		Response temp2 = null;
		String response = "Error";
		
		RequestSpecification temp = given()
		.headers(header)
		.queryParams(params)
		.body(body)
		.when();
		
		switch(type) {
	      case GET:
	    	  temp2 = temp.get(path);
	        break;
	      case POST:
	    	  temp2 = temp.post(path);
	        break;
	    }
		
		response = temp2.then()
		.assertThat()
		.statusCode(200).extract().response().asString();
		
		return new JsonPath(response);	
	}

	public static void main(String[] args) {
		RestAssured.baseURI="localhost:3000/flights";
		
		//Get features
//		System.out.println(makeRequest(input, "/v1/dx/GetFeatures",Request.GET).getList("data").get(0));
//		//Initialize the session
//		String sessionId= makeRequest(input, "/v1/dx/InitSession",Request.GET).getString("SessionID");
//		//Get Defaults
//		insertInputMap("SessionID", sessionId);
//		System.out.println(makeRequest(input, "/v1/dx/GetUseDefaultValues",Request.GET).getString("value"));
//		//Accept the terms
//		insertInputMap("passphrase", "I have read, understood and I accept and agree to comply with the Terms of Use of EndlessMedicalAPI and Endless Medical services. The Terms of Use are available on endlessmedical.com");
//		System.out.println(makeRequest(input, "/v1/dx/AcceptTermsOfUse",Request.POST).getString("status"));
//		clearInputMaps();
//		//Change Defaults
//		insertInputMap("SessionID", sessionId);
//		insertInputMap("value", true);
//		System.out.println(makeRequest(input, "/v1/dx/SetUseDefaultValues",Request.POST).getString("status"));
//		clearInputMaps();
		
//		RestAssured.baseURI="http://216.10.245.166";
//		insertHeaderMap("content-type", "application/json");
//		System.out.println(makeRequest(input, "/Library/Addbook.php",Request.POST,Payload.bodyAddBook(),header).getString(""));
//		
		
//		insertHeaderMap("X-RapidAPI-Key" ,"SIGN-UP-FOR-KEY");
//		insertHeaderMap("X-RapidAPI-Host" ,"omgvamp-hearthstone-v1.p.rapidapi.com");
//		String cardname="/cards/%7BYsera%7D";
//		System.out.println(makeRequest(input, cardname,Request.GET,body,header).getList(""));

	}

}
