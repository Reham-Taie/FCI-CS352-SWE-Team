package com.FCI.SWE.ServicesModels;

import java.util.List;

import com.google.appengine.api.datastore.DatastoreService;
import com.google.appengine.api.datastore.DatastoreServiceFactory;
import com.google.appengine.api.datastore.Entity;
import com.google.appengine.api.datastore.FetchOptions;
import com.google.appengine.api.datastore.PreparedQuery;
import com.google.appengine.api.datastore.Query;

public class TimelinePost extends PostType {
	private Privacy c;

	String TimelineName;

	String Feeling;

	public void setTimelineName(String timelineName) {
		TimelineName = timelineName;
	}

	

	public void setFeeling(String feeling) {
		Feeling = feeling;
	}

	public void create() {

		DatastoreService datastore = DatastoreServiceFactory
				.getDatastoreService();

		Query gaeQuery = new Query("TimelinePosts");
		PreparedQuery pq = datastore.prepare(gaeQuery);
		List<Entity> list = pq.asList(FetchOptions.Builder.withDefaults());

		Entity employee = new Entity("TimelinePosts", list.size() + 2);
		employee.setProperty("TimelineName", TimelineName);
		employee.setProperty("Post", Post);
		employee.setProperty("Feeling", Feeling);
		employee.setProperty("Privacy", Privacy);

		datastore.put(employee);

	}
}
