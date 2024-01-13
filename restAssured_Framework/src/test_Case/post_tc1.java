package test_Case;

import java.io.IOException;
import java.time.LocalDate;

import common_method.CommonMethodUtilities;
import common_method.common_method_api;
import common_method.put_common_method;
import io.restassured.path.json.JsonPath;
import request_Repository.post_request_repository;


public class post_tc1 {
	public static void orchestrator() throws IOException
	{
		String responsebody = " ";
		int responsestatuscode = 0;
		String baseuri = post_request_repository.baseuri();
		String resource = post_request_repository.resource();
		String requestbody = post_request_repository.requestbody();
		
		for (int i=0; i<5; i++)
		{
			responsestatuscode = common_method_api.responsestatuscode_extractor(baseuri, resource, requestbody);
			if(responsestatuscode == 200)
			{
				responsebody =common_method_api.responsebody_extractor(baseuri, resource, requestbody);
				responsebodyValidator(responsebody);
				break;
			}
			else
			{
				System.out.println("correct status code is not found in the iteration" +i);
			}
			
		}
		CommonMethodUtilities.evidenceFileCreator("post_tc1", requestbody, responsebody);
		Assert.assertEquals =(responsestatuscode , 200);
	}
	public static void responsebodyValidator(String responsebody)
	{
		//create jsonPath object to extract responsebody parameters
		JsonPath jsp = new JsonPath(responsebody);
		
		//extract responsebody parameters
		String res_name = jsp.getString("name");
		//System.out.println(res_name);
		String res_job = jsp.getString("job");
		//System.out.println(res_job);
		String res_updatedAt = jsp.getString("updatedAt");
		//System.out.println(res_updatedAt);
		
		//validate responsebody parameters
		Assert.assertEquals(res_name, "morpheus");
		Assert.assertEquals(res_job, "leader");
	
		//extract date from updatedAt parameter
	    String actual_date=res_updatedAt.substring(0,10);
	    String current_date=LocalDate.now().toString();        //create date object
	    Assert.assertEquals(actual_date, current_date);
	}


}
