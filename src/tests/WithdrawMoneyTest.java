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


/**
 * 
 * Created to Test the Withdraw Money Use Case
 *
 * @author Jason Kalec
 * @modifiedBy Johnny Mak
 * @created 2/6/2018
 * @updated 2/11/2018
 */

public class WithdrawMoneyTest {
	
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
