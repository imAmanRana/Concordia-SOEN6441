package controllers;

import play.data.Form;
import play.data.FormFactory;
import play.mvc.*;

import javax.inject.Inject;
import javax.inject.Singleton;

import models.Tweet;


import java.util.ArrayList;
import java.util.List;

import static play.libs.Scala.asScala;

import twitter4j.Query;
import twitter4j.QueryResult;
import twitter4j.Status;
import twitter4j.Twitter;
import twitter4j.TwitterException;
import twitter4j.TwitterFactory;
import twitter4j.conf.ConfigurationBuilder;

@SuppressWarnings("deprecation")
public class TweetController extends Controller {

	private final Form<TweetData> form;
	String keyword;
	List<Tweet> final_result = new ArrayList<>();

	@Inject
	public TweetController(FormFactory formFactory) {
		this.form = formFactory.form(TweetData.class);
	}

	public Result listTweets() {
		return ok(views.html.listTweets.render(keyword, final_result, form));
	}

	public Result createTweets() {

		final Form<TweetData> boundForm = form.bindFromRequest();

		if (boundForm.hasErrors()) {
			play.Logger.ALogger logger = play.Logger.of(getClass());
			logger.error("errors = {}", boundForm.errors());
			return badRequest(views.html.listTweets.render(keyword, final_result, boundForm));
		} else {
			TweetData data = boundForm.get();
			keyword = data.getKeyword();
			ConfigurationBuilder cb = new ConfigurationBuilder();
			cb.setDebugEnabled(true).setOAuthConsumerKey("dmu5QlY7m7ii57UHK7nC8PhEH")
					.setOAuthConsumerSecret("aGbYMWcYino9yCqhq8ZfmdmNHCLqZ6Z6EgHPHRwW0uk31SQrhu")
					.setOAuthAccessToken("972207166961659904-765KE2Wy7Z3BYuRw9VbrffJjJ9po1rV")
					.setOAuthAccessTokenSecret("Utnfkw20VWoSbPs3dxbQXuH7gP3pDkuLzhmXj5UCelIzb");
			TwitterFactory tf = new TwitterFactory(cb.build());
			Twitter twitter = tf.getInstance();

			try {
				Query query = new Query(keyword).count(10);
				QueryResult result;

				result = twitter.search(query);
				List<Status> tweets = result.getTweets();
				for (Status tweet : tweets) {
					final_result.add(new Tweet(tweet.getUser().getScreenName(), tweet.getText()));
					System.out.println("@" + tweet.getUser().getScreenName() + " - " + tweet.getText());
					System.out.println(final_result);
				}
			} catch (TwitterException te) {
				te.printStackTrace();
				System.out.println("Failed to search tweets: " + te.getMessage());
				System.exit(-1);
			}
		}
		return redirect(routes.TweetController.listTweets());
	}
}
