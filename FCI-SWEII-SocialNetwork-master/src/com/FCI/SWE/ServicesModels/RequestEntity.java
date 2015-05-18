package com.FCI.SWE.ServicesModels;

import java.util.List;

import com.FCI.SWE.Models.User;
import com.google.appengine.api.datastore.DatastoreService;
import com.google.appengine.api.datastore.DatastoreServiceFactory;
import com.google.appengine.api.datastore.Entity;
import com.google.appengine.api.datastore.FetchOptions;
import com.google.appengine.api.datastore.PreparedQuery;
import com.google.appengine.api.datastore.Query;



public class RequestEntity {
	
	/**
	 * this function create table for friends by the email, 
	 * @param friendEmail
	 */
	public static boolean friend(String friendEmail) {
		DatastoreService datastore = DatastoreServiceFactory
				.getDatastoreService();

		Query gaeQuery = new Query("friends");
		PreparedQuery pq = datastore.prepare(gaeQuery);
		List<Entity> list = pq.asList(FetchOptions.Builder.withDefaults());

		Entity employee = new Entity("friends", list.size() + 2);
		employee.setProperty("friendEmail", friendEmail);

		employee.setProperty("myEmail", User.getCurrentActiveUser().getEmail()
				.toString());
		employee.setProperty("status", "false");

		datastore.put(employee);

		return true;
	}

	/**
	 * this function search by email in table friends and accept the request 
	 * @param semail
	 * @return
	 */
	public static void getrequest(String semail) {
		String myEmail = User.getCurrentActiveUser().getEmail().toString();
		DatastoreService datastore = DatastoreServiceFactory
				.getDatastoreService();

		Query gaeQuery = new Query("friends");
		PreparedQuery pq = datastore.prepare(gaeQuery);
		for (Entity entity : pq.asIterable()) {
			if (entity.getProperty("friendEmail").toString().equals(myEmail)
					&& entity.getProperty("myEmail").toString().equals(semail)) {

				entity.setProperty("status", "accept");

				datastore.put(entity);

			}
			System.out.println("friendemail"
					+ entity.getProperty("friendEmail").toString());
		}

	}

}
