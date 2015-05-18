package com.FCI.SWE.Services;

import javax.ws.rs.FormParam;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.json.simple.JSONObject;

import com.FCI.SWE.ServicesModels.pageEntity;
import com.FCI.SWE.ServicesModels.postEntity;

@Path("/")
@Produces(MediaType.TEXT_PLAIN)
public class pageService {
	
	/**
	 * 
	 * @param pageName
	 * @param pageID
	 * @param pageType
	 * @param pagePrivacy
	 * @return
	 */
	@POST
	@Path("/createpageService")
	public String createpageService(@FormParam("pageName") String pageName,
			@FormParam("pageID") String pageID,
			@FormParam("pageType") String pageType,
			@FormParam("pagePrivacy") String pagePrivacy) {
		JSONObject object = new JSONObject();
		pageEntity.savepage(pageName, pageID, pageType, pagePrivacy);

		object.put("status", " success");
		// System.out.print(uemail);
		// object.put("status", "accept");
		return object.toString();

	}
	/**
	 * 
	 * @param p
	 * @return
	 */

	@POST
	@Path("/likepageService")
	public String likepageService(@FormParam("p") String p) {
		JSONObject object = new JSONObject();
		pageEntity.like(p);

		object.put("status", " accept");
		return object.toString();
	}

	/**
	 * 
	 * @param p
	 * @return
	 */
	@POST
	@Path("/EnterPageService")
	public String EnterPageService(@FormParam("p") String p) {
		JSONObject object = new JSONObject();
		pageEntity.Enter(p);

		object.put("status", " accept");
		return object.toString();
	}
	/**
	 * 
	 * @param PostType
	 * @param TimelineName
	 * @param Post
	 * @param Feeling
	 * @param Privacy
	 * @return
	 * @throws InstantiationException
	 * @throws IllegalAccessException
	 * @throws ClassNotFoundException
	 */
	@POST
	@Path("/PageService")
	public String PageService(@FormParam("PostType") String PostType,
			@FormParam("TimelineName") String TimelineName,
			@FormParam("Post") String Post,
			@FormParam("Feeling") String Feeling,
			@FormParam("Privacy") String Privacy)
			throws InstantiationException, IllegalAccessException,
			ClassNotFoundException {
		JSONObject object = new JSONObject();
		// Builder b =new Builder();
		// b.checkType (PostType);
		postEntity.createTimelinePost(PostType, TimelineName, Post, Feeling,
				Privacy);
		// Builder b =new Builder();
		// b.checkType (PostType);
		object.put("status", " accept");
		return object.toString();
	}


}
