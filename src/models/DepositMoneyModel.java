package models;

import java.sql.SQLException;

import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.dao.DaoManager;
import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.jdbc.JdbcConnectionSource;
import com.j256.ormlite.table.DatabaseTable;
import com.j256.ormlite.table.TableUtils;


// This sets the model's/table's name
@DatabaseTable(tableName = "deposit_money")
public class DepositMoneyModel {
	
	//primary key
	// generatedId automatically creates the primary ids
	// therefore, every time we create a new model, we don't have to know what the next id to set is
	@DatabaseField(generatedId = true)
	public int id;
	// Creates a column in the database table as an int, named add_amount
	//add_amount:  the amount to be deposit.
	 @DatabaseField 
	public float add_amount;
	 // Creates a column in the database table as a string, named account
	 //account: Selected account that.
	 @DatabaseField //Accounts should already be choosen...
	public String account;
	 // ... any other fields
	 
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
