package com.FCI.SWE.Controller;

import javax.ws.rs.FormParam;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.glassfish.jersey.server.mvc.Viewable;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
@Path("/")
@Produces("text/html")
public class RequestController {
	
	/**
	 * 
	 * @param uname
	 * @param email
	 * @param pass
	 * @return
	 */
	@POST
	@Path("/response")
	@Produces(MediaType.TEXT_PLAIN)
	public String response(@FormParam("uname") String uname,
			@FormParam("email") String email, @FormParam("password") String pass) {

		String serviceUrl = "http://localhost:8888/rest/RegistrationService";
		String urlParameters = "uname=" + uname + "&email=" + email
				+ "&password=" + pass;
		String retJson = Connection.connect(serviceUrl, urlParameters, "POST",
				"application/x-www-form-urlencoded;charset=UTF-8");
		JSONParser parser = new JSONParser();
		Object obj;
		try {
			// System.out.println(retJson);
			obj = parser.parse(retJson);
			JSONObject object = (JSONObject) obj;
			if (object.get("Status").equals("OK"))
				return "Registered Successfully";

		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		
		return "Failed";
	}

	
	/**
	 * 
	 * @param femail
	 * @return
	 */
	@POST
	@Path("/sendrequest")
	@Produces("text/html")
	public Response sendrequest(@FormParam("femail") String femail) {
		String serviceUrl = "http://localhost:8888/rest/sendrequest";
		String urlParameters = "femail=" + femail;
		String retJson = Connection.connect(serviceUrl, urlParameters, "POST",
				"application/x-www-form-urlencoded;charset=UTF-8");
		return Response.ok(new Viewable("/jsp/send")).build();
	}

	/**
	 * 
	 * @param semail
	 * @return
	 */
	@POST
	@Path("/acceptrequest")
	@Produces("text/html")
	public Response acceptrequest(@FormParam("semail") String semail) {
		String serviceUrl = "http://localhost:8888/rest/acceptrequest";
		String urlParameters = "semail=" + semail;
		String retJson = Connection.connect(serviceUrl, urlParameters, "POST",
				"application/x-www-form-urlencoded;charset=UTF-8");
		return Response.ok(new Viewable("/jsp/accept")).build();
	}

	/**
	 * 
	 * @return
	 */
	@POST
	@Path("/accept")
	@Produces("text/html")
	public Response accept() {
		return Response.ok(new Viewable("/jsp/home")).build();
	}

	@POST
	@Path("/requests")
	@Produces("text/html")
	public Response requests() {
		return Response.ok(new Viewable("/jsp/sendrequest")).build();
	}

}
