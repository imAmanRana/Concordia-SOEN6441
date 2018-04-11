package service;

import static org.junit.Assert.assertNotEquals;
import static play.inject.Bindings.bind;

import java.util.List;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import play.Application;
import play.inject.guice.GuiceApplicationBuilder;
import play.test.Helpers;
import twitter4j.Status;

public class TwitterServiceTest {

	private static Application testApp;
	TwitterService twitterservice = testApp.injector().instanceOf(TwitterService.class);

	@BeforeClass
	public static void initTestApp() {
		testApp = new GuiceApplicationBuilder()
				.overrides(bind(TwitterService.class).to(MockTwitterService.class)).build();

	}

	@Test
	public void testqueryApi() {
		List<Status> tweets = twitterservice.queryApi("messi");
		Status jsonObject = tweets.get(0);

		String res = jsonObject.toString();
		assertNotEquals("{\r\n" + " \"created_at\":\"Thu Apr 06 15:24:15 +0000 2017\",\r\n"
				+ " \"id\": 850006245121695744,\r\n" + " \"id_str\": \"850006245121695744\",\r\n"
				+ " \"text\": \"1/ Today we’re sharing our vision for the future of the Twitter API platform!nhttps://t.co/XweGngmxlP\",\r\n"
				+ " \"user\": {},  \r\n" + " \"entities\": {}\r\n" + "}}", res);
	}

	@AfterClass
	public static void stopTestApp() {
		Helpers.stop(testApp);
	}

}