package controllers;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

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

public class TweetController extends Controller {

	private final Form<TweetData> form;
	private final List<Tweet> tweets;
	private final Twitter twitter;

	@Inject
	public TweetController(FormFactory formFactory) {
		this.form = formFactory.form(TweetData.class);
		this.tweets = new ArrayList<>();
		ConfigurationBuilder cb = new ConfigurationBuilder();
		cb.setDebugEnabled(true)
			.setOAuthConsumerKey("zHuFIr0kfEwLJJcGHpKv41ohq")
			.setOAuthConsumerSecret("dacuhmxqdJU4v77a10cb74IYs9N75Jl77S0te5ykAd1HsAib2X")
			.setOAuthAccessToken("228998825-IjjOx9ZAndDo9dEs3EpY5fSJr6s3h6wqp7UiOIU4")
			.setOAuthAccessTokenSecret("MAaHLWxzfrxir6rALkSEewFWwdMhl65xbAvGQaq6uZ5fd");
		TwitterFactory tf = new TwitterFactory(cb.build());
		twitter = tf.getInstance();
	}

	public Result listTweets() {
		return ok(views.html.listTweets.render(tweets, form));
	}

	public Result fetchTweets() {
		/*try {
		Files.write(Paths.get("a:/output.txt"), "".getBytes());
		}catch(Exception e) {}
		*/
		final Form<TweetData> boundForm = form.bindFromRequest();

		if (boundForm.hasErrors()) {
			play.Logger.ALogger logger = play.Logger.of(getClass());
			logger.error("errors = {}", boundForm.errors());
			return badRequest(views.html.listTweets.render(tweets, boundForm));
		} else {
			TweetData data = boundForm.get();
			String keyword = data.getKeyword();

			try {
				Query query = new Query(keyword);
				query.setCount(10);
				QueryResult result;
				result = twitter.search(query);
				List<Status> tweets = result.getTweets();
				User user;
				Tweet tweet;
				for (Status t : tweets) {
					user = new User();
					user.setName(t.getUser().getName());
					user.setScreenName(t.getUser().getScreenName());
					user.setProfileImageUrl(user.getScreenName());
					tweet = new Tweet();
					tweet.setUser(user);
					tweet.setCreatedAt(t.getCreatedAt());
					tweet.setTweet(t.getText());
					tweet.setRetweetCount(t.getRetweetCount());
					tweet.setFavoriteCount(t.getFavoriteCount());
					/*try {
						Files.write(Paths.get("a:/output.txt"), t.toString().getBytes(),StandardOpenOption.APPEND);
					} catch (Exception e) {

					}*/
					this.tweets.add(tweet);
				}
			} catch (TwitterException te) {
				te.printStackTrace();
				System.out.println("Failed to search tweets: " + te.getMessage());
				System.exit(-1);
			}
		}
		return redirect(routes.TweetController.listTweets());
	}
	
	public Result getUser(final String screenName) {
		twitter4j.User user=null;
		ResponseList<Status> recentPost=null;
		try {
			user = twitter.showUser(screenName);
			recentPost =  twitter.getUserTimeline(screenName);
		}catch (TwitterException te) {
			te.printStackTrace();
			System.out.println("Failed to fetch user profile: " + te.getMessage());
			System.exit(-1);
		}
		return ok(views.html.userProfile.render(user,recentPost));
	}
}
