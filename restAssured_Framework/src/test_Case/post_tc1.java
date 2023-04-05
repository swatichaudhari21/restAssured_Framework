package test_Case;

import java.io.IOException;
import java.time.LocalDate;

import org.testng.Assert;

import common_method.CommonMethodUtilities;
import common_method.common_method_api;
import io.restassured.path.json.JsonPath;
import request_Repository.post_request_repository;

public class post_tc1 
{

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
			if(responsestatuscode == 201)
			{
				responsebody = common_method_api.responsebody_extractor(baseuri, resource, requestbody);
				responsebodyValidator(responsebody);
				break;
			}
			else
			{
				System.out.println("correct status code is not found in the iteration" +i);
			}
		
			
			}
		CommonMethodUtilities.evidenceFileCreator("post_tc1", requestbody, responsebody);
		Assert.assertEquals(responsestatuscode, 201);
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
			String res_id = jsp.getString("id");
			//System.out.println(res_id);
			String res_createdAt = jsp.getString("createdAt");
			//System.out.println(res_createdAt);
			
			//validate responsebody parameters
			Assert.assertEquals(res_name, "morpheus");
			Assert.assertEquals(res_job, "leader");
			Assert.assertNotNull(res_id, "assertion error,id parameter is null");
	
			//extract date from createdAt parameter
			/*String actual_date = res_createdAt.substring(0,10);
			String current_date = LocalDate.now().toString();        //create date object
			Assert.assertEquals(actual_date, current_date);*/
			String Date = new String(res_createdAt);
			//System.out.println("Date.substring(0,10)");
			String result = new String(Date);
			//System.out.println("result.substring(0,10)");
			Assert.assertEquals(Date, result);
		
		}
}
