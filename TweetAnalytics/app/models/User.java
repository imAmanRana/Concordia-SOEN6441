/**
 * 
 */
package models;

/**
 * @author Amandeep Singh
 *
 */
public class User {

	private String name;
	private String screenName;
	private String profileImageUrl;
	
	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}
	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}
	/**
	 * @return the screenName
	 */
	public String getScreenName() {
		return screenName;
	}
	/**
	 * @param screenName the screenName to set
	 */
	public void setScreenName(String screenName) {
		this.screenName = screenName;
	}
	/**
	 * @return the profileImageUrl
	 */
	public String getProfileImageUrl() {
		return profileImageUrl;
	}
	/**
	 * @param profileImageUrl the profileImageUrl to set
	 */
	public void setProfileImageUrl(String screenName) {
		this.profileImageUrl = String.format("https://twitter.com/%s/profile_image?size=normal", screenName);
	}
	
	
}
