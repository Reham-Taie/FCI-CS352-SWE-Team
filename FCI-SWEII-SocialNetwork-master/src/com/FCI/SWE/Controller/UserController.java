package com.FCI.SWE.Controller;

import java.util.HashMap;
import java.util.Map;
import java.util.Vector;

import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.glassfish.jersey.server.mvc.Viewable;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import com.FCI.SWE.Models.User;
import com.FCI.SWE.ServicesModels.UserEntity;

/**
 * This class contains REST services, also contains action function for web
 * application
 * 
 * @author Mohamed Samir
 * @version 1.0
 * @since 2014-02-12
 *
 */
@Path("/")
@Produces("text/html")
public class UserController {
	/**
	 * Action function to render Signup page, this function will be executed
	 * using url like this /rest/signup
	 * 
	 * @return sign up page
	 */
	@POST
	@Path("/doSearch")
	public Response usersList(@FormParam("uname") String uname) {
		System.out.println(uname);
		String serviceUrl = "http://localhost:8888/rest/SearchService";
		String urlParameters = "uname=" + uname;
		String retJson = Connection.connect(serviceUrl, urlParameters, "POST",
				"application/x-www-form-urlencoded;charset=UTF-8");

		return null;
	}

	@GET
	@Path("/signup")
	public Response signUp() {
		return Response.ok(new Viewable("/jsp/register")).build();
	}

	@GET
	@Path("/search")
	public Response search() {
		return Response.ok(new Viewable("/jsp/search")).build();
	}

	/**
	 * Action function to render home page of application, home page contains
	 * only signup and login buttons
	 * 
	 * @return enty point page (Home page of this application)
	 */

	@GET
	@Path("/")
	public Response index() {
		return Response.ok(new Viewable("/jsp/entryPoint")).build();
	}

	/**
	 * Action function to render login page this function will be executed using
	 * url like this /rest/login
	 * 
	 * @return login page
	 */
	@GET
	@Path("/login")
	public Response login() {
		return Response.ok(new Viewable("/jsp/login")).build();
	}

	/**
	 * Action function to response to signup request, This function will act as
	 * a controller part and it will calls RegistrationService to make
	 * registration
	 * 
	 * @param uname
	 *            provided user name
	 * @param email
	 *            provided user email
	 * @param pass
	 *            provided user password
	 * @return Status string
	 */

	@GET
	@Path("/convers")
	public Response conv() {
		return Response.ok(new Viewable("/jsp/conversation")).build();
	}

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

