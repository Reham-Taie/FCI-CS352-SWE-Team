package com.FCI.SWE.ServicesModels;

import com.FCI.SWE.Models.User;
import com.google.appengine.api.datastore.DatastoreService;
import com.google.appengine.api.datastore.DatastoreServiceFactory;
import com.google.appengine.api.datastore.Entity;
import com.google.appengine.api.datastore.PreparedQuery;
import com.google.appengine.api.datastore.Query;

public  class Message implements command  {

	@Override
	public void notify(String X) {
		// TODO Auto-generated method stub
		
			X = User.getCurrentActiveUser().getEmail().toString();
			DatastoreService datastore = DatastoreServiceFactory
					.getDatastoreService();

			Query gaeQuery = new Query("Message");
			PreparedQuery pq = datastore.prepare(gaeQuery);
			for (Entity entity : pq.asIterable()) {
				if (entity.getProperty("Sender").toString().equals(X)
						&& !entity.getProperty("Message").toString().equals(null)) {

					String noti="You have a message from"+entity.getProperty("Sender").toString();

					Receiver.SaveNotification(noti);

				}
				
			

			
		}
		
	}

	
	

}
