package tests;

import java.sql.SQLException;

import com.j256.ormlite.dao.Dao;

import models.DepositMoneyModel;
import models.WithdrawMoneyModel;

/**
 * @author Damian Kazior
 * @created 4/6/2018
 */

public class TestDataFactory {
	
	public static final String  TEST_TYPE = "test type";
	public static final String  TEST_REASON = "test reason";
	public static final String  TEST_DATE = "11-11-2001";
	
	public void createDummyDeposit(Integer amount) {
		
		DepositMoneyModel depositModel = new DepositMoneyModel();
		depositModel.add_amount = amount;
		depositModel.add_type   = TEST_TYPE;
		depositModel.date  		= TEST_DATE;
		Dao<DepositMoneyModel, Integer> dao = DepositMoneyModel.getDao();
		try {
			dao.create(depositModel);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public void createDummyWithdrawal(Integer amount) {
		
		WithdrawMoneyModel withdrawalModel = new WithdrawMoneyModel();
		withdrawalModel.withdrawAmount = amount;
		withdrawalModel.transactionReason = TEST_REASON;
		
		Dao<WithdrawMoneyModel, Integer> dao = WithdrawMoneyModel.getDao();
		try {
			dao.create(withdrawalModel);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
}
