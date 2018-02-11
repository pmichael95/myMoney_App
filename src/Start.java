import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.jdbc.JdbcConnectionSource;

import controllers.DisplayBalanceController;
import controllers.DepositMoneyController;
import controllers.WithdrawMoneyController;

// import controllers.ExampleController;

import models.DatabaseConnectionSource;
import models.DisplayBalanceModel;
import models.WithdrawMoneyModel;
import models.DepositMoneyModel;

// import models.ExampleModel;



/**
 * Start is the main entry point to the software
 * 
 * @author Steven Tucci
 * @modifiedBy Johnny Mak, Sabrina D'Mello, Shunyu Wang
 * @created 1/29/2018
 * @lastUpdated 2/11/2018 
 */
public class Start {
	
	public static void main(String[] args) {
		// Get a connection to our database before we start any ui
		JdbcConnectionSource source = DatabaseConnectionSource.getConnection();
		
		if (source != null) {
			//ExampleController test = new ExampleController();

			WithdrawMoneyController withdrawController = new WithdrawMoneyController();

			DepositMoneyController deposit_money = new DepositMoneyController(); //added by Sabrina
			
			DisplayBalanceController dispaly_balance = new DisplayBalanceController();
			
			// Once we are done, close the connection to the database 
			DatabaseConnectionSource.closeConncetion();
		} else {
			System.out.println("Could not make a connection to the database");
		}
	}

}
