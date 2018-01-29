package models;
import java.io.IOException;

import java.sql.SQLException;

import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.dao.DaoManager;
import com.j256.ormlite.jdbc.JdbcConnectionSource;
import com.j256.ormlite.table.TableUtils;



/**
 * 
 * The DatabaseConnectionSource is an abstraction of the connection to the database

 * Since we are only going to have a single connection to the database, we are going to use the singleton pattern to simply your life.
 * The singleton pattern is a very bad pattern, and should never be used. Usually when it comes to databases, you might want to pool your connections
 * but for this project, that is overkill. While the singleton pattern could be avoided here by passing around the connection reference, 
 * that would make implementing the code more tricky. Also the JDBCConnectionSource is thread unsafe, and only uses a single connection anyway, 
 * so we'll only be able to have a single connection anyway 
 * 
 * @author Steven Tucci 
 * @created 1/29/2018
 * @updated 1/29/2018
 * 
 */
public class DatabaseConnectionSource {
	
	// A reference to the connection 
	static JdbcConnectionSource instance;

	// the file name of the sqllite database file
	private static final String DATABASE_FILE = "mymoneyappdb.db";
	
	/**
	 * Get a connection to the database
	 * 
	 * @return returns a connection to the database. May be null.
	 * The caller must check if the connection is null
	 */
	public static JdbcConnectionSource getConnection() {
		if (instance == null) {
			// Use sqllite
			String databaseUrl = "jdbc:sqlite:" + DATABASE_FILE;
			try {
				instance = new JdbcConnectionSource(databaseUrl);
			} catch (SQLException e) {
				e.printStackTrace();
				instance = null;
			}
		}
		return instance;
	}
	
	/**
	 * Closes the connection to the database
	 * 
	 * The caller must check if the connection is null
	 */
	public static void closeConncetion() {
		if (instance != null) {
			try {
				instance.close();
			} catch (IOException e) {

				e.printStackTrace();
			}
		}
	}
	
	
}
