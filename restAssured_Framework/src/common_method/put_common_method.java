package common_method;

import io.restassured.RestAssured;
import static io.restassured.RestAssured.given;

public class put_common_method {
	
	public static int responsestatuscode(String baseuri, String resource, String requestbody)
	{
		RestAssured.baseURI=baseuri;
		int responsestatuscode = given().header("Content-Type", "application/json").body(requestbody).when().put(resource)
				.then().extract().statusCode();
		return responsestatuscode;
		
	}
	public static String responsebody_extractor(String baseuri, String resource, String requestbody)
	{
		RestAssured.baseURI=baseuri;
		String responsebody = given().header("Content-Type", "application/json").body(requestbody).when().put(resource)
				.then().extract().response().asString();
		return responsebody;
	}

}
