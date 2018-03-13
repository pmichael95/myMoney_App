package models;

import java.sql.SQLException;
import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.dao.DaoManager;
import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.jdbc.JdbcConnectionSource;
import com.j256.ormlite.table.DatabaseTable;
import com.j256.ormlite.table.TableUtils;

/**
 * The controller to show the history of transaction.
 * 
 * @author Ramez Nahas
 * @created 13/03/2018
 *
 */
@DatabaseTable(tableName = "Transaction_History")
public class ShowHistoryModel {
	// primary key
	// generatedId automatically creates the primary ids
	// therefore, every time we create a new model, we don't have to know what the next id to set is
	@DatabaseField(generatedId = true)
	public int id;
	
	// Type of transaction (deposit or withdrawal)
	@DatabaseField
	public String transactionType;
	
	// If withdrawal, what kind of withdrawal
	@DatabaseField
	public String withdrawalType;
	
	// If deposit, what kind of deposit
	@DatabaseField
	public String depositType;
	
	// Amount of the transaction
	@DatabaseField
	public float amount;
	
	/**
	 * Function that returns DAO (Database Access Object)
	 */
	public static Dao<ShowHistoryModel, Integer> getDao() {
		try {
			// Get a connection
			JdbcConnectionSource instance = DatabaseConnectionSource.getConnection();
			// Create the dao
			Dao<ShowHistoryModel, Integer> dao = DaoManager.createDao(instance, ShowHistoryModel.class);
			// Create the table if it doesn't exist
			TableUtils.createTableIfNotExists(instance, ShowHistoryModel.class);
			return dao;
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}
}
