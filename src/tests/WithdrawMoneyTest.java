package tests;

import static org.junit.Assert.*;

import java.sql.SQLException;
import java.util.List;

import org.junit.Test;

import com.j256.ormlite.dao.Dao;

import controllers.DepositMoneyController;
import controllers.WithdrawMoneyController;
import junit.framework.Assert;
import models.WithdrawMoneyModel;
import views.WithdrawMoneyView.WithdrawMoneyViewData;


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
		WithdrawMoneyViewData userInput = new WithdrawMoneyViewData ();
		userInput.amount = 50;
		userInput.type   = "Bill";
		WithdrawMoneyController contr = new WithdrawMoneyController();
		contr.updateModel(userInput);
		Dao<WithdrawMoneyModel, Integer> dao = WithdrawMoneyModel.getDao();
		
		List<WithdrawMoneyModel> query;
		try {
			query = dao.query(dao.queryBuilder().orderBy("id", false).limit(1L).prepare());
			if (query.isEmpty()) {
				fail();
			}
			else {
				WithdrawMoneyModel actual = query.get(0);
				WithdrawMoneyModel expected = new WithdrawMoneyModel();
				expected.withdrawAmount = 50;
				expected.withdrawType = "Bill";
				
				assertEquals(expected.withdrawAmount, actual.withdrawAmount, 1.0f);
				assertEquals(expected.withdrawType, actual.withdrawType);
			}
			
			
		} catch (SQLException e) {
			fail("Deposit Failed");
		}	
	}
	
	
	// You can have as many tests here
	// ....

}
