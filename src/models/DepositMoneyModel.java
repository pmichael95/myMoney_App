package models;

import java.sql.SQLException;

import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.dao.DaoManager;
import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.jdbc.JdbcConnectionSource;
import com.j256.ormlite.table.DatabaseTable;
import com.j256.ormlite.table.TableUtils;

/**
 * 
 * Following the skeleton code created by Steven, I have created the DepositMoneyModel.
 * I have temporarily commented out the account Database Field.
 * 
 * Added an extra field (Deposit Type) essential to our Database
 * 
 * @author Sabrina, Johnny
 * @created 2/1/2018
 * @updated 2/7/2018
 *
 */


// This sets the model's/table's name
@DatabaseTable(tableName = "deposit_money")
public class DepositMoneyModel {
	
	//primary key
	// generatedId automatically creates the primary ids
	// therefore, every time we create a new model, we don't have to know what the next id to set is
	@DatabaseField(generatedId = true)
	public int id;
	
	// Creates a column in the database table as an int, named add_amount
	// add_amount:  the amount to be deposit.
	@DatabaseField 
	public float add_amount;
	
	// Creates a column in the database table as a string, named add_type
	// add_type: the deposit type (Income, Loan, Gift, etc.)
	@DatabaseField 
	public String add_type;
	
	/**
	 * Added Extra Field to our database
	 * Addition: Deposit Type (Income, Loan, Gift, etc.)
	 */
	
	
	
	
	//This attribute has been commented out (account attribute) 
	/*
	//@DatabaseField 
	//public String account;
	*/
	 
	public static Dao<DepositMoneyModel, Integer> getDao()
	{
		try {
			// Get a connection
			JdbcConnectionSource instance = DatabaseConnectionSource.getConnection();
			// Create the dao
			Dao<DepositMoneyModel, Integer> dao = DaoManager.createDao(instance, DepositMoneyModel.class);
			// Create the table if it doesn't exist
			TableUtils.createTableIfNotExists(instance, DepositMoneyModel.class);
			return dao;
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}	
	}
}
