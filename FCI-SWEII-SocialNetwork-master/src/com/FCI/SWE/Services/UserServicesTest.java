package com.FCI.SWE.Services;

import javax.ws.rs.FormParam;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.testng.Assert;
import org.testng.annotations.Test;

import com.FCI.SWE.Controller.Connection;
import com.FCI.SWE.Models.User;

public class UserServicesTest {

	 @Test
	  public void acceptrequestService() {
		
			String urlParameters = "semail=" + "reham.taie2hotmail.com";
			String retJson = Connection.connect("http://localhost:8888/rest/acceptrequest", urlParameters, "POST",
					"application/x-www-form-urlencoded;charset=UTF-8");
			JSONParser parser = new JSONParser();
			Object obj;
			JSONObject object = null;
			try {
				obj = parser.parse(retJson);
				object = (JSONObject) obj;
			}
			catch(Exception e)
			{
				e.printStackTrace();
			}
		  Assert.assertEquals("accept", object.get("Status").toString());
		  
	  }
	
	 @Test
	  public void sendrequestService() {
		 
			String urlParameters = "femail=" + "reham.taie@hotmail.com";
			String retJson = Connection.connect("http://localhost:8888/rest/sendrequest", urlParameters, "POST",
					"application/x-www-form-urlencoded;charset=UTF-8");
			       JSONParser parser = new JSONParser();
					Object obj;
					JSONObject object = null;
					try {
						obj = parser.parse(retJson);
						object = (JSONObject) obj;
					}
					catch(Exception e)
					{
						e.printStackTrace();
					}
				  Assert.assertEquals("success", object.get("Status").toString());
				  
	  }

	 @Test
	  public void likepageService() {
		  String urlParameters = "p=" + "FCI";
		  String retJson = Connection.connect("http://localhost:8888/rest/likepageService", urlParameters, "POST",
					"application/x-www-form-urlencoded;charset=UTF-8");

				    JSONParser parser = new JSONParser();
					Object obj;
					JSONObject object = null;
					try {
						obj = parser.parse(retJson);
						object = (JSONObject) obj;
					}
					catch(Exception e)
					{
						e.printStackTrace();
					}
				  Assert.assertEquals("accept", object.get("Status").toString());

	  }

	 
	@Test
	  public void createpageService() {
		 
			String urlParameters = "pageName=" + "FCI" + "&pageID=" + "1"
					+ "&pageType=" + "X" + "&pagePrivacy=" + "public";
			String retJson = Connection.connect("http://localhost:8888/rest/createpageService", urlParameters, "POST",
					"application/x-www-form-urlencoded;charset=UTF-8");
			 JSONParser parser = new JSONParser();
				Object obj;
				JSONObject object = null;
				try {
					obj = parser.parse(retJson);
					object = (JSONObject) obj;
				}
				catch(Exception e)
				{
					e.printStackTrace();
				}
			  Assert.assertEquals("success", object.get("Status").toString());
	  }
	
	 @Test
	  public void registrationService() {
		  String urlParameters = "uname=" + "reham" + "&email=" + "reham.taie@hotmail.com" + "&password=" + "12345";
		  String retJson = Connection.connect(
					"http://localhost:8888/rest/LoginService", urlParameters,
					"POST", "application/x-www-form-urlencoded;charset=UTF-8");
		  
		    JSONParser parser = new JSONParser();
			Object obj;
			JSONObject object = null;
			try {
				obj = parser.parse(retJson);
				object = (JSONObject) obj;
			}
			catch(Exception e)
			{
				e.printStackTrace();
			}
		  Assert.assertEquals("OK", object.get("Status").toString());

	  }
	 @Test
	  public void loginService() {
		  String urlParameters = "uname=" + "reham" + "&password=" + "12345";
		  String retJson = Connection.connect(
					"http://localhost:8888/rest/LoginService", urlParameters,
					"POST", "application/x-www-form-urlencoded;charset=UTF-8");
		  
		    JSONParser parser = new JSONParser();
			Object obj;
			JSONObject object = null;
			try {
				obj = parser.parse(retJson);
				object = (JSONObject) obj;
			}
			catch(Exception e)
			{
				e.printStackTrace();
			}
		  Assert.assertEquals("OK", object.get("Status").toString());

	    
	  }
	
	 @Test
	  public void conversationService() {
		     
			String urlParameters = "ID=" + "1"+ "&Name=" + "reham";
			String retJson = Connection.connect("http://localhost:8888/rest/conversationService", urlParameters, "POST",
					"application/x-www-form-urlencoded;charset=UTF-8");
			JSONParser parser = new JSONParser();
			Object obj;
			JSONObject object = null;
			try {
				obj = parser.parse(retJson);
				object = (JSONObject) obj;
			}
			catch(Exception e)
			{
				e.printStackTrace();
			}
		   Assert.assertEquals("success", object.get("Status").toString());
	  }
//--------------------------------------------------------------------------------------
  @Test
  public void EnterPageService() {
	 
		String urlParameters = "p=" + "FCI";
		String retJson = Connection.connect("http://localhost:8888/rest/EnterPageService", urlParameters, "POST",
				"application/x-www-form-urlencoded;charset=UTF-8");
	   
	JSONParser parser = new JSONParser();
	Object obj;
	JSONObject object = null;
	try {
		obj = parser.parse(retJson);
		object = (JSONObject) obj;
	}
	catch(Exception e)
	{
		e.printStackTrace();
	}
   Assert.assertEquals("accept", object.get("Status").toString());
  }

 
  @Test
  public void HashtagService() {
	
		String urlParameters = "Hashtag="+ "IT" +"&Post=" + "jkgj" ;
		String retJson = Connection.connect("http://localhost:8888/rest/HashtagService", urlParameters, "POST",
				"application/x-www-form-urlencoded;charset=UTF-8");
		JSONParser parser = new JSONParser();
		Object obj;
		JSONObject object = null;
		try {
			obj = parser.parse(retJson);
			object = (JSONObject) obj;
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	   Assert.assertEquals("accept", object.get("Status").toString());
  }

  @Test
  public void Messageservice() {
		 
		
			String urlParameter = "Sender=" + "reham"
					+ "&Reciever=" + "sara"+ "&message= " + "hiii";
			String retJson2 = Connection.connect("http://localhost:8888/rest/Messageservice", urlParameter, "POST",
					"application/x-www-form-urlencoded;charset=UTF-8");
			JSONParser parser = new JSONParser();
			Object obj;
			JSONObject object = null;
			
			
		   Assert.assertEquals("ok", object.get("Status").toString());
	  
  }

  @Test
  public void PageService() {
	  String serviceUrl = "http://localhost:8888/rest/PageService";
		String urlParameters = "PostType="+"kjgk"+"&TimelineName=" + "kgkhg" +
				"&Post=" + "jhfgk" +"&Feeling=" + "kjhkjg"+ "&Privacy=" + "itikyfi";
		String retJson = Connection.connect(serviceUrl, urlParameters, "POST",
				"application/x-www-form-urlencoded;charset=UTF-8");
		JSONParser parser = new JSONParser();
		Object obj;
		JSONObject object = null;
		
		
	   Assert.assertEquals("accept", object.get("Status").toString());
  }

 
  @Test
  public void signoutService() {
	  JSONParser parser = new JSONParser();
		Object obj;
		JSONObject object = null;
		
		
	   Assert.assertEquals("success", object.get("Status").toString());
  }
}
