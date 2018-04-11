/**
 * 
 */
package models;

import java.util.List;

import scala.reflect.internal.Trees.This;

/**
 * @author Amandeep Singh
 *
 */
public class SearchResults {
	private String keyword;
	private List<Tweet> tweet;
	
	public SearchResults(String keyword,List<Tweet> tweet) {
		this.keyword = keyword;
		this.tweet = tweet;
		System.out.println(this.tweet.get(0));
	}
	
	
	/**
	 * @return the keyword
	 */
	public String getKeyword() {
		return keyword;
	}
	/**
	 * @param keyword the keyword to set
	 */
	public void setKeyword(String keyword) {
		this.keyword = keyword;
	}
	/**
	 * @return the tweet
	 */
	public List<Tweet> getTweet() {
		return tweet;
	}
	/**
	 * @param tweet the tweet to set
	 */
	public void setTweet(List<Tweet> tweet) {
		this.tweet = tweet;
	}
}
