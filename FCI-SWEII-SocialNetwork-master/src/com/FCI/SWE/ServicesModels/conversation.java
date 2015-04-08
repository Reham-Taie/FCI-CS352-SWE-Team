package com.FCI.SWE.ServicesModels;

import com.FCI.SWE.Models.User;
import com.google.appengine.api.datastore.DatastoreService;
import com.google.appengine.api.datastore.DatastoreServiceFactory;
import com.google.appengine.api.datastore.Entity;
import com.google.appengine.api.datastore.PreparedQuery;
import com.google.appengine.api.datastore.Query;

public class conversation implements command {

	@Override
	public void notify(String X) {
		// TODO Auto-generated method stub
		
	
			
				X = User.getCurrentActiveUser().getEmail().toString();
				DatastoreService datastore = DatastoreServiceFactory
						.getDatastoreService();

				Query gaeQuery = new Query("conversation");
				PreparedQuery pq = datastore.prepare(gaeQuery);
				for (Entity entity : pq.asIterable()) {
					if (entity.getProperty("Member_Name").toString().equals(X)
							) {

						String noti="You are a member in a conversation number" + entity.getProperty("Conversation_ID").toString();

						Receiver.SaveNotification(noti);

					}
					
				

				
			}
			
		

		
	}

}
