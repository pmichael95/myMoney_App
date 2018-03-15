package models;

import java.sql.SQLException;

import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.dao.DaoManager;
import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.jdbc.JdbcConnectionSource;
import com.j256.ormlite.table.DatabaseTable;
import com.j256.ormlite.table.TableUtils;

/**
 * An example of a new model/table is made
 * 
 * For our model layer, we are going to use a library called ormlite
 * http://ormlite.com/javadoc/ormlite-core/doc-files/ormlite_1.html#Getting-Started
 * 
 * If you have never used an orm, it basically abstracts the entire database away. 
 * This means, you don't have to write your own hardcoded sql queries for every little operation
 * 
 * Ormlite uses java annotations to automatically create the database queries, and database schema
 * {@link} http://ormlite.com/javadoc/ormlite-core/doc-files/ormlite_2.html#Class-Setup
 * {@link} http://ormlite.com/javadoc/ormlite-core/doc-files/ormlite_toc.html#SEC_Contents 
 * 
 * @author Steven Tucci
 * @created 1/29/2018
 * @updated 1/29/2018
 */

// This sets the model's/table's name
@DatabaseTable(tableName = "example_model")
public class ExampleModel {
	//primary key
	// generatedId automatically creates the primary ids
	// therefore, every time we create a new model, we don't have to know what the next id to set is
	@DatabaseField(generatedId = true)
	public int id;
	// Creates a column in the database table as an int, named field1
	 @DatabaseField
	public int field1;
	 // Creates a column in the database table as a string, named field2
	 @DatabaseField
	public String field2;
	 // ... any other fields
	 

	 /**
	  * Every model needs to implement a get dao method
	  * In order to perform any sql queries, we need to get the dao(data access object) for the model
	  * 
	  * see http://ormlite.com/javadoc/ormlite-core/doc-files/ormlite_2.html#DAO-Setup
	  * the first item in the generic is the actual model class
	  * the second item in the generic is the database id type. in this case the id is an int, so we make in an integer
	  * 
	  * @return returns a dao object
	  */
	 public static Dao<ExampleModel, Integer> getDao() {
		try {
			// Get a connection
			JdbcConnectionSource instance = DatabaseConnectionSource.getConnection();
			// Create the dao
			Dao<ExampleModel, Integer> dao = DaoManager.createDao(instance, ExampleModel.class);
			// Create the table if it doesn't exist
			TableUtils.createTableIfNotExists(instance, ExampleModel.class);
			return dao;
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}

}
