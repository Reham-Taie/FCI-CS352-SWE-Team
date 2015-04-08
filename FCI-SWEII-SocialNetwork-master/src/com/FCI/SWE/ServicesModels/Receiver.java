package com.FCI.SWE.ServicesModels;

import java.util.List;

import com.FCI.SWE.Models.User;
import com.google.appengine.api.datastore.DatastoreService;
import com.google.appengine.api.datastore.DatastoreServiceFactory;
import com.google.appengine.api.datastore.Entity;
import com.google.appengine.api.datastore.FetchOptions;
import com.google.appengine.api.datastore.PreparedQuery;
import com.google.appengine.api.datastore.Query;

public class Receiver {
	/**
	 * 
	 * @param friendEmail
	 */
	public static void SaveNotification(String Notification) {
		DatastoreService datastore = DatastoreServiceFactory
				.getDatastoreService();

		Query gaeQuery = new Query("Notifications");
		PreparedQuery pq = datastore.prepare(gaeQuery);
		List<Entity> list = pq.asList(FetchOptions.Builder.withDefaults());

		Entity employee = new Entity("Notifications", list.size() + 2);
		employee.setProperty("Notify to", User.getCurrentActiveUser().getEmail()
				.toString());

		employee.setProperty("notification", Notification);
		

		datastore.put(employee);

		// return true;
	}

}
