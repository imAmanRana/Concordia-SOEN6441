/**
 * 
 */
package actors;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

import akka.actor.AbstractActorWithTimers;
import akka.actor.ActorRef;
import akka.actor.Props;
import models.SearchResults;
import models.Tweet;
import scala.concurrent.duration.Duration;
import service.RealTwitterService;
import service.TwitterService;
import twitter4j.Status;

/**
 *	Creates actor system which fetch the tweets from the Twitter API
 *	using twitter4j library. It notifies the clients as well with the 
 *	response. 
 *  @author Amandeep Singh
 */
public class TimeActor extends AbstractActorWithTimers {

	private Map<String,ActorRef> userActors = new LinkedHashMap<>();
	private TwitterService twitterService = RealTwitterService.getInstance();

	/**Action used to receive the Twitter response managed by this
	 * 
	 * @return
	 */
	@Override
	public Receive createReceive() {
		
		return receiveBuilder()
				.match(RegisterMsg.class, register -> userActors.put(register.keyword,sender()))
				.match(Tick.class, msg -> notifyClients())
				.matchEquals("stopActors",s->{System.out.println("got stop sign");})
				.build();
	}
	/**
	 * Notifies each user actor registered with TimeActor class
	 */
	private void notifyClients() {
		userActors.forEach((key,value)-> {	SearchResults s = new SearchResults(key,filterTweets(fetchTweets(key)));
											UserActor.TimeMessage tMsg = new UserActor.TimeMessage(s);
											value.tell(tMsg,self());
										});
	}
	
	/**
	 * this action is used to filter the fetched tweets and collects to an ArrayList 
	 * @param fetchTweets
	 * @return
	 */
	private List<Tweet> filterTweets(List<Status> fetchTweets) {
		List<Tweet> tweets = fetchTweets.stream()
			.map(status -> new Tweet(status.getUser().getScreenName(),status.getText())).collect(Collectors.toList());
		return tweets;
	}
	
	/**
	 * Fetches the tweets matching the keyword
	 * @param keyword
	 * @return
	 */
	private List<Status> fetchTweets(String keyword) {
		if(twitterService==null) {
			return null;
		}else {
			return twitterService.queryApi(keyword);
		}
	}

	private static final class Tick {
	}
	
	/** this overridden method is used to tell after how much duration the actor will be called again.
	 * 
	 */
	@Override
	public void preStart() {
		getTimers().startPeriodicTimer("Timer", new Tick(), Duration.create(7, TimeUnit.SECONDS));
	}
	/**
	 *	this class is used to register actor to TimeActor.
	 * @author raghav
	 *
	 */
	public static class RegisterMsg {
		private final String keyword;
		public RegisterMsg(String keyword) {
			this.keyword = keyword;
		}
	}
	/*
	 * this action is used to create the TimeActor 
	 *
	 */
	/**
	 * this method is used to create the actor named TimeActor 
	 * @return Props
	 *
	 */
	public static Props getProps() {
		return Props.create(TimeActor.class);
	}

}
