package models;

import java.sql.SQLException;

import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.dao.DaoManager;
import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.jdbc.JdbcConnectionSource;
import com.j256.ormlite.table.DatabaseTable;
import com.j256.ormlite.table.TableUtils;

/**
 * Table Structure model for the Withdraw table containing elements such as:
 * WithdrawID, Ammount Withdrawn, New Account Balance, ID of account from which money has been withdrawn
 * 
 * @author Steven Tucci || Jason Kalec
 * @created 2/6/2018
 * @updated 2/7/2018
 */

// This sets the model's/table's name
@DatabaseTable(tableName = "WithdrawMoney_model")
public class WithdrawMoneyModel {
	//primary key
	// generatedId automatically creates the primary ids
	// therefore, every time we create a new model, we don't have to know what the next id to set is
	@DatabaseField(generatedId = true)
	public int id;
	// Field following the id, the amount of money that was taken from account
	 @DatabaseField
	public float withdrawAmount;
	 
	 @DatabaseField
	public String withdrawType;
	 
	//Create more fields such as account balance following withdraw and from which account it was withdrawn
	 //@DatabaseField
	//public float newAccountBalance;
	 
	 //@DatabaseField
	//public int accountId;

	 
	 public static Dao<WithdrawMoneyModel, Integer> getDao() {
		try {
			// Get a connection
			JdbcConnectionSource instance = DatabaseConnectionSource.getConnection();
			// Create the dao
			Dao<WithdrawMoneyModel, Integer> dao = DaoManager.createDao(instance, WithdrawMoneyModel.class);
			// Create the table if it doesn't exist
			TableUtils.createTableIfNotExists(instance, WithdrawMoneyModel.class);
			return dao;
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}

}