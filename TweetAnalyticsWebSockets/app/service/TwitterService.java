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
	
	public User showUser(String screenName) {
		User user = null;
		try {	
			user = twitter.showUser(screenName);
		} catch (TwitterException exp) {
		}
		
		return user;
	}
	
	
	public ResponseList<Status> getUserTimeline(String screenName) {
		ResponseList<Status> list = null;
		try {	
			list = twitter.getUserTimeline(screenName);
		} catch (TwitterException exp) {
		}
		
		return list;
	}
	
	
}
