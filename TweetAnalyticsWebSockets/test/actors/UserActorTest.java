package actors;

import static org.junit.Assert.assertEquals;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import com.fasterxml.jackson.databind.node.JsonNodeFactory;
import com.fasterxml.jackson.databind.node.ObjectNode;

import akka.actor.ActorRef;
import akka.actor.ActorSystem;
import akka.testkit.javadsl.TestKit;

public class UserActorTest {

	static ActorSystem system;
	ActorRef ref;

	@BeforeClass
	public static void setup() {
		system = ActorSystem.create();
	}

	@AfterClass
	public static void teardown() {
		TestKit.shutdownActorSystem(system);
		system = null;
	}

	@Test
	public void test() {
		System.out.println("inside test");
		final TestKit testProbe = new TestKit(system);
		ActorRef victim = null;
		ActorRef userActor = system.actorOf(UserActor.getProps(victim), "User-actor");
		ActorRef timerActor = system.actorOf(TimeActor.getProps(), "timeActor");
		// final ObjectNode root = mapper.createObjectNode();

		JsonNodeFactory factory = JsonNodeFactory.instance;
		ObjectNode root = factory.objectNode();
		root.put("keyword", "canada");
		userActor.tell(root, ActorRef.noSender());

		// RegisterMsg greeting = testProbe.expectMsgClass(RegisterMsg.class);
		assertEquals("\"canada\"", root.get("keyword").toString());
		// System.out.println(greeting.keyword);

	}

}
