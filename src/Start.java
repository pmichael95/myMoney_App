import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.jdbc.JdbcConnectionSource;

import controllers.DisplayBalanceController;
import controllers.ExampleController;
import models.DatabaseConnectionSource;
import models.DisplayBalanceModel;

/**
 * Start is the main entry point to the software
 * 
 * @author Steven Tucci
 * @created 1/29/2018
 * @lastUpdated 1/29/2018 
 */
public class Start {
	
	public static void main(String[] args) {
		// Get a connection to our database before we start any ui
		JdbcConnectionSource source = DatabaseConnectionSource.getConnection();
		
		
		if (source != null) {
			ExampleController test = new ExampleController();
			DisplayBalanceController dispaly_balance = new DisplayBalanceController();
			DatabaseConnectionSource.closeConncetion();
		} else {
			System.out.println("Could not make a connection to the database");
		}
	}

}
