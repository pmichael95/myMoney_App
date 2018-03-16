package models;

import java.sql.SQLException;

import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.dao.DaoManager;
import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.jdbc.JdbcConnectionSource;
import com.j256.ormlite.table.DatabaseTable;
import com.j256.ormlite.table.TableUtils;

/**
 * DisplayBalance Model created by following the skeleton
 * 
 * @author Steven Tucci, Shunyu Wang, Tobi Decary-Larocque
 * @created 1/29/2018
 * @updated 1/29/2018
 */

// This sets the model's/table's name
@DatabaseTable(tableName = "display_balance")
public class DisplayBalanceModel {
	//foreign key refer to the primary key of account 
	@DatabaseField(generatedId = true)
	public int id;
	
	@DatabaseField
	public String account;
	// Creates a column in the database table as accountBalance, named accountBalance
	@DatabaseField
	public float accountBalance;

	 

	/**
	 * Function that returns DAO (Database Access Object) 
	 */
	 public static Dao<DisplayBalanceModel, Integer> getDao() {
		try {
			// Get a connection
			JdbcConnectionSource instance = DatabaseConnectionSource.getConnection();
			// Create the dao
			Dao<DisplayBalanceModel, Integer> dao = DaoManager.createDao(instance, DisplayBalanceModel.class);
			// Create the table if it doesn't exist
			TableUtils.createTableIfNotExists(instance, DisplayBalanceModel.class);
			return dao;
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}

}