package controllersTest;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;
import static play.mvc.Http.Status.BAD_REQUEST;
import static play.mvc.Http.Status.OK;
import static play.mvc.Http.Status.SEE_OTHER;
import static play.test.Helpers.GET;
import static play.test.Helpers.POST;
import static play.test.Helpers.route;

import java.util.HashMap;
import java.util.Map;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import controllers.TweetController;
import play.mvc.Http.RequestBuilder;
import play.mvc.Result;
import play.test.Helpers;
import play.test.WithApplication;
import twitter4j.Query;

/**
 * Tests the methods of controller TweetController
 * {@link controllers.TweetController TweetController}
 * 
 * @author Amandeep Singh
 *
 */
public class TweetControllerTest extends WithApplication {

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
	}

	/**
	 * @throws java.lang.Exception
	 */
	@After
	public void tearDown() throws Exception {
	}

	/**
	 * Test method for
	 * {@link controllers.TweetController#TweetController(play.data.FormFactory)}.
	 */
	@Test
	public void testTweetController() {
		// NOTHING TO DO
	}

	/**
	 * Test method for {@link controllers.TweetController#listTweets()}.
	 */
	@Test
	public void testListTweets() {
		RequestBuilder request = Helpers.fakeRequest().method(GET).uri("/tweet");
		Result result = route(app, request);
		assertThat(OK, is(equalTo(result.status())));
	}

	/**
	 * Test method for {@link controllers.TweetController#fetchTweets()}.
	 */
	@Test
	public void testFetchTweets() {
		Map<String, String> data = new HashMap<>();
		data.put("keyword", "aman");

		RequestBuilder request = Helpers.fakeRequest().method(POST).bodyForm(data).uri("/tweet");
		Result result = route(app, request);
		assertThat(SEE_OTHER, is(equalTo(result.status())));
	}

	@Test(expected = Exception.class)
	public void testFetchTweetsForBadRequest() {
		Map<String, String> data = new HashMap<>();
		data.put("csrfToken", "465464862313dvsfdsfds");

		RequestBuilder request = Helpers.fakeRequest().method(POST).bodyForm(data).uri("/tweet");
		Result result = route(app, request);
		assertThat(BAD_REQUEST, is(equalTo(result.status())));
	}

	/**
	 * Test method for
	 * {@link controllers.TweetController#getUser(java.lang.String)}.
	 */
	@Test
	public void testGetUser() {
		RequestBuilder request = Helpers.fakeRequest().method(GET).uri("/user?screenName=imAmanRana");
		Result result = route(app, request);
		assertThat(OK, is(equalTo(result.status())));
	}

	/**
	 * Test method for {@link controllers.TweetController#clearAll()}.
	 */
	@Test
	public void testClearAll() {
		RequestBuilder request = Helpers.fakeRequest().method(GET).uri("/clearAll");
		Result result = route(app, request);
		assertThat(SEE_OTHER, is(equalTo(result.status())));
	}

	/**
	 * Test method for {@link controllers.TweetController#queryApi()}.
	 */
	@Test(expected = Exception.class)
	public void testQueryApi() {
		Query query = null;
		assertThat(null, is(equalTo(new TweetController().queryApi(query))));
	}
}
