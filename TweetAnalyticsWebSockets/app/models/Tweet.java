package models;

import java.util.Date;

/**
 * Model class for holding twitter data
 * @author Satnam Singh
 */
public class Tweet {
	
	private User user;
	private String tweet;
	private Date createdAt;
	private long retweetCount;
	private long favoriteCount;
	
	/**
	 * @return the user
	 */
	public User getUser() {
		return user;
	}
	/**
	 * @param user  the user (of type class User)
	 */
	public void setUser(User user) {
		this.user = user;
	}
	/**
	 * @return the tweet
	 */
	public String getTweet() {
		return tweet;
	}
	/**
	 * @param tweet   tweet to be set(of type class Tweet)
	 */
	public void setTweet(String tweet) {
		this.tweet = tweet;
	}
	/**
	 * @return date at which tweet was created
	 */
	public Date getCreatedAt() {
		return createdAt;
	}
	/**
	 * @param createdAt  date at which tweet was created
	 */
	public void setCreatedAt(Date createdAt) {
		this.createdAt = createdAt;
	}
	/**
	 * @return the retweetCount
	 */
	public long getRetweetCount() {
		return retweetCount;
	}
	/**
	 * @param retweetCount the count of retweets
	 */
	public void setRetweetCount(long retweetCount) {
		this.retweetCount = retweetCount;
	}
	/**
	 * @return the count favorite tweets
	 */
	public long getFavoriteCount() {
		return favoriteCount;
	}
	/**
	 * @param favoriteCount   the favoriteCount
	 */
	public void setFavoriteCount(long favoriteCount) {
		this.favoriteCount = favoriteCount;
	}
}
