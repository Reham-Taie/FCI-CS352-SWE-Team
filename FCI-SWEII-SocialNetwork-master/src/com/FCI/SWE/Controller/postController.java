package com.FCI.SWE.Controller;

import javax.ws.rs.FormParam;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;

import org.glassfish.jersey.server.mvc.Viewable;
@Path("/")
@Produces("text/html")
public class postController {
	
	/**
	 * 
	 * @param post
	 * @param Post_To
	 * @return
	 */
	@POST
	@Path("/post")
	@Produces("text/html")
	public Response post(@FormParam("post") String post,
			@FormParam("Post_To") String Post_To) {
		String serviceUrl = "http://localhost:8888/rest/postservice";
		String urlParameter = "post=" + post + "&Post_To= " + Post_To;

		String retJson2 = Connection.connect(serviceUrl, urlParameter, "POST",
				"application/x-www-form-urlencoded;charset=UTF-8");

		return Response.ok(new Viewable("/jsp/timeline")).build();
	}
	/**
	 * 
	 * @return
	 */
	@POST
	@Path("tlpost")
	public Response tlpost() {

		return Response.ok(new Viewable("/jsp/timelinePost")).build();
	}

	/**
	 * 
	 * @return
	 */
	@POST
	@Path("ppost")
	public Response ppost() {

		return Response.ok(new Viewable("/jsp/PagePost")).build();
	}

	/**
	 * 
	 * @return
	 */
	@POST
	@Path("htag")
	public Response htag() {

		return Response.ok(new Viewable("/jsp/hashtag")).build();
	}


    /**
     * 
     * @param PostType
     * @param TimelineName
     * @param Post
     * @param Feeling
     * @param Privacy
     * @return
     */
	@POST
	@Path("/TimelinePost")
	@Produces("text/html")
	public Response TimelinePost(@FormParam("PostType") String PostType,
			@FormParam("TimelineName") String TimelineName,
			@FormParam("Post") String Post,
			@FormParam("Feeling") String Feeling,
			@FormParam("Privacy") String Privacy) {
		String serviceUrl = "http://localhost:8888/rest/TimelinePostService";
		String urlParameters = "PostType=" + PostType + "&TimelineName="
				+ TimelineName + "&Post=" + Post + "&Feeling=" + Feeling
				+ "&Privacy=" + Privacy;
		String retJson = Connection.connect(serviceUrl, urlParameters, "POST",
				"application/x-www-form-urlencoded;charset=UTF-8");
		return Response.ok(new Viewable("/jsp/home")).build();
	}


	/**
	 * 
	 * @param Hashtag
	 * @param Post
	 * @return
	 */
	@POST
	@Path("/Hashtag")
	@Produces("text/html")
	public Response Hashtag(@FormParam("Hashtag") String Hashtag,
			@FormParam("Post") String Post) {
		String serviceUrl = "http://localhost:8888/rest/HashtagService";
		String urlParameters = "Hashtag=" + Hashtag + "&Post=" + Post;
		String retJson = Connection.connect(serviceUrl, urlParameters, "POST",
				"application/x-www-form-urlencoded;charset=UTF-8");
		return Response.ok(new Viewable("/jsp/home")).build();
	}

	/**
	 * 
	 * @param Hashtag
	 * @return
	 */
	@POST
	@Path("/Hashtagcount")
	@Produces("text/html")
	public Response Hashtag(@FormParam("Hashtag") String Hashtag) {
		String serviceUrl = "http://localhost:8888/rest/HashtagcountService";
		String urlParameters = "Hashtag=" + Hashtag;
		String retJson = Connection.connect(serviceUrl, urlParameters, "POST",
				"application/x-www-form-urlencoded;charset=UTF-8");
		return Response.ok(new Viewable("/jsp/home")).build();
	}


}
