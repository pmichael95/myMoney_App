import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.jdbc.JdbcConnectionSource;

import controllers.ExampleController;
import controllers.WithdrawMoneyController;
import models.DatabaseConnectionSource;
import models.ExampleModel;
import models.WithdrawMoneyModel;

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
			WithdrawMoneyController withdrawController = new WithdrawMoneyController();
			// Once we are done, close the connection to the database 
			DatabaseConnectionSource.closeConncetion();
		} else {
			System.out.println("Could not make a connection to the database");
		}
	}

}
