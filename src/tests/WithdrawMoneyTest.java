package tests;

import static org.junit.Assert.*;

import org.junit.Test;

import junit.framework.Assert;


/*
 * This is an example of junit4 unit test cases
 * 
 * Read some tutorials on proper unit testing
 * http://www.vogella.com/tutorials/JUnit/article.html
 * https://www.tutorialspoint.com/junit/junit_test_framework.htm/
 * 
 * 
 * Usually you create a new test class for every class or common feature.
 * And every unit test, tests one method/function/operation. It should not test multiple things at the same time
 * 
 */
public class WithdrawMoneyTest {

	@Test
	public void ExampleTest1() {
		int x = 1;
		int y = 2;
		int expected = 3;
		int actual = x + y;
		//you can assert any types, from int, string, floats, see the link
		//http://junit.org/junit4/javadoc/4.8/org/junit/Assert.html
		
		// usually you have some expected value you know ahead of time to be true
		// and the actual value that your test is trying test
		assertEquals(expected, actual);

	}
	
	
	@Test
	public void WithdrawTest() {
		fail("example failed test");
	}
	
	
	// You can have as many tests here
	// ....

}
