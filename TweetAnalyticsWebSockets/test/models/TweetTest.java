/**
 * 
 */
package models;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

import java.text.SimpleDateFormat;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

/**
 * Tests the methods of model class Tweet
 * 
 * @author manpreet
 *
 */
public class TweetTest {

	private static final String DATE = "02-04-2013 11:35:42";
	private static final String USER_NAME = "Aman";
	private static final String PROFILE_IMAGE_URL = "https://pbs.twimg.com/profile_images/910573838299213825/Wk2V3ukk_bigger.jpg";
	private static final String SCREEN_NAME = "imAmanRana";
	private static final long ONE = 1;
	private static final String TWEET = "unit testing tweet";

	private Tweet tweet;
	private User user;
	private static SimpleDateFormat dateFormat;

	/**
	 * @throws java.lang.Exception
	 */
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		dateFormat = new SimpleDateFormat("dd-MM-yyyy hh:mm:ss");
		;
	}

	/**
	 * @throws java.lang.Exception
	 */
	@AfterClass
	public static void tearDownAfterClass() throws Exception {
		dateFormat = null;
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

		tweet = new Tweet();
		tweet.setUser(user);
		tweet.setFavoriteCount(1);
		tweet.setRetweetCount(1);
		tweet.setCreatedAt(dateFormat.parse("02-04-2013 11:35:42"));
		tweet.setTweet("unit testing tweet");
	}

	/**
	 * @throws java.lang.Exception
	 */
	@After
	public void tearDown() throws Exception {
		tweet = null;
	}

	/**
	 * Test method for {@link models.Tweet#getUser()}.
	 */
	@Test
	public final void testGetUser() {

		User usr = new User();
		usr.setName(USER_NAME);
		usr.setScreenName(SCREEN_NAME);
		usr.setProfileImageUrl(PROFILE_IMAGE_URL);

		assertThat(usr.toString(), is(equalTo(tweet.getUser().toString())));

	}

	/**
	 * Test method for {@link models.Tweet#setUser(models.User)}.
	 */
	@Test
	public final void testSetUser() {
		// NOTHING TO DO
	}

	/**
	 * Test method for {@link models.Tweet#getTweet()}.
	 */
	@Test
	public final void testGetTweet() {
		assertThat(TWEET, is(equalTo(tweet.getTweet())));
	}

	/**
	 * Test method for {@link models.Tweet#setTweet(java.lang.String)}.
	 */
	@Test
	public final void testSetTweet() {
		// nothing to test
	}

	/**
	 * Test method for {@link models.Tweet#getCreatedAt()}.
	 */
	@Test
	public final void testGetCreatedAt() {
		assertThat(DATE, is(equalTo(dateFormat.format(tweet.getCreatedAt()))));
	}

	/**
	 * Test method for {@link models.Tweet#setCreatedAt(java.util.Date)}.
	 */
	@Test
	public final void testSetCreatedAt() {
		// nothing to do
	}

	/**
	 * Test method for {@link models.Tweet#getRetweetCount()}.
	 */
	@Test
	public final void testGetRetweetCount() {
		assertThat(ONE, is(equalTo(tweet.getRetweetCount())));
	}

	/**
	 * Test method for {@link models.Tweet#setRetweetCount(long)}.
	 */
	@Test
	public final void testSetRetweetCount() {
		// NOTHING TO DO
	}

	/**
	 * Test method for {@link models.Tweet#getFavoriteCount()}.
	 */
	@Test
	public final void testGetFavoriteCount() {
		assertThat(ONE, is(equalTo(tweet.getFavoriteCount())));
	}

	/**
	 * Test method for {@link models.Tweet#setFavoriteCount(long)}.
	 */
	@Test
	public final void testSetFavoriteCount() {
		// NOTHING TO DO
	}

}