		/*
		 * UserEntity user = new UserEntity(uname, email, pass);
		 * user.saveUser(); return uname;
		 */
		return "Failed";
	}

	/**
	 * Action function to response to login request. This function will act as a
	 * controller part, it will calls login service to check user data and get
	 * user from datastore
	 * 
	 * @param uname
	 *            provided user name
	 * @param pass
	 *            provided user password
	 * @return Home page view
	 */
	@POST
	@Path("/home")
	@Produces("text/html")
	public Response home(@FormParam("uname") String uname,
			@FormParam("password") String pass) {
		String urlParameters = "uname=" + uname + "&password=" + pass;

		String retJson = Connection.connect(
				"http://localhost:8888/rest/LoginService", urlParameters,
				"POST", "application/x-www-form-urlencoded;charset=UTF-8");

		JSONParser parser = new JSONParser();
		Object obj;
		try {
			obj = parser.parse(retJson);
			JSONObject object = (JSONObject) obj;
			if (object.get("Status").equals("Failed"))
				return null;
			Map<String, String> map = new HashMap<String, String>();
			User user = User.getUser(object.toJSONString());
			map.put("name", user.getName());
			map.put("email", user.getEmail());
			return Response.ok(new Viewable("/jsp/home", map)).build();
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		/*
		 * UserEntity user = new UserEntity(uname, email, pass);
		 * user.saveUser(); return uname;
		 */
		return null;

	}

	/**
	 * 
	 * @return
	 */
	@POST
	@Path("/signout")
	@Produces("text/html")
	public Response signout() {
		return Response.ok(new Viewable("/jsp/entryPoint")).build();
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

	/**
	 * 
	 * @return
	 */
	@POST
	@Path("/option")
	@Produces("text/html")
	public Response option() {
		return Response.ok(new Viewable("/jsp/option")).build();
	}

	@POST
	@Path("/requests")
	@Produces("text/html")
	public Response requests() {
		return Response.ok(new Viewable("/jsp/sendrequest")).build();
	}

	/**
	 * 
	 * @return
	 */
	@POST
	@Path("/viewsearch")
	@Produces("text/html")
	public Response viewsearch() {
		return Response.ok(new Viewable("/jsp/enterName")).build();
	}

	/**
	 * 
	 */
	/*
	 * @POST
	 * 
	 * @Path("/searchUser")
	 * 
	 * @Produces("text/html") public Response searchUser(@FormParam("sname")
	 * String sname) {
	 * 
	 * String serviceUrl = "http://localhost:8888/rest/searchUserService";
	 * String urlParameters = "sname=" + sname;
	 * 
	 * String retJson = Connection.connect( serviceUrl, urlParameters, "POST",
	 * "application/x-www-form-urlencoded;charset=UTF-8"); Map<String,
	 * Vector<User>> passedUsers = new HashMap<String, Vector<User>>();
	 * JSONParser parser = new JSONParser(); Object obj; try { JSONArray array =
	 * (JSONArray) parser.parse(retJson); Vector<User> users = new
	 * Vector<User>(); for (int i = 0; i < array.size(); i++) {
	 * 
	 * JSONObject object; object = (JSONObject) array.get(i);
	 * users.add(User.parseUserInfo( object.toJSONString() ) ); }
	 * 
	 * passedUsers.put("userList", users); return Response.ok(new
	 * Viewable("/jsp/showUsers", passedUsers)) .build(); } catch
	 * (ParseException e) { // TODO Auto-generated catch block
	 * e.printStackTrace(); }
	 * 
	 * return null;
	 * 
	 * }
	 */

	/**
	 * 
	 * @return
	 */
	@GET
	@Path("/message")
	public Response message() {
		return Response.ok(new Viewable("/jsp/sendmessage")).build();
	}

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

	// ------------------------------------------------------------------------------------------------------------
	/**
	 * 
	 * @return
	 */
	@GET
	@Path("/chatmessage")
	public Response chatmessage() {
		return Response.ok(new Viewable("/jsp/Groupmessage.jsp")).build();
	}

	/* /////////////////////////// */
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

	/*
	 * @POST
	 * 
	 * @Path("/msgcheck")
	 * 
	 * @Produces("text/html") public Response msgcontent(@FormParam("ID") String
	 * ID,@FormParam("Message") String Message) { String serviceUrl =
	 * "http://localhost:8888/rest/msgcontentService"; String urlParameters
	 * ="ID=" + ID + "&Message=" + Message; String retJson =
	 * Connection.connect(serviceUrl, urlParameters, "POST",
	 * "application/x-www-form-urlencoded;charset=UTF-8"); return
	 * Response.ok(new Viewable("/jsp/messagecontent")).build(); }
	 */

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
	@Path("/back")
	public Response back() {
		return Response.ok(new Viewable("/jsp/home")).build();
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

	// -------------------------------------------------------------------------------------------------------
	/**
	 * 
	 * @param uname
	 * @param message
	 * @param Groupid
	 * @return
	 */
	@POST
	@Path("/Groupmessage")
	@Produces("text/html")
	public String Groupmessage(@FormParam("uname") String uname,
			@FormParam("message") String message,
			@FormParam("Groupid") String Groupid) {
		String serviceUrl = "http://localhost:8888/rest/GroupmessageService";
		String urlParameter = "Sender=" + User.getCurrentActiveUser().getName()
				+ "&Groupid=" + Groupid + "&message= " + message;
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

	// -----------------------------------------------------------------------------------------------------------
	/**
	 * 
	 * @param uname
	 * @param message
	 * @param Groupid
	 * @return
	 */
	@POST
	@Path("/Groupmessagechat")
	@Produces("text/html")
	public String Groupmessagechat(@FormParam("uname") String uname,
			@FormParam("message") String message,
			@FormParam("Groupid") String Groupid) {
		String serviceUrl = "http://localhost:8888/rest/GroupmessageService";
		String urlParameter = "Sender=" + User.getCurrentActiveUser().getName()
				+ "&Groupid=" + Groupid + "&message= " + message;
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
	// -------------------------------------------------------------------------------------------------------------

}
