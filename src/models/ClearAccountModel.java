package models;

import java.sql.SQLException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.dao.DaoManager;
import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.jdbc.JdbcConnectionSource;
import com.j256.ormlite.table.DatabaseTable;
import com.j256.ormlite.table.TableUtils;

/**
 * 
 * 
 * @author Alissa Bellerose
 * @modifiedBy 
 * @created 3/11/2018
 * @updated 
 */

public class ClearAccountModel {
	
//names of the tables to be deleted from the database
final private String TABLE_1 = "WithdrawMoney_model";
final private String TABLE_2 = "deposit_money";
final private String TABLE_3 = "display_balance";


	
JdbcConnectionSource instance = DatabaseConnectionSource.getConnection();
		
	    /**
	     * Connect to the mymoneyappdb.db database
	     *
	     * @return the Connection object
	     */
	    private Connection connect() {
	    	
	        // SQLite connection string
	        String url = "jdbc:sqlite:mymoneyappdb.db";
	        Connection conn = null;
	        try {
	            conn = DriverManager.getConnection(url);
	        } catch (SQLException e) {
	            System.out.println(e.getMessage());
	        }
	        return conn;
	    }
	 
	    /**
	     * Delete a table from the database
	     *
	     */
	    public void ClearAccount() {  
	    	

	    	{
	    		//database queries
		        String table1 = "DELETE FROM "+TABLE_1;
		        String table2 = "DELETE FROM "+TABLE_2;
		        String table3 = "DELETE FROM "+TABLE_3;

		   	 
		        try (Connection conn = this.connect();
		        		
		                PreparedStatement pstmt = conn.prepareStatement(table1)) 

		        {
	                PreparedStatement pstmt2 = conn.prepareStatement(table2) ;
	                PreparedStatement pstmt3 = conn.prepareStatement(table3);
		 
		        	// executes the delete statement
		            pstmt.executeUpdate();
		            pstmt2.executeUpdate();
		            pstmt3.executeUpdate();
		            
		            System.out.println("Account deleted");
		 
		        } catch (SQLException e) {
		            System.out.println(e.getMessage());
		        }
	    	}



	    }    	 
}
