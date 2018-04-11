package models;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.List;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

/***
 * Tests the SearchResults class
 * 
 * @author kamal
 *
 */
public class SearchResultsTest {

	public static final String keyword = "Bitcoin";
	public static final ArrayList<Tweet> list = new ArrayList<>();;

	static SearchResults searchResult;

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {

		list.add(new Tweet("Jane", "BitCoin is going up!!!"));
		list.add(new Tweet("anna", "Bitcoin is crap"));

		searchResult = new SearchResults(keyword, list);
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
		searchResult = null;
	}

	/**
	 * Test method for {@link models.SearchResults#getKeyword()}.
	 */

	@Test
	public void testKeyword() {
		assertEquals(keyword, (searchResult.getKeyword()));
	}

	/**
	 * Test method for {@link models.SearchResults#getTweet()}.
	 */

	@Test
	public void testListOfTweets() {
		for (int i = 0; i < list.size(); i++) {
			List<Tweet> list2 = searchResult.getTweet();
			assertTrue(list.equals(list2));
		}
	}

}
