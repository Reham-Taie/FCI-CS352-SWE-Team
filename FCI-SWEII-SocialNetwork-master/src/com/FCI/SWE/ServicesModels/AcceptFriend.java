package com.FCI.SWE.ServicesModels;

import com.FCI.SWE.Models.User;
import com.google.appengine.api.datastore.DatastoreService;
import com.google.appengine.api.datastore.DatastoreServiceFactory;
import com.google.appengine.api.datastore.Entity;
import com.google.appengine.api.datastore.PreparedQuery;
import com.google.appengine.api.datastore.Query;

public class AcceptFriend implements command
{

	@Override
	public void notify(String X) {
		// TODO Auto-generated method stub
		X = User.getCurrentActiveUser().getEmail().toString();
		DatastoreService datastore = DatastoreServiceFactory
				.getDatastoreService();

		Query gaeQuery = new Query("friends");
		PreparedQuery pq = datastore.prepare(gaeQuery);
		for (Entity entity : pq.asIterable()) {
			if (entity.getProperty("FriendEmail").toString().equals(X)
					&& entity.getProperty("status").toString().equals("accept")) {

				String noti="your request from"+entity.getProperty("myEmail").toString()+"is accepted";

				Receiver.SaveNotification(noti);

			}
			
		

		
	}
		
	}

	
		
	

}
