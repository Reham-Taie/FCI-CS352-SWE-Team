package com.FCI.SWE.ServicesModels;

import java.util.List;

import com.FCI.SWE.Models.User;
import com.google.appengine.api.datastore.DatastoreService;
import com.google.appengine.api.datastore.DatastoreServiceFactory;
import com.google.appengine.api.datastore.Entity;
import com.google.appengine.api.datastore.FetchOptions;
import com.google.appengine.api.datastore.Key;
import com.google.appengine.api.datastore.PreparedQuery;
import com.google.appengine.api.datastore.Query;


public class postEntity {
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
	public postEntity(String name, String email, String password) {
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
	 * this function create table posts to save the posts 
	 * @param post
	 *       the post
	 * @param Post_To
	 *          name of the receiver
	 * @return
	 */
	public static Key savepost(String post, String Post_To) {
		DatastoreService datastore = DatastoreServiceFactory
				.getDatastoreService();

		Query gaeQuery = new Query("Posts");
		PreparedQuery pq = datastore.prepare(gaeQuery);
		List<Entity> list = pq.asList(FetchOptions.Builder.withDefaults());

		Entity employee = new Entity("Posts", list.size() + 2);
		employee.setProperty("Post_From", User.getCurrentActiveUser().getName()
				.toString());

		employee.setProperty("Post", post);

		employee.setProperty("Post_To", Post_To);

		Key s = datastore.put(employee);

		return s;
	}
	public static boolean createHashtagPost(String Hashtag, String Post) {

		DatastoreService datastore = DatastoreServiceFactory
				.getDatastoreService();

		Query gaeQuery = new Query("HashtagTable");
		PreparedQuery pq = datastore.prepare(gaeQuery);
		List<Entity> list = pq.asList(FetchOptions.Builder.withDefaults());

		Entity employee = new Entity("HashtagTable", list.size() + 2);
		employee.setProperty("Admin", User.getCurrentActiveUser().getName());

		employee.setProperty("Hashtag", Hashtag);
		employee.setProperty("Post", Post);

		datastore.put(employee);

		return true;

	}

	/**
	 * this function open hashtag count table to use it in  countHashtagPost function 
	 * @return
	 */
	public static boolean savecount() {
		DatastoreService datastore = DatastoreServiceFactory
				.getDatastoreService();

		Query gaeQuery = new Query("HashtagCount");
		PreparedQuery pq = datastore.prepare(gaeQuery);
		List<Entity> list = pq.asList(FetchOptions.Builder.withDefaults());
		Entity employee = new Entity("HashtagCount", list.size() + 2);
		employee.setProperty("Hashtag", 0);
		employee.setProperty("Count", 0);
		datastore.put(employee);
		return true;
	}

	/**
	 * this function count the posts in specific hashtag .
	 * @param Hashtag
	 * @return
	 */
	public static boolean countHashtagPost(String Hashtag) {
		savecount();

		DatastoreService datastore = DatastoreServiceFactory
				.getDatastoreService();

		Query gaeQuery = new Query("HashtagTable");
		PreparedQuery pq = datastore.prepare(gaeQuery);
		DatastoreService datastoree = DatastoreServiceFactory
				.getDatastoreService();
		
		for (Entity entity : pq.asIterable()) {
			if (entity.getProperty("Hashtag").toString().equals(Hashtag)) {

				Query gaeQueryy = new Query("HashtagCount");
				PreparedQuery pq1 = datastoree.prepare(gaeQueryy);

				List<Entity> list = pq1.asList(FetchOptions.Builder
						.withDefaults());
				Entity employee = new Entity("HashtagCount", list.size() + 2);
				employee.setProperty("Hashtag", Hashtag);
				employee.setProperty("Count", Integer.parseInt(employee
						.getProperty("Count").toString()) + 1);

				datastoree.put(employee);
				return true;

			}
		}
		return false;
	}
	
	/**
	    * this function create  timeline post
	    * @param PostType
	    * @param TimelineName
	    * @param Post
	    * @param Feeling
	    * @param Privacy
	    * @return
	    * @throws InstantiationException
	    * @throws IllegalAccessException
	    * @throws ClassNotFoundException
	    */
		public static boolean createTimelinePost(String PostType,
				String TimelineName, String Post, String Feeling, String Privacy)
				throws InstantiationException, IllegalAccessException,
				ClassNotFoundException {
			System.out.println(PostType + " " + TimelineName + " " + Post + " "
					+ Feeling + " " + Privacy);
			Builder b = new Builder();

			// b.setPost(PostType);
			b.setTimelineName(TimelineName);
			b.setPost(Post);
			b.setFeeling(Feeling);
			b.setPrivacy(Privacy);

			b.checkType(PostType);

			return true;
		}

}
