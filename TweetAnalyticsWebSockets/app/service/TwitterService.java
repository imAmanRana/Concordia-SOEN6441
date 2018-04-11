package service;

import java.util.List;

import twitter4j.ResponseList;
import twitter4j.Status;
import twitter4j.User;

//@ImplementedBy(RealGreetingService.class)
public interface TwitterService {

	public List<Status> queryApi(String keyword);
	public User showUser(String screenName);
	public ResponseList<Status> getUserTimeline(String screenName);
	}


