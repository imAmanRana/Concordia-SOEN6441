/**
 * Model Package holds tweet's specific data
 * 
 */
package models;
/**
 * This class acts as a model to store data which gets tweets from the user when text is added in the search bar
 * @author raghav
 * @version 2.0
 */
public class Tweet {

	/**
	 * stores name of user
	 */
	public String user;
	/**
	 * stores keyword 
	 */
	public String text;
	
public Tweet(String user,  String text) {
		super();
		this.user = user;
		
		this.text = text;
	}


}
