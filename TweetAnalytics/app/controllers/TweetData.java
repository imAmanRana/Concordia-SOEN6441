package controllers;

import play.data.validation.Constraints;
/**
 * Represents the structure of keyword for which tweets are to be searched 
 * @author Raghav
 *
 */
public class TweetData {
	
	@Constraints.Required
	private String keyword;
	
	/**
	 * 
	 * Default Constructor
	 */
	
	public TweetData() {
	}
	/**
	 * 
	 * @return keyword the word of type String to be searched
	 */
	public String getKeyword() {
		return keyword;
	}
	/**
	 * 
	 * @param keyword   keyword to be set
	 */
	public void setKeyword(String keyword) {
		this.keyword = keyword;
	}
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "TweetData [keyword=" + keyword + "]";
	}

}
