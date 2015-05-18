package com.FCI.SWE.Services;

import javax.ws.rs.FormParam;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.json.simple.JSONObject;

import com.FCI.SWE.ServicesModels.MessageEntity;

@Path("/")
@Produces(MediaType.TEXT_PLAIN)
public class MessageService {
	

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
		MessageEntity.conversation(ID, Name);

		object.put("status", " success");
		// System.out.print(uemail);
		// object.put("status", "accept");
		return object.toString();

	}

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
		MessageEntity.msgcheck(ID, Message);

		return object.toString();

	}

	
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
		MessageEntity user2 = new MessageEntity(Sender, Reciever, message);
		user2.savemessage();
		JSONObject object = new JSONObject();
		object.put("Status", "OK");
		return object.toString();
	}

}
