package controllers;

import play.data.validation.Constraints;
/**
 * class used for storing tweeter form data
 * @author raghav
 *
 */
public class TweetData {
	@Constraints.Required
	private String keyword;
	/**
	 * constructor for tweet data
	 */
	public TweetData() {
	}
	/**
	 * 
	 * @return keyword
	 */
	public String getKeyword() {
		return keyword;
	}
	/**
	 * 
	 * @param keyword sets the keyword
	 */
	public void setKeyword(String keyword) {
		this.keyword = keyword;
	}

}
