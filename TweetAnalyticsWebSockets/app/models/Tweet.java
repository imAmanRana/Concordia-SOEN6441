package models;

/**
 * Model class for holding twitter data
 * @author Satnam Singh
 */
public class Tweet {
	
	private String screenName;
	private String tweet;
	
	public Tweet(String screenName,String tweet) {
		this.screenName = screenName;
		this.tweet = tweet;
	}
	
	/**
	 * @return the screenName
	 */
	public String getScreenName() {
		return screenName;
	}
	/**
	 * @param screenName the screenName to set
	 */
	public void setScreenName(String screenName) {
		this.screenName = screenName;
	}
	/**
	 * @return the tweet
	 */
	public String getTweet() {
		return tweet;
	}
	/**
	 * @param tweet the tweet to set
	 */
	public void setTweet(String tweet) {
		this.tweet = tweet;
	}
	
	
}
