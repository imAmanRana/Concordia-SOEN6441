/**
 * 
 */
package models;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

/**
 * @author manpreet
 *
 */
public class UserTest {

	private static final String USER_NAME = "Aman";
	private static final String PROFILE_IMAGE_URL="https://pbs.twimg.com/profile_images/910573838299213825/Wk2V3ukk_bigger.jpg";
	private static final String SCREEN_NAME="imAmanRana";
	
	private User user;
	
	/**
	 * @throws java.lang.Exception
	 */
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}

	/**
	 * @throws java.lang.Exception
	 */
	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
		user = new User();
		user.setName("Aman");
		user.setProfileImageUrl("https://pbs.twimg.com/profile_images/910573838299213825/Wk2V3ukk_bigger.jpg");
		user.setScreenName("imAmanRana");
	}

	/**
	 * @throws java.lang.Exception
	 */
	@After
	public void tearDown() throws Exception {
		user = null;
	}

	/**
	 * Test method for {@link models.User#getName()}.
	 */
	@Test
	public final void testGetName() {
		assertThat(USER_NAME, is(equalTo(user.getName())));
	}

	/**
	 * Test method for {@link models.User#getScreenName()}.
	 */
	@Test
	public final void testGetScreenName() {
		assertThat(SCREEN_NAME, is(equalTo(user.getScreenName())));
	}

	/**
	 * Test method for {@link models.User#getProfileImageUrl()}.
	 */
	@Test
	public final void testGetProfileImageUrl() {
		assertThat(PROFILE_IMAGE_URL, is(equalTo(user.getProfileImageUrl())));
	}

	/**
	 * Test method for {@link models.User#toString()}.
	 */
	@Test
	public final void testToString() {
		User usr = new User();
		usr.setName(USER_NAME);
		usr.setScreenName(SCREEN_NAME);
		usr.setProfileImageUrl(PROFILE_IMAGE_URL);
		assertThat(usr.toString(), is(equalTo(user.toString())));
	}

}
