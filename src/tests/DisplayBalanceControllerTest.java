package tests;

import java.sql.SQLException;
import org.junit.Test;
import controllers.DisplayBalanceController;
import views.DisplayBalanceView.DisplayBalanceViewData;
import static org.junit.Assert.*;

/**
 * @author Damian Kazior
 * @created 4/6/2018
 */

public class DisplayBalanceControllerTest {
	
	public static final Integer TEST_AMOUNT = 100;
	
	@Test
	public void initialBalanceTest() {
		
		TestDataFactory dataFactory = new TestDataFactory();
		dataFactory.createDummyDeposit(TEST_AMOUNT);
	
		DisplayBalanceController displayController = new DisplayBalanceController();
		try {
			displayController.initialBalance();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		assertNotEquals(TEST_AMOUNT, (int)displayController.model.accountBalance, '0'); 
	}
	
	@Test
	public void updateBalanceTest() {
		
		DisplayBalanceController displayController = new DisplayBalanceController();
		try {
			displayController.updateBalance(DisplayBalanceController.DEPOSIT, TEST_AMOUNT);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		assertEquals(TEST_AMOUNT, (int)displayController.model.accountBalance, '0'); 
	}

	@Test
	public void updateModelTest() {
		
		DisplayBalanceController displayController = new DisplayBalanceController();
		DisplayBalanceViewData testData = new DisplayBalanceViewData();
		testData.account = "inexistingAccount";
		displayController.updateModel(testData);
	}
}
