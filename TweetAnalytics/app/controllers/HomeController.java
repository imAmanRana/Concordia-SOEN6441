package controllers;

import play.mvc.*;

/**
 * This controller contains an action to handle HTTP requests
 * to the application's home page.
 * @author manpreet
 */
public class HomeController extends Controller {

    /**
     * An action that calls <code>TweetController.listTweets()</code> 
     * The configuration in the <code>routes</code> file means that
     * this method will be called when the application receives a
     * <code>GET</code> request with a path of <code>/</code>.
     * @return returns a play.mvc.Result value, representing the HTTP response to send to the client
     */
    public Result index() {
    	
        return redirect(routes.TweetController.listTweets());
    }
}
