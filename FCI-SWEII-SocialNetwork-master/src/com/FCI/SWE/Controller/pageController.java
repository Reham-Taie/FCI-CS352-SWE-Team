package com.FCI.SWE.Controller;

import javax.ws.rs.FormParam;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;

import org.glassfish.jersey.server.mvc.Viewable;
@Path("/")
@Produces("text/html")                                                               
public class pageController {
	
	/**
	 * 
	 * @return
	 */
	@POST
	@Path("/timeline")
	@Produces("text/html")
	public Response time() {
		return Response.ok(new Viewable("/jsp/timeline")).build();
	}
	/**
	 * 
	 * @return
	 */
	@POST
	@Path("/page")
	public Response page() {
		return Response.ok(new Viewable("/jsp/createpage")).build();
	}

	/**
	 * 
	 * @return
	 */
	@POST
	@Path("likepage")
	public Response likepage() {
		return Response.ok(new Viewable("/jsp/likepage")).build();
	}
	
	/**
	 * 
	 * @param pageName
	 * @param pageID
	 * @param pageType
	 * @param pagePrivacy
	 * @return
	 */
	@POST
	@Path("/createpage")
	@Produces("text/html")
	public Response createpage(@FormParam("pageName") String pageName,
			@FormParam("pageID") String pageID,
			@FormParam("pageType") String pageType,
			@FormParam("pagePrivacy") String pagePrivacy) {
		String serviceUrl = "http://localhost:8888/rest/createpageService";
		String urlParameters = "pageName=" + pageName + "&pageID=" + pageID
				+ "&pageType=" + pageType + "&pagePrivacy=" + pagePrivacy;
		String retJson = Connection.connect(serviceUrl, urlParameters, "POST",
				"application/x-www-form-urlencoded;charset=UTF-8");
		return Response.ok(new Viewable("/jsp/pageDone")).build();
	}

	/**
	 * 
	 * @param p
	 *        page name
	 * @return
	 */
	@POST
	@Path("/likelikepage")
	@Produces("text/html")
	public Response likelikepage(@FormParam("p") String p) {
		String serviceUrl = "http://localhost:8888/rest/likepageService";
		String urlParameters = "p=" + p;
		String retJson = Connection.connect(serviceUrl, urlParameters, "POST",
				"application/x-www-form-urlencoded;charset=UTF-8");
		return Response.ok(new Viewable("/jsp/home")).build();
	}

	/**
	 * 
	 * @param p
	 *      page name 
	 * @return
	 */
	@POST
	@Path("/EnterPage")
	@Produces("text/html")
	public Response EnterPage(@FormParam("p") String p) {
		String serviceUrl = "http://localhost:8888/rest/EnterPageService";
		String urlParameters = "p=" + p;
		String retJson = Connection.connect(serviceUrl, urlParameters, "POST",
				"application/x-www-form-urlencoded;charset=UTF-8");
		return Response.ok(new Viewable("/jsp/home")).build();
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
	@Path("/PagePost")
	@Produces("text/html")
	public Response PagePost(@FormParam("PostType") String PostType,
			@FormParam("TimelineName") String TimelineName,
			@FormParam("Post") String Post,
			@FormParam("Feeling") String Feeling,
			@FormParam("Privacy") String Privacy) {
		String serviceUrl = "http://localhost:8888/rest/PageService";
		String urlParameters = "PostType=" + PostType + "&TimelineName="
				+ TimelineName + "&Post=" + Post + "&Feeling=" + Feeling
				+ "&Privacy=" + Privacy;
		String retJson = Connection.connect(serviceUrl, urlParameters, "POST",
				"application/x-www-form-urlencoded;charset=UTF-8");
		return Response.ok(new Viewable("/jsp/home")).build();
	}

	

}
