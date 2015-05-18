package com.FCI.SWE.Controller;

import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;

import org.glassfish.jersey.server.mvc.Viewable;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import com.FCI.SWE.Models.User;
@Path("/")
@Produces("text/html")
public class MessageController {
	
	/**
	 * 
	 * @return
	 */
	@GET
	@Path("/convers")
	public Response conv() {
		return Response.ok(new Viewable("/jsp/conversation")).build();
	}
	
	/**
	 * 
	 * @return
	 */
	@GET
	@Path("/message")
	public Response message() {
		return Response.ok(new Viewable("/jsp/sendmessage")).build();
	}
	/**
	 * 
	 * @param uname
	 * @param message
	 * @return
	 */
	@POST
	@Path("/Sendmessage")
	@Produces("text/html")
	public String Sendmessage(@FormParam("uname") String uname,
			@FormParam("message") String message) {
		String serviceUrl = "http://localhost:8888/rest/Messageservice";
		String urlParameter = "Sender=" + User.getCurrentActiveUser().getName()
				+ "&Reciever=" + uname + "&message= " + message;
		String retJson2 = Connection.connect(serviceUrl, urlParameter, "POST",
				"application/x-www-form-urlencoded;charset=UTF-8");
		JSONParser parser2 = new JSONParser();
		Object obj2;

		try {
			// System.out.println(retJson);
			obj2 = parser2.parse(retJson2);
			JSONObject object2 = (JSONObject) obj2;
			if (object2.get("Status").equals("OK")) {
				// System.out.println("3");
				return "Message has been sent Successfully";
			}

		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	
	/**
	 * 
	 * @return
	 */
	@GET
	@Path("/chatmessage")
	public Response chatmessage() {
		return Response.ok(new Viewable("/jsp/Groupmessage.jsp")).build();
	}

	/**
	 * 
	 * @param ID
	 * @param Name
	 * @return
	 */
	@POST
	@Path("/conversation")
	@Produces("text/html")
	public Response conversation(@FormParam("ID") String ID,
			@FormParam("Name") String Name) {
		String serviceUrl = "http://localhost:8888/rest/conversationService";
		String urlParameters = "ID=" + ID + "&Name=" + Name;
		String retJson = Connection.connect(serviceUrl, urlParameters, "POST",
				"application/x-www-form-urlencoded;charset=UTF-8");
		return Response.ok(new Viewable("/jsp/conversation")).build();
	}
	
	/**
	 * 
	 * @param ID
	 * @param Message
	 * @return
	 */
	@POST
	@Path("/msgcheck")
	@Produces("text/html")
	public Response msgcontent(@FormParam("ID") String ID,
			@FormParam("Message") String Message) {
		String serviceUrl = "http://localhost:8888/rest/msgcheckService";
		String urlParameters = "ID=" + ID + "&Message=" + Message;
		String retJson = Connection.connect(serviceUrl, urlParameters, "POST",
				"application/x-www-form-urlencoded;charset=UTF-8");
		return Response.ok(new Viewable("/jsp/home")).build();
	}
	
	/**
	 * 
	 * @return
	 */
	@POST
	@Path("/conversationpage")
	public Response conversationpage() {
		return Response.ok(new Viewable("/jsp/conversation")).build();
	}

	/**
	 * 
	 * @return
	 */
	@POST
	@Path("/msg")
	public Response msg() {
		return Response.ok(new Viewable("/jsp/msgID")).build();
	}


}
