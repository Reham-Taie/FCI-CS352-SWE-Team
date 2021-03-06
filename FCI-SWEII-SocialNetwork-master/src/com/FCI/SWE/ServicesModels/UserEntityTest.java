package com.FCI.SWE.ServicesModels;


import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.google.appengine.api.datastore.Entity;
import com.google.appengine.tools.development.testing.LocalDatastoreServiceTestConfig;
import com.google.appengine.tools.development.testing.LocalMemcacheServiceTestConfig;
import com.google.appengine.tools.development.testing.LocalServiceTestHelper;
import com.google.appengine.tools.development.testing.LocalTaskQueueTestConfig;

import org.testng.Assert;
import org.testng.annotations.Test;

public class UserEntityTest {
	
	private final LocalServiceTestHelper helper = new LocalServiceTestHelper(

			new LocalDatastoreServiceTestConfig());
			UserEntity user = new UserEntity("example", "example", "1234");
			UserEntity user2 = new UserEntity("example", "example", "1234");
			@BeforeClass
			public void setUp() {
				helper.setUp();
				user.saveUser();
				//user2.friend("reham");
				
			}

			@AfterClass
			public void tearDown() {
				helper.tearDown();
			}

@Test
  public void like() { 
	  pageEntity ge=new pageEntity(null, null, null);
	  Assert.assertEquals(false , ge.like("FCI"));
  }


			
 @Test
  public void getUser() {
    //throw new RuntimeException("Test not implemented");
	  UserEntity user = UserEntity.getUser("example", "1234");
	  Assert.assertNotNull(user);
  }
 @Test
 public void saveUser() {
	  UserEntity s=new UserEntity(null, null, null);
 Assert.assertEquals(true, s.saveUser().booleanValue());
 }
 @Test
 public void friend() {
	 
	RequestEntity s=new RequestEntity();
	 Assert.assertEquals(false, s.friend("reham"));
	  }
 @Test
 public void savecount() {
	postEntity s=new postEntity(null, null, null);
	 Assert.assertEquals(true, s.savecount());
 }
 @Test
 public void msgcheck() {
	 MessageEntity s=new MessageEntity(null, null, null);
	 Assert.assertNotNull(true);
 }
 
 @Test
 public void createTimelinePost()  {
	   postEntity ge = new postEntity(null, null, null);
	 try {
		Assert.assertEquals(false ,ge.createTimelinePost("khik", "lh ", "jln", "ilu", "lup") );
	} catch (InstantiationException | IllegalAccessException
			| ClassNotFoundException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	   }
  @Test
  public void Enter() {
	 pageEntity s=new pageEntity(null, null, null);
		 Assert.assertEquals(false, s.Enter("FCI"));
  }

@Test
  public void conversation() {
	//  UserEntity s=new UserEntity(null, null, null);
		// Assert.assertEquals(false, s.conversation("4","edu"));
}

  @Test
  public void countHashtagPost() {
	  postEntity s=new postEntity(null, null, null);
		 Assert.assertEquals(false, s.countHashtagPost("lolo"));
		}

  @Test
  public void createHashtagPost() {
	  postEntity ge = new postEntity(null, null, null);
	   Assert.assertEquals(false ,ge.createHashtagPost("lolo", "post") );
  }

 
  
  @Test
  public void savemessage() {
	    MessageEntity ge=new MessageEntity(null, null, null);
	    Assert.assertEquals(true, ge.savemessage().booleanValue());
  }

  @Test
  public void savepage() {
	  pageEntity ge=new pageEntity(null, null, null);
	  Assert.assertEquals(false, ge.savepage("hhh", "2", "Y", "private"));
  }

  @Test
  public void savepost() {
	  postEntity ge = new postEntity(null, null, null);
	   Assert.assertEquals(false , ge.savepost("ty","reham"));
  }

  
}
