package controllers;

import static org.junit.Assert.*;

import org.junit.Test;

import models.Tweet;
import models.User;

public class TweetTest {

	@Test
	public void test() {
		Tweet t=new Tweet();
		User user1=new User();
		user1.setName("raghav");
		user1.setScreenName("Dutta");
		user1.setProfileImageUrl("123");
		t.setUser(user1);
		t.setTweet("Love");
		//t.setCreatedAt(12/12-2018);
		t.setRetweetCount(12333456);
		t.setFavoriteCount(12388889);
		assertEquals(user1,t.getUser());
		assertEquals("Love",t.getTweet());
		assertEquals(12333456,t.getRetweetCount());
		assertEquals(12388889,t.getFavoriteCount());
		assertEquals(user1,t.getUser());assertEquals(user1,t.getUser());
		
	}

}
