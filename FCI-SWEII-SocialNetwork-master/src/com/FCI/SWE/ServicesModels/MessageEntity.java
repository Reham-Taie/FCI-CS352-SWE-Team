package com.FCI.SWE.ServicesModels;

import java.util.List;




import com.FCI.SWE.Models.User;
import com.google.appengine.api.datastore.DatastoreService;
import com.google.appengine.api.datastore.DatastoreServiceFactory;
import com.google.appengine.api.datastore.Entity;
import com.google.appengine.api.datastore.FetchOptions;
import com.google.appengine.api.datastore.PreparedQuery;
import com.google.appengine.api.datastore.Query;
import com.google.appengine.api.datastore.Transaction;

public class MessageEntity {
	
	private String name;
	private String email;
	private String password;
	private long id;
	public static int count = 0;

	/**
	 * Constructor accepts user data
	 * 
	 * @param name
	 *            user name
	 * @param email
	 *            user email
	 * @param password
	 *            user provided password
	 */
	public MessageEntity(String name, String email, String password) {
		this.name = name;
		this.email = email;
		this.password = password;
	}

	private void setId(long id) {
		this.id = id;
	}

	public long getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public String getEmail() {
		return email;
	}

	public String getPass() {
		return password;
	}
	
	

	/**
	 * this function create table message to save the conversation 
	 * @return
	 */
	public Boolean savemessage() {
		DatastoreService datastore = DatastoreServiceFactory
				.getDatastoreService();
		Transaction txn = datastore.beginTransaction();
		Query gaeQuery = new Query("Message");
		PreparedQuery pq = datastore.prepare(gaeQuery);
		List<Entity> list = pq.asList(FetchOptions.Builder.withDefaults());
		System.out.println("Size = " + list.size());

		try {
			Entity employee = new Entity("Message", list.size() + 2);

			employee.setProperty("Sender", this.name);
			employee.setProperty("Reciever", this.email);
			employee.setProperty("Status", this.password);

			datastore.put(employee);
			txn.commit();

		} finally {
			if (txn.isActive()) {
				txn.rollback();
			}
		}
		return true;
	}


	/**
	 * this function create table conversation to save conversation ID and the members of the conversation
	 * @param ID
	 * @param Name
	 */
	public static boolean conversation(String ID, String Name) {
		DatastoreService datastore = DatastoreServiceFactory
				.getDatastoreService();

		Query gaeQuery = new Query("conversation");
		PreparedQuery pq = datastore.prepare(gaeQuery);
		List<Entity> list = pq.asList(FetchOptions.Builder.withDefaults());

		Entity employee = new Entity("conversation", list.size() + 2);
		employee.setProperty("Conversation_ID", ID);

		employee.setProperty("Member_Name", Name);

		datastore.put(employee);

		return true;
	}

	/**
	 * this function save the messages in the conversation table
	 * @param ID
	 * @param Message
	 */
	public static boolean msgcheck(String ID, String Message) {
		DatastoreService datastore = DatastoreServiceFactory
				.getDatastoreService();

		Query gaeQuery = new Query("conversation");
		PreparedQuery pq = datastore.prepare(gaeQuery);
		List<Entity> list = pq.asList(FetchOptions.Builder.withDefaults());

		Entity employee = new Entity("conversation", list.size() + 2);

		for (Entity entity : pq.asIterable()) {

			if (entity.getProperty("Conversation_ID").equals(ID)
					&& entity.getProperty("Member_Name").equals(
							User.getCurrentActiveUser().getName())) {

				// entity.setProperty("status", "accept");

				DatastoreService datastor = DatastoreServiceFactory
						.getDatastoreService();

				Query Query = new Query("MessageContent_");
				PreparedQuery pq2 = datastor.prepare(Query);
				List<Entity> list1 = pq2.asList(FetchOptions.Builder
						.withDefaults());

				Entity employeee = new Entity("MessageContent_",
						list1.size() + 2);
				employeee.setProperty("Conversation_ID", ID);

				employeee.setProperty("Sender", User.getCurrentActiveUser()
						.getName());
				employeee.setProperty("Message", Message);

				datastor.put(employeee);
				return true;

			}

		}
		return false;

	}

}
