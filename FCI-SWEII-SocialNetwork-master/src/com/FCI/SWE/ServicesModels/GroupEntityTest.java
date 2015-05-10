package com.FCI.SWE.ServicesModels;

import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.google.appengine.tools.development.testing.LocalDatastoreServiceTestConfig;
import com.google.appengine.tools.development.testing.LocalMemcacheServiceTestConfig;
import com.google.appengine.tools.development.testing.LocalServiceTestHelper;
import com.google.appengine.tools.development.testing.LocalTaskQueueTestConfig;

public class GroupEntityTest {

	private final LocalServiceTestHelper helper = new LocalServiceTestHelper(

			new LocalDatastoreServiceTestConfig(),
					new LocalMemcacheServiceTestConfig(),
					new LocalTaskQueueTestConfig());

			@BeforeClass
			public void setUp() {
				helper.setUp();

			}

			@AfterClass
			public void tearDown() {
				helper.tearDown();
			}

  @Test
  public void getDesc() {
    throw new RuntimeException("Test not implemented");
  }

  @Test
  public void getName() {
    throw new RuntimeException("Test not implemented");
  }

  @Test
  public void getOwnerId() {
    throw new RuntimeException("Test not implemented");
  }

  @Test
  public void getPrivacy() {
    throw new RuntimeException("Test not implemented");
  }

  @Test
  public void saveGroup() {
    GroupEntity ge=new GroupEntity();
    Assert.assertEquals(true, ge.saveGroup().booleanValue());
  }

  @Test
  public void setDescription() {
    throw new RuntimeException("Test not implemented");
  }

  @Test
  public void setName() {
    throw new RuntimeException("Test not implemented");
  }

  @Test
  public void setOwnerId() {
    throw new RuntimeException("Test not implemented");
  }

  @Test
  public void setPrivacy() {
    throw new RuntimeException("Test not implemented");
  }
}
