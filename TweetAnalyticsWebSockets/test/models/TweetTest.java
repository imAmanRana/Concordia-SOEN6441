package models;

import static org.junit.Assert.assertEquals;

import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

/**
 * Test the model class - Tweet.
 */

public class TweetTest {

	public static final String screenname = "John Morgan";
	public static final String tweet = "Necessity is the mother of thr invention";

	static Tweet object;

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		object = new Tweet(screenname, tweet);
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
		object = null;
	}

	@Before
	public void setUp() {
		object.setTweet(tweet);
		object.setScreenName(screenname);
	}

	/**
	 * Test method for {@link models.Tweet#getScreenName()}.
	 */

	@Test
	public void testscreenname() {
		assertEquals(screenname, (object.getScreenName()));
	}

	/**
	 * Test method for {@link models.Tweet#getTweet()}.
	 */
	@Test
	public void testtweet() {
		assertEquals(tweet, (object.getTweet()));
	}
}
