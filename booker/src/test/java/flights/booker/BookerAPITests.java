package flights.booker;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.HashMap;
import java.util.Map;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

@SpringBootTest
public class BookerAPITests {
    
    enum Request {
        GET,
        POST
    }

    private static Map<String, Object> input = new HashMap<>();
    private static Map<String, String> header = new HashMap<>();
    static String body = "";

    public static void clearInputMaps() {
        input.clear();
        header.clear();
    }

    public static Response makeRequest(Map<String, ?> params, String path, Request type, String body,
            Map<String, String> header) {// Add (String body) if you want to receive the body
        Response temp2 = null;
        String response = "Error";
        header.put("content-type", "application/json");
        RequestSpecification temp = given()
                .headers(header)
                .queryParams(params)
                .body(body)
                .when();

        switch (type) {
            case GET:
                temp2 = temp.get(path);
                break;
            case POST:
                temp2 = temp.post(path);
                break;
        }
        return temp2;
    }
    
    @Test
	void testConfirmBookingValidRequest(String bodyString) {
        RestAssured.baseURI = "http://localhost:8080/destination";
        body = bodyString;
        Response response = makeRequest(input, "/day", Request.POST, body, header);
        assertEquals(201,response.getStatusCode());
        clearInputMaps();
    }
}
