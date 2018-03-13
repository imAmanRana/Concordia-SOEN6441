package controllersTest;

import org.junit.Test;


import play.mvc.Http.RequestBuilder;
import play.mvc.Result;
import play.test.Helpers;
import play.test.WithApplication;

import static org.junit.Assert.assertEquals;
import static play.test.Helpers.*;
import static play.mvc.Result.*;

/**
 *Tests the controller HomeController 
 * @author Kamalpreet Kaur
 *
 */
public class HomeControllerTest extends WithApplication {
	/**
	 * Tests whether index action method redirects correctly.
	 */
@Test
    public void testIndex() {
        RequestBuilder request = Helpers.fakeRequest()
                .method(GET)
                .uri("/");

        Result result = route(app, request);
        assertEquals(SEE_OTHER, result.status());
        
        
        
        
}


}

