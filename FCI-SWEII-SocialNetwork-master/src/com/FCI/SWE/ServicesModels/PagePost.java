package com.FCI.SWE.ServicesModels;

import java.util.List;



import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.google.appengine.api.datastore.DatastoreService;
import com.google.appengine.api.datastore.DatastoreServiceFactory;
import com.google.appengine.api.datastore.Entity;
import com.google.appengine.api.datastore.FetchOptions;
import com.google.appengine.api.datastore.PreparedQuery;
import com.google.appengine.api.datastore.Query;

public class PagePost extends PostType{
	private  Privacy c;
	public static int count1=0;
	
	public void create() {
		
		
	
		DatastoreService datastore = DatastoreServiceFactory
				.getDatastoreService();

		Query gaeQuery = new Query("PagePosts");
		PreparedQuery pq = datastore.prepare(gaeQuery);
		List<Entity> list = pq.asList(FetchOptions.Builder.withDefaults());

		Entity employee = new Entity("PagePosts", list.size() + 2);
		employee.setProperty("TimelineName", TimelineName);
		employee.setProperty("Post", Post);
		employee.setProperty("Feeling", Feeling);
		employee.setProperty("Privacy", Privacy);
		employee.setProperty("Number_Of_seen", count1);

		datastore.put(employee);

	}
}
