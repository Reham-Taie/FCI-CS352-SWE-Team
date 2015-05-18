package com.FCI.SWE.Services;

import javax.ws.rs.FormParam;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.json.simple.JSONObject;

import com.FCI.SWE.ServicesModels.RequestEntity;
@Path("/")
@Produces(MediaType.TEXT_PLAIN)
public class RequestService {

	/**
	 * 
	 * @param uemail
	 * @return
	 */
	@POST
	@Path("/sendrequest")
	public String sendrequestService(@FormParam("femail") String uemail) {
		JSONObject object = new JSONObject();
		RequestEntity.friend(uemail);

		object.put("status", " success");
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
		RequestEntity.getrequest(semail);

		object.put("status", " accept");
		return object.toString();

	}


}
