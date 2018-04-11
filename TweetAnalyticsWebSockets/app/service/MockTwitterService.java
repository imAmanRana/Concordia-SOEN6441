package service;

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

public class MockTwitterService implements TwitterService {
	
	@Override
	public List<Status> queryApi(String keyword) {
		List<Status> tweets = null;
		String jsonString = "{\r\n" + 
				" \"created_at\":\"Thu Apr 06 15:24:15 +0000 2017\",\r\n" + 
				" \"id\": 850006245121695744,\r\n" + 
				" \"id_str\": \"850006245121695744\",\r\n" + 
				" \"text\": \"1/ Today wre sharing our vision for the future of the Twitter API platform!nhttps://t.co/XweGngmxlP\",\r\n" + 
				" \"user\": {},  \r\n" + 
				" \"entities\": {}\r\n" + 
				"}}";
       
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
