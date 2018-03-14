package controllers;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CompletionStage;

import javax.inject.Inject;

import com.typesafe.config.Config;
import com.typesafe.config.ConfigFactory;

import models.Tweet;
import models.User;
import play.Logger.ALogger;
import play.data.Form;
import play.data.FormFactory;
import play.mvc.Controller;
import play.mvc.Result;
import twitter4j.Query;
import twitter4j.QueryResult;
import twitter4j.ResponseList;
import twitter4j.Status;
import twitter4j.Twitter;
import twitter4j.TwitterException;
import twitter4j.TwitterFactory;
import twitter4j.conf.ConfigurationBuilder;

/**
 * This controller contains multiple action methods to handle HTTP requests to
 * the application's Tweet's page.
 * 
 * @author Raghav
 *
 */
public class TweetController extends Controller {

	/** logger **/
	private static ALogger logger = play.Logger.of(TweetController.class);

	private final Form<TweetData> form;

	private final List<Tweet> tweets;

	private final Twitter twitter;

	private final Config config;

	/**
	 * Default Constructor
	 */
	public TweetController() {
		twitter = null;
		config = null;
		tweets = null;
		form = null;
	}

	/**
	 * Constructor intializes the form ,list of tweets , Twitter instance by setting
	 * the configuration for authorization for twitter api.
	 * 
	 * @param formFactory
	 *            creates the form
	 * @author Amandeep Singh
	 */
	@Inject
	public TweetController(FormFactory formFactory) {
		this.form = formFactory.form(TweetData.class);
		this.tweets = new ArrayList<>();
		config = ConfigFactory.load();

		ConfigurationBuilder cb = new ConfigurationBuilder();
		cb.setDebugEnabled(true).setOAuthConsumerKey(config.getString("CONSUMER_KEY"))
				.setOAuthConsumerSecret(config.getString("CONSUMER_SECRET"))
				.setOAuthAccessToken(config.getString("ACCESS_TOKEN"))
				.setOAuthAccessTokenSecret(config.getString("ACCESS_TOKEN_SECRET"));
		TwitterFactory tf = new TwitterFactory(cb.build());
		twitter = tf.getInstance();

	}

	/**
	 * Action method calls the view template listtweets and render the list of
	 * tweets and form to listtweets.
	 * 
	 * @return returns a play.mvc.Result value, representing the HTTP response to
	 *         send to the client
	 * @author Amandeep Singh
	 */
	public CompletionStage<Result> listTweets() {
		return CompletableFuture.completedFuture(ok(views.html.listTweets.render(tweets, form)));
	}

	/**
	 * Fetch the tweets from twitter api asynchronously by calling
	 * {@link #queryApi(Query query) querApi} on a different thread using
	 * <code>CompletableFuture.supplyAsync</code> method and then redirects the
	 * fetched results to template listtweets using
	 * <code>CompletableFuture.thenApply </code>
	 * 
	 * @return CompletableFuture of type Result
	 * @author Kamalpreet Kaur
	 */

	public CompletionStage<Result> fetchTweets() {

		CompletableFuture<List<Tweet>> promiseTweet = null;
		final Form<TweetData> boundForm = form.bindFromRequest();

		if (boundForm.hasErrors()) {
			logger.error("errors = {}", boundForm.allErrors());
			return CompletableFuture.completedFuture(badRequest(views.html.listTweets.render(tweets, boundForm)));
		} else {
			TweetData data = boundForm.get();
			String keyword = data.getKeyword();
			Query query = new Query(keyword);
			query.setCount(10);
			promiseTweet = CompletableFuture.supplyAsync(() -> queryApi(query));

		}
		return promiseTweet.thenApply(tweets -> {
			this.tweets.addAll(tweets);
			return redirect(routes.TweetController.listTweets());
		});
	}

	/**
	 * Takes a query object and search it on twitter and returns the list of type
	 * Tweet by converting it from the list of type Status
	 * 
	 * @param query
	 *            query to be searched
	 * @return list of type Tweet
	 * @author manpreet
	 */
	public List<Tweet> queryApi(Query query) {

		List<Tweet> resultTweets = null;
		try {

			QueryResult result = twitter.search(query);

			List<Status> tweets = result.getTweets();
			User user;
			Tweet tweet;
			resultTweets = new ArrayList<>();
			for (Status t : tweets) {
				user = new User();
				user.setName(t.getUser().getName());
				user.setScreenName(t.getUser().getScreenName());
				user.setProfileImageUrl(t.getUser().getProfileImageURLHttps());
				tweet = new Tweet();
				tweet.setUser(user);
				tweet.setCreatedAt(t.getCreatedAt());
				tweet.setTweet(t.getText());
				tweet.setRetweetCount(t.getRetweetedStatus() != null ? t.getRetweetedStatus().getRetweetCount() : 0);
				tweet.setFavoriteCount(t.getRetweetedStatus() != null ? t.getRetweetedStatus().getFavoriteCount() : 0);
				resultTweets.add(tweet);
			}

		} catch (TwitterException exp) {
			logger.error("Exception in queryApi method", exp);
		}
		return resultTweets;
	}

	/**
	 * Takes the user screenname and fetch the user object and list of tweets for
	 * that user asynchronously
	 * 
	 * @param screenName
	 *            the users screenname
	 * @return user's profile and its recent posts as a completable future of type
	 *         <code>Result</code>
	 * @author Amandeep Singh
	 */
	public CompletionStage<Result> getUser(final String screenName) {
		CompletableFuture<twitter4j.User> promiseUser;
		CompletableFuture<ResponseList<Status>> promiseRecentPost;

		promiseUser = CompletableFuture.supplyAsync(() -> {
			twitter4j.User user = null;
			try {
				user = twitter.showUser(screenName);
			} catch (TwitterException exp) {
				logger.error("Exceptin while fetching user");
			}
			return user;
		});
		promiseRecentPost = CompletableFuture.supplyAsync(() -> {
			ResponseList<Status> recentPost = null;
			try {
				recentPost = twitter.getUserTimeline(screenName);
			} catch (TwitterException exp) {
				logger.error("Exceptin while fetching user");
			}
			return recentPost;
		});

		return CompletableFuture.allOf(promiseUser, promiseRecentPost).thenApplyAsync(dummy -> {
			twitter4j.User user = promiseUser.join();
			ResponseList<Status> recentPost = promiseRecentPost.join();
			return ok(views.html.userProfile.render(user, recentPost));
		});

	}

	/**
	 * Clears all the tweets from the list asynchronously using
	 * <code>CompletableFuture.supplyAsync</code> method
	 * 
	 * @return returns a <code>CompletionStage Result</code> value, representing the
	 *         HTTP response to send to the client
	 * @author Raghav
	 */
	public CompletionStage<Result> clearAll() {
		return CompletableFuture.supplyAsync(() -> {
			this.tweets.clear();
			return redirect(routes.TweetController.listTweets());
		});
	}
}
