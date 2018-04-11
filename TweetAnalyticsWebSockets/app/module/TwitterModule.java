package module;

import com.google.inject.AbstractModule;

import service.RealTwitterService;
import service.TwitterService;



public class TwitterModule extends AbstractModule{

	@Override
	protected void configure() {
		 bind(TwitterService.class)
         .to(RealTwitterService.class);
      
		 }

}
