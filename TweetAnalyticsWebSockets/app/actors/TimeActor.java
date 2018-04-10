/**
 * 
 */
package actors;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

import akka.actor.AbstractActor.Receive;
import akka.actor.AbstractActorWithTimers;
import akka.actor.ActorRef;
import akka.actor.Props;
import models.SearchResults;
import models.Tweet;
import scala.concurrent.duration.Duration;
import service.TwitterService;
import twitter4j.Status;

/**
 * @author AmanRana
 *
 */
public class TimeActor extends AbstractActorWithTimers {

	private Map<String,ActorRef> userActors = new LinkedHashMap<>();
	private TwitterService twitterService = TwitterService.getInstance();
	
	@Override
	public Receive createReceive() {
		
		return receiveBuilder()
				.match(RegisterMsg.class, register -> userActors.put(register.keyword,sender()))
				.match(Tick.class, msg -> notifyClients())
				.matchEquals("stopActors",s->{System.out.println("got stop sign");})
				.build();
	}

	private void notifyClients() {
		userActors.forEach((key,value)-> {	SearchResults s = new SearchResults(key,filterTweets(fetchTweets(key)));
											UserActor.TimeMessage tMsg = new UserActor.TimeMessage(s);
											value.tell(tMsg,self());
										});
	}
	private List<Tweet> filterTweets(List<Status> fetchTweets) {
		System.out.println("filtering");
		List<Tweet> tweets = fetchTweets.stream()
			.map(status -> new Tweet(status.getUser().getScreenName(),status.getText())).collect(Collectors.toList());
		return tweets;
	}

	private List<Status> fetchTweets(String keyword) {
		System.out.println("fetching tweets");
		if(twitterService==null) {
			return null;
		}else {
			return twitterService.queryApi(keyword);
		}
	}
	private static final class Tick {
	}

	@Override
	public void preStart() {
		getTimers().startPeriodicTimer("Timer", new Tick(), Duration.create(15, TimeUnit.SECONDS));
	}

	public static class RegisterMsg {
		private final String keyword;
		public RegisterMsg(String keyword) {
			this.keyword = keyword;
		}
	}
	
	public static Props getProps() {
		return Props.create(TimeActor.class);
	}

}
