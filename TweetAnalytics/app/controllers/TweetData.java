package controllers;

import play.data.validation.Constraints;

public class TweetData {
	@Constraints.Required
	private String keyword;

	public TweetData() {
	}

	public String getKeyword() {
		return keyword;
	}

	public void setKeyword(String keyword) {
		this.keyword = keyword;
	}

}
