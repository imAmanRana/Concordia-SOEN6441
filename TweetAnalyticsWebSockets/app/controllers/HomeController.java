package controllers;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CompletionStage;

import javax.inject.Inject;
import static akka.pattern.PatternsCS.ask;
import actors.TimeActor;
import actors.UserActor;
import akka.actor.ActorRef;
import akka.actor.ActorSystem;
import akka.stream.Materializer;
import play.libs.streams.ActorFlow;
import play.mvc.Controller;
import play.mvc.Result;
import play.mvc.WebSocket;
import service.RealTwitterService;
import service.TwitterService;
import twitter4j.ResponseList;
import twitter4j.Status;

/**
 * This controller contains an action to handle HTTP requests
 * to the application's home page.
 */
public class HomeController extends Controller {

	private ActorSystem actorSystem;
	private Materializer materializer;
	private TwitterService twitterService = RealTwitterService.getInstance();
	private ActorRef timeActor;
	
	/**
	 * this action is used to inject the TimeActor in the HomeController
	 * @param system system wraps the Actors 
	 * @param materializer materializer is a factory for stream execution engines, it is the thing that makes streams run
	 */
	@Inject
	public HomeController(ActorSystem system,Materializer materializer) {
		this.actorSystem = system;
		this.materializer = materializer;
		timeActor = actorSystem.actorOf(TimeActor.getProps(),"timeActor");
	}
	
	
    /**
     * An action that renders an HTML page with a welcome message.
     * The configuration in the <code>routes</code> file means that
     * this method will be called when the application receives a
     * <code>GET</code> request with a path of <code>/</code>.
     */
    public Result index() {
        return ok(views.html.index.render(request()));
    }
    /**
     * 
     * @return Websocket
     */
    public WebSocket ws() {
    	return WebSocket.Json.accept(request -> ActorFlow.actorRef(UserActor::getProps,actorSystem,materializer));
    }
    
    /**
	 * Takes the user screenname and fetch the user object and list of tweets for
	 * that user asynchronously
	 * 
	 * @param screenName
	 *            the users screenname
	 * @return user's profile and its recent posts as a completable future of type
	 *         <code>Result</code>
	 * @author Amandeep Singh
	 */
	public CompletionStage<Result> getUser(final String screenName) {
		CompletableFuture<twitter4j.User> promiseUser;
		CompletableFuture<ResponseList<Status>> promiseRecentPost;

		promiseUser = CompletableFuture.supplyAsync(() -> {
			twitter4j.User user = null;
				user = twitterService.showUser(screenName);
			
			return user;
		});
		promiseRecentPost = CompletableFuture.supplyAsync(() -> {
			ResponseList<Status> recentPost = null;
			
				recentPost = twitterService.getUserTimeline(screenName);
			 
			return recentPost;
		});

		return CompletableFuture.allOf(promiseUser, promiseRecentPost).thenApplyAsync(dummy -> {
			twitter4j.User user = promiseUser.join();
			ResponseList<Status> recentPost = promiseRecentPost.join();
			return ok(views.html.userProfile.render(user, recentPost));
		});

	}
	
	
	/**
	 * Clears all the tweets from the list asynchronously using
	 * <code>CompletableFuture.supplyAsync</code> method
	 * 
	 * @return returns a <code>CompletionStage Result</code> value, representing the
	 *         HTTP response to send to the client
	 * @author Raghav
	 */
//	public CompletionStage<Result> clearAll() {
//		return ask(this.timeActor,"stopActors",1).thenApply(s->{
//			return CompletableFuture.supplyAsync(() -> {
//				return redirect(routes.HomeController.index());
//			});
//		});
//	}

}
