package com.FCI.SWE.ServicesModels;

import java.util.List;

import com.FCI.SWE.Models.User;
import com.google.appengine.api.datastore.DatastoreService;
import com.google.appengine.api.datastore.DatastoreServiceFactory;
import com.google.appengine.api.datastore.Entity;
import com.google.appengine.api.datastore.FetchOptions;
import com.google.appengine.api.datastore.PreparedQuery;
import com.google.appengine.api.datastore.Query;

public class pageEntity {
	
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
	public pageEntity(String name, String email, String password) {
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
     * this function create table pages_ and save the pages in it
     * @param pageName
     * @param pageID
     * @param pageType
     * @param pagePrivacy
     * @return
     */
	public static boolean savepage(String pageName, String pageID,
			String pageType, String pagePrivacy) {

		DatastoreService datastore = DatastoreServiceFactory
				.getDatastoreService();

		Query gaeQuery = new Query("Pages_");
		PreparedQuery pq = datastore.prepare(gaeQuery);
		List<Entity> list = pq.asList(FetchOptions.Builder.withDefaults());

		Entity employee = new Entity("Pages_", list.size() + 2);
		employee.setProperty("Admin", User.getCurrentActiveUser().getName());
		employee.setProperty("Page_Name", pageName);
		employee.setProperty("Page_ID", pageID);
		employee.setProperty("Page_Type", pageType);
		employee.setProperty("Page_Privacy", pagePrivacy);
		employee.setProperty("Number_Of_Likes", count);

		datastore.put(employee);
		return true;

	}
   /**
    * this function take the page name and like it
    * @param p
    *    page name
    * @return
    */
	public static boolean like(String p) {

		DatastoreService datastore = DatastoreServiceFactory
				.getDatastoreService();

		Query gaeQuery = new Query("Pages_");
		PreparedQuery pq = datastore.prepare(gaeQuery);
		for (Entity entity : pq.asIterable()) {
			System.out.println("ffffff");
			if (entity.getProperty("Page_Name").toString().equals(p.trim())) {

				entity.setProperty("Number_Of_Likes", Integer.parseInt(entity
						.getProperty("Number_Of_Likes").toString()) + 1);

				datastore.put(entity);
				return true;

			}

		}

		return false;

	}
   /**
    *this function open the page and increase the number of seen of the post 
    * @param p
    *    page name
    * @return
    */
	public static boolean Enter(String p) {

		DatastoreService datastore = DatastoreServiceFactory
				.getDatastoreService();

		Query gaeQuery = new Query("PagePosts");
		PreparedQuery pq = datastore.prepare(gaeQuery);
		for (Entity entity : pq.asIterable()) {
			if (entity.getProperty("TimelineName").toString().equals(p)) {

				entity.setProperty("Number_Of_seen", Integer.parseInt(entity
						.getProperty("Number_Of_seen").toString()) + 1);

				datastore.put(entity);
				return true;
			}

		}

		return false;
	}

}
