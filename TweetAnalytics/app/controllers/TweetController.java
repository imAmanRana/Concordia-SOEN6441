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
 * class for fetching tweets
 * @author raghav
 *
 */
public class TweetController extends Controller {

	private final Form<TweetData> form;
	private final List<Tweet> tweets;
	private final Twitter twitter;
	private final Config config;
	/**
	 * 
	 * @param formFactory sets the formFactory
	 */
	@Inject
	public TweetController(FormFactory formFactory) {
		this.form = formFactory.form(TweetData.class);
		this.tweets = new ArrayList<>();
		config = ConfigFactory.load();
		
		ConfigurationBuilder cb = new ConfigurationBuilder();
		cb.setDebugEnabled(true)
			.setOAuthConsumerKey(config.getString("CONSUMER_KEY"))
			.setOAuthConsumerSecret(config.getString("CONSUMER_SECRET"))
			.setOAuthAccessToken(config.getString("ACCESS_TOKEN"))
			.setOAuthAccessTokenSecret(config.getString("ACCESS_TOKEN_SECRET"));
		TwitterFactory tf = new TwitterFactory(cb.build());
		twitter = tf.getInstance();
	}
	/**
	 * 
	 * @return list of tweets
	 */
	public Result listTweets() {
		return ok(views.html.listTweets.render(tweets, form));
	}
	/**
	 * 
	 * @return tweets
	 */

	public CompletionStage<Result> fetchTweets() {
		
		/*try {
			Files.write(Paths.get("a:/output.txt"), "".getBytes());
		} catch (Exception e) {

		}*/
		CompletableFuture<List<Tweet>> promiseTweet=null;
		final Form<TweetData> boundForm = form.bindFromRequest();

		if (boundForm.hasErrors()) {
			play.Logger.ALogger logger = play.Logger.of(getClass());
			logger.error("errors = {}", boundForm.errors());
			return null;//badRequest(views.html.listTweets.render(tweets, boundForm));
		} else {
			TweetData data = boundForm.get();
			String keyword = data.getKeyword();
			Query query = new Query(keyword);
			query.setCount(10);
			promiseTweet = CompletableFuture.supplyAsync(()->queryApi(query));
				
		}
		return promiseTweet.thenApply(tweets -> {
					this.tweets.addAll(tweets);
					return redirect(routes.TweetController.listTweets());
					});
	}
	/**
	 * 
	 * @param query sets the query
	 * @return resultTweets 
	 */
	private List<Tweet> queryApi(Query query){
		
		List<Tweet> resultTweets=null;
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
				tweet.setRetweetCount(t.getRetweetedStatus()!=null?t.getRetweetedStatus().getRetweetCount():0);
				tweet.setFavoriteCount(t.getRetweetedStatus()!=null?t.getRetweetedStatus().getFavoriteCount():0);
				resultTweets.add(tweet);
			}
			
		}catch(Exception exp) {
			System.out.println("exception");
		}
		return resultTweets;
	}
	/**
	 * 
	 * @param screenName sets the screenName
	 * @return user's profile and recent posts
	 */
	public CompletionStage<Result> getUser(final String screenName) {
		CompletableFuture<twitter4j.User> promiseUser;
		CompletableFuture<ResponseList<Status>> promiseRecentPost;
			
		promiseUser = CompletableFuture.supplyAsync(()->{
									twitter4j.User user = null;
									try {
										user =  twitter.showUser(screenName);
									} catch (TwitterException exp){
										System.out.println("Exceptin while fetching user");
									}
									return user;
								});
		promiseRecentPost =  CompletableFuture.supplyAsync(()->{
									ResponseList<Status> recentPost = null;
									try {
										recentPost =  twitter.getUserTimeline(screenName);
									}  catch (TwitterException exp){
										System.out.println("Exceptin while fetching user");
									}
									return recentPost;
								});
		
		return CompletableFuture.allOf(promiseUser,promiseRecentPost)
							.thenApplyAsync(dummy -> {
								twitter4j.User user = promiseUser.join();
								ResponseList<Status> recentPost = promiseRecentPost.join();
								return ok(views.html.userProfile.render(user,recentPost));
							});
			
			
		
	}
	/**
	 * 
	 * @return asynchronously list of tweets
	 */
	public CompletionStage<Result> clearAll() {
		return CompletableFuture.supplyAsync(()->{
			this.tweets.clear();
			return redirect(routes.TweetController.listTweets());
		});
	}
}
