package com.FCI.SWE.Services;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
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
@Produces(MediaType.TEXT_PLAIN)
public class UserServices {

	/*
	 * @GET
	 * 
	 * @Path("/index") public Response index() { return Response.ok(new
	 * Viewable("/jsp/entryPoint")).build(); }
	 */

	/*
	 * @POST
	 * 
	 * @Path("/SearchService") public String searchFriend(@FormParam("uname")
	 * String uname){
	 * 
	 * }
	 */

	/**
	 * Registration Rest service, this service will be called to make
	 * registration. This function will store user data in data store
	 * 
	 * @param uname
	 *            provided user name
	 * @param email
	 *            provided user email
	 * @param pass
	 *            provided password
	 * @return Status json
	 */
	@POST
	@Path("/RegistrationService")
	public String registrationService(@FormParam("uname") String uname,
			@FormParam("email") String email, @FormParam("password") String pass) {
		UserEntity user = new UserEntity(uname, email, pass);
		user.saveUser();
		JSONObject object = new JSONObject();
		object.put("Status", "OK");
		return object.toString();
	}

	/**
	 * Login Rest Service, this service will be called to make login process
	 * also will check user data and returns new user from datastore
	 * 
	 * @param uname
	 *            provided user name
	 * @param pass
	 *            provided user password
	 * @return user in json format
	 */
	@POST
	@Path("/LoginService")
	public String loginService(@FormParam("uname") String uname,
			@FormParam("password") String pass) {
		JSONObject object = new JSONObject();
		UserEntity user = UserEntity.getUser(uname, pass);
		if (user == null) {
			object.put("Status", "Failed");

		} else {
			object.put("Status", "OK");
			object.put("name", user.getName());
			object.put("email", user.getEmail());
			object.put("password", user.getPass());
			object.put("id", user.getId());
		}
		return object.toString();

	}

	@POST
	@Path("/searchUserService")
	public String searchUserService(@FormParam("sname") String sname) {
		Vector<UserEntity> users = UserEntity.searchUser(sname);
		JSONArray returnedJson = new JSONArray();
		for (UserEntity user : users) {
			JSONObject object = new JSONObject();
			object.put("id", user.getId());
			object.put("Name", user.getName());
			object.put("Email", user.getEmail());
			returnedJson.add(object);

		}
		return returnedJson.toJSONString();
		/*
		 * UserEntity.searchUser(sname); if(UserEntity.searchUser(sname)==true)
		 * { object.put("status"," Found");
		 * 
		 * } else { object.put("status"," Not Found"); }
		 * 
		 * 
		 * 
		 * 
		 * 
		 * return object.toString();
		 */

	}

	/**
	 * 
	 * @return
	 */
	@POST
	@Path("/signoutService")
	public String signoutService() {
		User.setCurrentActiveUser();
		JSONObject object = new JSONObject();
		object.put("status", " success");

		return object.toString();

	}

	@POST
	@Path("/sendrequest")
	public String sendrequestService(@FormParam("femail") String uemail) {
		JSONObject object = new JSONObject();
		UserEntity.friend(uemail);

		object.put("status", " success");
		// System.out.print(uemail);
		// object.put("status", "accept");
		return object.toString();

	}

	/* ////////////////////////////////////////// */

	/**
	 * conversation Rest Service, this service will be called to save the names
	 * of the members in the conversation and conversation ID process
	 * 
	 * @param ID
	 * @param Name
	 * @return
	 */
	@POST
	@Path("/conversationService")
	public String conversationService(@FormParam("ID") String ID,
			@FormParam("Name") String Name) {
		JSONObject object = new JSONObject();
		UserEntity.conversation(ID, Name);

		object.put("status", " success");
		// System.out.print(uemail);
		// object.put("status", "accept");
		return object.toString();

	}

	/*
	 * @POST
	 * 
	 * @Path("/msgcontentService") public String
	 * msgcontentService(@FormParam("ID") String ID,@FormParam("Message") String
	 * Message) { JSONObject object = new JSONObject();
	 * UserEntity.msgcontent(ID,Message);
	 * 
	 * object.put("status"," success"); //System.out.print(uemail);
	 * //object.put("status", "accept"); return object.toString();
	 * 
	 * }
	 */
	/**
	 * msgcheckService, this service will save the message that related to a
	 * specific conversation
	 * 
	 * @param ID
	 * @param Message
	 * @return
	 */
	@POST
	@Path("/msgcheckService")
	public String msgcontentService(@FormParam("ID") String ID,
			@FormParam("Message") String Message) {
		JSONObject object = new JSONObject();
		UserEntity.msgcheck(ID, Message);

		// object.put("status"," success");
		// System.out.print(uemail);
		// object.put("status", "accept");
		return object.toString();

	}

	/**
	 * acceptrequest service, in this service the user will enter the email and
	 * check of the request and accept it
	 * 
	 * @param semail
	 * @return
	 */
	@POST
	@Path("/acceptrequest")
	public String acceptrequestService(@FormParam("semail") String semail) {
		JSONObject object = new JSONObject();
		UserEntity.getrequest(semail);

		object.put("status", " accept");
		return object.toString();

	}

	// //////////////////////////////////////////
	/**
	 * Messageservice, this service will send a message to one friend only by
	 * using his name
	 * 
	 * @param Sender
	 * @param Reciever
	 * @param message
	 * @return
	 */
	@POST
	@Path("/Messageservice")
	public String Messageservice(@FormParam("Sender") String Sender,
			@FormParam("Reciever") String Reciever,
			@FormParam("message") String message) {
		System.out.println(message);
		UserEntity user2 = new UserEntity(Sender, Reciever, message);
		user2.savemessage();
		JSONObject object = new JSONObject();
		object.put("Status", "OK");
		return object.toString();
	}
	// ---------------------------------------------------
	/*
	 * @POST
	 * 
	 * @Path("/messageService") public String
	 * messageService(@FormParam("Sender") String Sender,
	 * 
	 * @FormParam("Reciever") String Reciever, @FormParam("message") String
	 * message) { UserEntity user2 = new UserEntity(Sender, Reciever, message);
	 * user2.savemessage(); JSONObject object = new JSONObject();
	 * object.put("message", "OK"); return object.toString(); }
	 */

	// -----------------------------------------------------------------------------------------------------------
	/*
	 * @POST
	 * 
	 * @Path("/Groupmessageservice") public String
	 * Groupmessageservice(@FormParam("Sender") String Sender,
	 * 
	 * @FormParam("Groupid") String Groupid, @FormParam("message") String
	 * message) { System.out.println(message); UserEntity user2 = new
	 * UserEntity(Sender, Groupid, message); user2.savemessage(); JSONObject
	 * object = new JSONObject(); object.put("Status", "OK"); return
	 * object.toString(); }
	 */
	// ---------------------------------------------------
	/*
	 * @POST
	 * 
	 * @Path("/GroupmessageService") public String
	 * GroupmessageService(@FormParam("Sender") String Sender,
	 * 
	 * @FormParam("Groupid") String Groupid, @FormParam("message") String
	 * message) { UserEntity user2 = new UserEntity(Sender, Groupid, message);
	 * user2.saveGroupmessage(); JSONObject object = new JSONObject();
	 * object.put("Status", "OK"); return object.toString(); }
	 */

}
