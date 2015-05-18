package com.FCI.SWE.Services;

import javax.ws.rs.FormParam;
import javax.ws.rs.POST;
import javax.ws.rs.Path;

import org.json.simple.JSONObject;

import com.FCI.SWE.ServicesModels.postEntity;

public class postService {

	
	/**
	 * 
	 * @param post
	 * @param Post_To
	 * @return
	 */
	@POST
	@Path("/postservice")
	public String postservice(@FormParam("post") String post,
			@FormParam("Post_To") String Post_To) {
		JSONObject object = new JSONObject();
		postEntity.savepost(post, Post_To);

		object.put("status", " success");
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
	@Path("/TimelinePostService")
	public String TimelinePostService(@FormParam("PostType") String PostType,
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

	
	/**
	 * 
	 * @param Hashtag
	 * @param Post
	 * @return
	 * @throws InstantiationException
	 * @throws IllegalAccessException
	 * @throws ClassNotFoundException
	 */
	@POST
	@Path("/HashtagService")
	public String HashtagService(@FormParam("Hashtag") String Hashtag,
			@FormParam("Post") String Post) throws InstantiationException,
			IllegalAccessException, ClassNotFoundException {
		JSONObject object = new JSONObject();

		postEntity.createHashtagPost(Hashtag, Post);

		object.put("status", " accept");
		return object.toString();
	}

	/**
	 * 
	 * @param Hashtag
	 * @return
	 */
	@POST
	@Path("/HashtagcountService")
	public String HashtagService(@FormParam("Hashtag") String Hashtag) {
		JSONObject object = new JSONObject();

		postEntity.countHashtagPost(Hashtag);

		object.put("status", " accept");
		return object.toString();
	}


}
