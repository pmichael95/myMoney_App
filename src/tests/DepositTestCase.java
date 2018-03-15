package tests;
import static org.junit.Assert.*;

import java.sql.SQLException;
import java.util.List;

import org.junit.Test;

import com.j256.ormlite.dao.Dao;

import controllers.DepositMoneyController;
import models.DepositMoneyModel;
import views.DepositMoneyView.DepositMoneyViewData;

/**
 * 
 * Created to Test the DepositMoney use cases...
 * 
 * @author Sabrina
 * @modifiedBy Johnny Mak
 * @created 2/1/2018
 * @updated 2/11/2018
 *
 */

public class DepositTestCase {

	@Test
	public void DepositTest() {
		
		DepositMoneyViewData userInput = new DepositMoneyViewData();
		userInput.amount = 100;
		userInput.type   = "Pay";
		DepositMoneyController contr = new DepositMoneyController();
		contr.updateModel(userInput);
		Dao<DepositMoneyModel, Integer> dao = DepositMoneyModel.getDao();
		
		List<DepositMoneyModel> daoq;
		try {
			daoq = dao.query(dao.queryBuilder().orderBy("id", false).limit(1L).prepare());
			if (daoq.isEmpty()) fail();
			else {
				DepositMoneyModel actual = daoq.get(0);
				DepositMoneyModel expected = new DepositMoneyModel();
				expected.add_amount = 100;
				expected.add_type = "Pay";
				
				assertEquals(expected.add_amount, actual.add_amount, 1.0f);
				assertEquals(expected.add_type, actual.add_type);
			}
			
			
		} catch (SQLException e) {
			fail("Deposit Failed");
		}	
	}

}
