/**
 * 
 */
package service;

import java.util.List;

import javax.inject.Singleton;

import com.typesafe.config.Config;
import com.typesafe.config.ConfigFactory;

import twitter4j.Query;
import twitter4j.QueryResult;
import twitter4j.ResponseList;
import twitter4j.Status;
import twitter4j.Twitter;
import twitter4j.TwitterException;
import twitter4j.TwitterFactory;
import twitter4j.User;
import twitter4j.conf.ConfigurationBuilder;


/**
 * @author Amandeep Singh
 *
 */
@Singleton
public class TwitterService {
	
	private final Config config;
	
	private final Twitter twitter;
	
	private static final TwitterService instance=new TwitterService();
	
	/**
	 * Constructor establishes the connection with twitter and returns the Twitter instance.
	 */
	private TwitterService() {
		config = ConfigFactory.load();

		ConfigurationBuilder cb = new ConfigurationBuilder();
		cb.setDebugEnabled(true).setOAuthConsumerKey(config.getString("CONSUMER_KEY"))
				.setOAuthConsumerSecret(config.getString("CONSUMER_SECRET"))
				.setOAuthAccessToken(config.getString("ACCESS_TOKEN"))
				.setOAuthAccessTokenSecret(config.getString("ACCESS_TOKEN_SECRET"));
		TwitterFactory tf = new TwitterFactory(cb.build());
		twitter = tf.getInstance();
		System.out.println("-->instantiated");
	}
	
	public static TwitterService getInstance() {
		return instance;
	}
	
	/**
	 * This action takes a keyword and search it on twitter and returns the list of
	 * type Status
	 * @param keyword keyword is the string passed to Twitter API on basis of which we retrieve the tweets
	 * @return List<Status>
	 */
	public List<Status> queryApi(String keyword) {
		List<Status> tweets = null;
		Query query = new Query(keyword);
		query.setCount(10);
		QueryResult result;
		try {
			result = twitter.search(query);
			tweets = result.getTweets();
		} catch (TwitterException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return tweets;

				
		
	}
	/**
	 * this method is used to display the information of the user.
	 * @param screenName screenName used to display screenName of the User
	 * @return User Object
	 */
	public User showUser(String screenName) {
		User user = null;
		try {	
			user = twitter.showUser(screenName);
		} catch (TwitterException exp) {
		}
		
		return user;
	}
	
	/**
	 * this action is used to display the timeline of the user.	
	 * @param screenName screenName is string passed to Twitter API on basis of which we retrieve the tweets according to the 
	 * name of the user.
	 * @return ResponseList<Status>
	 */
	public ResponseList<Status> getUserTimeline(String screenName) {
		ResponseList<Status> list = null;
		try {	
			list = twitter.getUserTimeline(screenName);
		} catch (TwitterException exp) {
		}
		
		return list;
	}
	
	
}
