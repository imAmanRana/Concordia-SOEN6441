package service;

import java.util.ArrayList;
import java.util.List;

import twitter4j.JSONException;
import twitter4j.JSONObject;
import twitter4j.Query;
import twitter4j.QueryResult;
import twitter4j.ResponseList;
import twitter4j.Status;
import twitter4j.TwitterException;
import twitter4j.TwitterObjectFactory;
import twitter4j.User;


/**
 * Mock implementation of TwitterService
 * @author Manpreet Kaur
 *
 */
public class MockTwitterService implements TwitterService {
	
	@Override
	public List<Status> queryApi(String keyword) {
		List<Status> tweets = new ArrayList<>();
		String jsonString = "{\"aman\":\"Test\"}";
       
        String json; //a valid json string
        Status status = null;
        try {
            status = TwitterObjectFactory.createStatus(jsonString);
        } catch (TwitterException e) {
            e.printStackTrace();
        }
		
        tweets.add(status);
		return tweets;

				
		
	}

	@Override
	public User showUser(String screenName) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ResponseList<Status> getUserTimeline(String screenName) {
		// TODO Auto-generated method stub
		return null;
	}
	


}
