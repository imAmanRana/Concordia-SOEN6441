/**
 * 
 */
package actors;

import java.util.List;
import java.util.Map;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;

import akka.actor.AbstractActor;
import akka.actor.ActorRef;
import akka.actor.Props;
import models.SearchResults;
import models.Tweet;
import play.libs.Json;

/**
 * @author AmanRana
 *
 */
public class UserActor extends AbstractActor {

	private final ActorRef ws;

	public UserActor(final ActorRef wsOut) {
		ws = wsOut;
	}

	public static Props getProps(final ActorRef wsout) {
		return Props.create(UserActor.class, wsout);
	}

	public static class TimeMessage {
        public final SearchResults result;
        public TimeMessage(SearchResults result) {
            this.result = result;
        }
	}
	
	@Override
    public void preStart() {
       
    }
	
	 private void sendTime(TimeMessage msg) throws JsonProcessingException {
	        final ObjectNode response = Json.newObject();
	        ObjectMapper objectMapper = new ObjectMapper();	
	        response.put("data",objectMapper.writeValueAsString(msg.result));
	        ws.tell(response, self());
	    }

	@Override
	public Receive createReceive() {
		return receiveBuilder()
				.match(TimeMessage.class, msg -> sendTime(msg))
				.match(ObjectNode.class, json -> registerNewActor(json))
				.build();
				
	}
	
	private void registerNewActor(ObjectNode json) {
		context().actorSelection("/user/timeActor/")
        	.tell(new TimeActor.RegisterMsg(json.get("keyword").asText()), self());
	}
}
