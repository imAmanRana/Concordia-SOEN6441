package models;

import java.util.Date;

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
	 * @param user the user to set
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
	 * @param tweet the tweet to set
	 */
	public void setTweet(String tweet) {
		this.tweet = tweet;
	}
	/**
	 * @return the createdAt
	 */
	public Date getCreatedAt() {
		return createdAt;
	}
	/**
	 * @param createdAt the createdAt to set
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
	 * @param retweetCount the retweetCount to set
	 */
	public void setRetweetCount(long retweetCount) {
		this.retweetCount = retweetCount;
	}
	/**
	 * @return the favoriteCount
	 */
	public long getFavoriteCount() {
		return favoriteCount;
	}
	/**
	 * @param favoriteCount the favoriteCount to set
	 */
	public void setFavoriteCount(long favoriteCount) {
		this.favoriteCount = favoriteCount;
	}

}
