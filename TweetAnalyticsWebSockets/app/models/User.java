/**
 * 
 */
package models;

/**
 * Model class defines the structure of a User
 * @author Amandeep Singh
 */
public class User {

	private String name;
	private String screenName;
	private String profileImageUrl;
	
	/**
	 * @return the name of the user
	 */
	public String getName() {
		return name;
	}
	/**
	 * @param name  the name of user
	 */
	public void setName(String name) {
		this.name = name;
	}
	/**
	 * @return the screenName of the user
	 */
	public String getScreenName() {
		return screenName;
	}
	/**
	 * @param screenName  screenname of the user
	 */
	public void setScreenName(String screenName) {
		this.screenName = screenName;
	}
	/**
	 * @return the profileImageUrl of the user
	 */
	public String getProfileImageUrl() {
		return profileImageUrl;
	}
	/**
	 * @param profileImageUrl the profileImageUrl to set
	 */
	public void setProfileImageUrl(String profileImageUrl) {
		this.profileImageUrl = profileImageUrl;
	}
	
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "User [name=" + name + ", screenName=" + screenName + ", profileImageUrl=" + profileImageUrl + "]";
	}
	
	
}
