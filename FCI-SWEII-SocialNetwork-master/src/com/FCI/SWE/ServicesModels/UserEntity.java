package com.FCI.SWE.ServicesModels;

import java.util.Date;
import java.util.List;
import java.util.Vector;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import com.FCI.SWE.Models.User;
import com.google.appengine.api.datastore.DatastoreService;
import com.google.appengine.api.datastore.DatastoreServiceFactory;
import com.google.appengine.api.datastore.Entity;
import com.google.appengine.api.datastore.EntityNotFoundException;
import com.google.appengine.api.datastore.FetchOptions;
import com.google.appengine.api.datastore.Key;
import com.google.appengine.api.datastore.PreparedQuery;
import com.google.appengine.api.datastore.Query;
import com.google.appengine.api.datastore.Transaction;

/**
 * <h1>User Entity class</h1>
 * <p>
 * This class will act as a model for user, it will holds user data
 * </p>
 *
 * @author Mohamed Samir
 * @version 1.0
 * @since 2014-02-12
 */
public class UserEntity {
	private String name;
	private String email;
	private String password;
	private long id;

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
	public UserEntity(String name, String email, String password) {
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
	 * 
	 * This static method will form UserEntity class using user name and
	 * password This method will serach for user in datastore
	 * 
	 * @param name
	 *            user name
	 * @param pass
	 *            user password
	 * @return Constructed user entity
	 */

	public static UserEntity getUser(String name, String pass) {
		DatastoreService datastore = DatastoreServiceFactory
				.getDatastoreService();

		Query gaeQuery = new Query("users");
		PreparedQuery pq = datastore.prepare(gaeQuery);
		for (Entity entity : pq.asIterable()) {
			if (entity.getProperty("name").toString().equals(name)
					&& entity.getProperty("password").toString().equals(pass)) {
				UserEntity returnedUser = new UserEntity(entity.getProperty(
						"name").toString(), entity.getProperty("email")
						.toString(), entity.getProperty("password").toString());
				returnedUser.setId(entity.getKey().getId());
				return returnedUser;
			}
		}

		return null;
	}

	/**
	 * 
	 * @param sname
	 * @return
	 */
	public static Vector<UserEntity> searchUser(String sname) {
		DatastoreService datastore = DatastoreServiceFactory
				.getDatastoreService();

		Query gaeQuery = new Query("users");
		PreparedQuery pq = datastore.prepare(gaeQuery);
		Vector<UserEntity> returnedUsers = new Vector<UserEntity>();
		for (Entity entity : pq.asIterable()) {
			String currentName = entity.getProperty("name").toString();
			if (currentName.contains(sname)) {
				UserEntity user = new UserEntity(entity.getProperty("name")
						.toString(), entity.getProperty("email").toString(),
						entity.getProperty("password").toString());
				user.setId(entity.getKey().getId());
				returnedUsers.add(user);
			}
		}

		return returnedUsers;
	}

	/**
	 * This method will be used to save user object in datastore
	 * 
	 * @return boolean if user is saved correctly or not
	 */
	public Boolean saveUser() {
		DatastoreService datastore = DatastoreServiceFactory
				.getDatastoreService();
		Transaction txn = datastore.beginTransaction();
		Query gaeQuery = new Query("users");
		PreparedQuery pq = datastore.prepare(gaeQuery);
		List<Entity> list = pq.asList(FetchOptions.Builder.withDefaults());
		System.out.println("Size = " + list.size());

		try {
			Entity employee = new Entity("users", list.size() + 2);

			employee.setProperty("name", this.name);
			employee.setProperty("email", this.email);
			employee.setProperty("password", this.password);

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
	 * 
	 * @param friendEmail
	 */
	public static void friend(String friendEmail) {
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

		// return true;
	}

	/**
	 * 
	 * @param semail
	 * @return
	 */
	public static UserEntity getrequest(String semail) {
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

		return null;
	}

	// /////////////////////////////////////////////
	/**
	 * 
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

	// ------------------------------------------------------------------------------------------------------------
	/*
	 * public Boolean saveGroupmessage() { DatastoreService datastore =
	 * DatastoreServiceFactory .getDatastoreService(); Transaction txn =
	 * datastore.beginTransaction(); Query gaeQuery = new Query("Groupmessage");
	 * PreparedQuery pq = datastore.prepare(gaeQuery); List<Entity> list =
	 * pq.asList(FetchOptions.Builder.withDefaults());
	 * System.out.println("Size = " + list.size());
	 * 
	 * try { Entity employee = new Entity("Groupmessage", list.size() + 2);
	 * 
	 * employee.setProperty("Sender", this.name);
	 * employee.setProperty("Groupid", this.Groupid);
	 * employee.setProperty("Status", this.password);
	 * 
	 * datastore.put(employee); txn.commit();
	 * 
	 * }finally{ if (txn.isActive()) { txn.rollback(); } } return true; }
	 */
	// -----------------------------------------------------------------------------------------------------------
	/**
	 * @return the groupid
	 */
	/*
	 * public String getGroupid() { return Groupid; } /**
	 * 
	 * @param groupid the groupid to set
	 * 
	 * public void setGroupid(String groupid) { Groupid = groupid; }
	 * //----------
	 * --------------------------------------------------------------
	 * ------------------------------ public Boolean saveGroupid() {
	 * List<com.FCI.SWE.Models.User> UserList= new
	 * ArrayList<com.FCI.SWE.Models.User>(); DatastoreService datastore =
	 * DatastoreServiceFactory .getDatastoreService(); Transaction txn =
	 * datastore.beginTransaction(); Query gaeQuery = new Query("Groupschat");
	 * PreparedQuery pq = datastore.prepare(gaeQuery); List<Entity> list =
	 * pq.asList(FetchOptions.Builder.withDefaults());
	 * System.out.println("Size = " + list.size());
	 * 
	 * try {
	 * 
	 * 
	 * 
	 * 
	 * for (int i=0 ; i< UserList.size(); i++) { Entity employee = new
	 * Entity("Groupschat", list.size() + 2); employee.setProperty("Groupid",
	 * this.Groupid); employee.setProperty("Users", UserList.get(i).getName());
	 * datastore.put(employee); }
	 * 
	 * //employee.setProperty("Groupid", this.Groupid);
	 * //employee.setProperty("Users", UserList.get(i).getname());
	 * 
	 * 
	 * txn.commit();
	 * 
	 * }finally{ if (txn.isActive()) { txn.rollback(); } } return true; }
	 * 
	 * //------------------------------------------------------------------------
	 * ------------------------- public Boolean saveGroupchat() {
	 * DatastoreService datastore = DatastoreServiceFactory
	 * .getDatastoreService(); Transaction txn = datastore.beginTransaction();
	 * Query gaeQuery = new Query("Groupschat"); PreparedQuery pq =
	 * datastore.prepare(gaeQuery); List<Entity> list =
	 * pq.asList(FetchOptions.Builder.withDefaults());
	 * System.out.println("Size = " + list.size());
	 * 
	 * try { Entity employee = new Entity("Groupschat", list.size() + 2);
	 * 
	 * employee.setProperty("Sender", this.name);
	 * employee.setProperty("Groupid", this.Groupid);
	 * employee.setProperty("Status", this.password);
	 * 
	 * datastore.put(employee); txn.commit();
	 * 
	 * }finally{ if (txn.isActive()) { txn.rollback(); } } return true; }
	 * //------
	 * ------------------------------------------------------------------
	 * -------------------
	 */

	/**
	 * 
	 * @param ID
	 * @param Name
	 */
	public static void conversation(String ID, String Name) {
		DatastoreService datastore = DatastoreServiceFactory
				.getDatastoreService();

		Query gaeQuery = new Query("conversation");
		PreparedQuery pq = datastore.prepare(gaeQuery);
		List<Entity> list = pq.asList(FetchOptions.Builder.withDefaults());

		Entity employee = new Entity("conversation", list.size() + 2);
		employee.setProperty("Conversation_ID", ID);

		employee.setProperty("Member_Name", Name);

		datastore.put(employee);

		// return true;
	}

	/*
	 * public static void msgcontent(String ID,String Message) {
	 * DatastoreService datastore = DatastoreServiceFactory
	 * .getDatastoreService();
	 * 
	 * Query gaeQuery = new Query("MessageContent"); PreparedQuery pq =
	 * datastore.prepare(gaeQuery); List<Entity> list =
	 * pq.asList(FetchOptions.Builder.withDefaults());
	 * 
	 * 
	 * Entity employee = new Entity("MessageContent", list.size() + 2);
	 * employee.setProperty("Conversation_ID",ID );
	 * 
	 * employee.setProperty("Sender", User.getCurrentActiveUser().getName());
	 * employee.setProperty("Message", Message);
	 * 
	 * 
	 * 
	 * datastore.put(employee);
	 * 
	 * 
	 * //return true; }
	 */

	/**
	 * 
	 * @param ID
	 * @param Message
	 */
	public static void msgcheck(String ID, String Message) {
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

			}

		}

		// return true;
	}

}
