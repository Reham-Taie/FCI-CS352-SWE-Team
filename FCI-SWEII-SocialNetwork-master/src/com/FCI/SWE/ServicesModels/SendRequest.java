package com.FCI.SWE.ServicesModels;

import com.FCI.SWE.Models.User;
import com.google.appengine.api.datastore.DatastoreService;
import com.google.appengine.api.datastore.DatastoreServiceFactory;
import com.google.appengine.api.datastore.Entity;
import com.google.appengine.api.datastore.PreparedQuery;
import com.google.appengine.api.datastore.Query;

public class SendRequest implements command {

	@Override
	public void notify(String X) {
		// TODO Auto-generated method stub
		X = User.getCurrentActiveUser().getEmail().toString();
		DatastoreService datastore = DatastoreServiceFactory
				.getDatastoreService();

		Query gaeQuery = new Query("friends");
		PreparedQuery pq = datastore.prepare(gaeQuery);
		for (Entity entity : pq.asIterable()) {
			if (entity.getProperty("friendEmail").toString().equals(X)
					&& entity.getProperty("status").toString().equals("false")) {

				String noti="You have a friend request from"+entity.getProperty("myEmail").toString();

				Receiver.SaveNotification(noti);

			}
			

		
		}
		
	}

	
}
