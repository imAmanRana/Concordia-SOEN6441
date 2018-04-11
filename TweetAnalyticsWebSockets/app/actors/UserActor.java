/**
 * 
 */
package actors;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;

import akka.actor.AbstractActor;
import akka.actor.ActorRef;
import akka.actor.Props;
import models.SearchResults;
import play.libs.Json;

/**
 * @author AmanRana
 *
 */
public class UserActor extends AbstractActor {

	private final ActorRef ws;
	/**
	 * Constructor of the given Class
	 * @param wsOut wsOut is used to pass the ActorRef
	 */
	public UserActor(final ActorRef wsOut) {
		ws = wsOut;
	}
	/**
	 * this action is used to create the UserActor for handling the Tweets of the User
	 * @param wsout wsout is used to pass the ActorRef
	 * @return Props
	 */
	public static Props getProps(final ActorRef wsout) {
		return Props.create(UserActor.class, wsout);
	}
	/**
	 * this class handles the tweets which are returned from the UserActor
	 * @author raghav
	 *
	 */
	public static class TimeMessage {
        public final SearchResults result;
        public TimeMessage(SearchResults result) {
            this.result = result;
        }
	}

	/**
	 * this action is used to send the time of the message.
	 * @param msg 
	 * @throws JsonProcessingException
	 */
	 private void sendTime(TimeMessage msg) throws JsonProcessingException {
	        final ObjectNode response = Json.newObject();
	        ObjectMapper objectMapper = new ObjectMapper();	
	        response.put("data",objectMapper.writeValueAsString(msg.result));
	        ws.tell(response, self());
	    }
	/**
	 * this is used to handle the Tweets that are sent by the TimeActor
	 * @return Receive 
	 */
	@Override
	public Receive createReceive() {
		return receiveBuilder()
				.match(TimeMessage.class, msg -> sendTime(msg))
				.match(ObjectNode.class, json -> registerNewActor(json))
				.build();
				
	}
	/**
	 * this action is used to register the UserActor with TimeActor
	 * @param json json is used to fetch the value of the "keyword" field and then registering to the TimeActor
	 */
	private void registerNewActor(ObjectNode json) {
		context().actorSelection("/user/timeActor/")
        	.tell(new TimeActor.RegisterMsg(json.get("keyword").asText()), self());
	}
}
