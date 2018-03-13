package controllersTest;

import static org.junit.Assert.*;

import org.junit.Test;

import controllers.TweetData;

/**
 *Tests the controller class  TweetData 
 * @author Satnam Singh
 *
 */
public class TweetDataTest {

	/**
	 *Tests the 
	 *{@link controllers.TweetData#setKeyword(String keyword)  setKeyword}
	 *method of TweetData class
	 * 
	 *
	 */
	@Test
	public void test() {
		TweetData td=new TweetData();
		td.setKeyword("satnam");
		assertEquals("satnam",td.getKeyword());
	}

}
