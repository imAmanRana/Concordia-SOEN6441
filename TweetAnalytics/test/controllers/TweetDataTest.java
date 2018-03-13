package controllers;

import static org.junit.Assert.*;

import org.junit.Test;

public class TweetDataTest {

	@Test
	public void test() {
		TweetData td=new TweetData();
		td.setKeyword("satnam");
		assertEquals("satnam",td.getKeyword());
	}

}
