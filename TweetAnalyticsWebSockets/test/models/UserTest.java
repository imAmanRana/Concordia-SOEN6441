package models;

/**
 * Test the model class - User.
 */

import static org.junit.Assert.assertEquals;

import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

public class UserTest {

	private static final String name = "John Morgan";
	private static final String screenName = "John";
	private static final String profileImageUrl = "https://pbs.twimg.com/profile_images/910573838299213825/Wk2V3ukk_bigger.jpg";

	static User user;

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		user = new User();
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
		user = null;
	}

	@Before
	public void setUp() {
		user.setName(name);
		user.setProfileImageUrl(profileImageUrl);
		user.setScreenName(screenName);
	}

	/**
	 * Test method for {@link models.User#getName()}.
	 */
	@Test
	public void testName() {
		assertEquals(name, user.getName());
	}

	/**
	 * Test method for {@link models.User#getProfileImageUrl()}.
	 */
	@Test
	public void testProfileImageUrl() {
		assertEquals(profileImageUrl, user.getProfileImageUrl());
	}

	/**
	 * Test method for {@link models.User#getScreenName()}.
	 */
	@Test
	public void testScreenName() {
		assertEquals(screenName, user.getScreenName());
	}
	/**
	 * Test method for {@link models.User#toString()}.
	 */
	@Test
	public void testtoString() 
	{
		assertEquals(user.toString(),
				"User [name=John Morgan, screenName=John, profileImageUrl=https://pbs.twimg.com/profile_images/910573838299213825/Wk2V3ukk_bigger.jpg]");
	}
	
	
}
