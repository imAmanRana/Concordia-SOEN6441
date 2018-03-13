package controllersTest;

import static org.junit.Assert.assertEquals;
import static play.mvc.Http.Status.OK;
import static play.test.Helpers.GET;
import static play.test.Helpers.route;

import org.junit.Test;

import play.mvc.Http.RequestBuilder;
import play.mvc.Result;
import play.test.Helpers;
import play.test.WithApplication;

/**
 *Tests the methods of controller TweetController {@link controllers.TweetController  TweetController} 
 * @author Kamalpreet Kaur
 *
 */
public class TweetControllerTest extends WithApplication{

	/**Tests the 
	 *{@link controllers.TweetController#fetchTweets()  testFetchTweets}  method of TweetController
	 */
	@Test
	public void testFetchTweets() {
	   
	        RequestBuilder request = Helpers.fakeRequest()
	                .method(GET)
	                .uri("/tweet");

	        Result result = route(app, request);
	        assertEquals(OK, result.status());
	}

}
