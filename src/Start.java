import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.jdbc.JdbcConnectionSource;
import javafx.application.*;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import controllers.DisplayBalanceController;
import controllers.DepositMoneyController;
import controllers.WithdrawMoneyController;

// import controllers.ExampleController;

import models.DatabaseConnectionSource;
import models.DisplayBalanceModel;
import models.WithdrawMoneyModel;
import models.DepositMoneyModel;

import views.GUIView;

// import models.ExampleModel;

/**
 * Start is the main entry point to the software
 * 
 * @author Steven Tucci
 * @modifiedBy Johnny Mak, Sabrina D'Mello, Shunyu Wang, Philip Michael
 * @created 1/29/2018
 * @lastUpdated 3/5/2018 
 */
public class Start extends Application {
	
	public static void main(String[] args) {
		// Get a connection to our database before we start any ui
		JdbcConnectionSource source = DatabaseConnectionSource.getConnection();
		
		// Launches the GUI
		launch(args);
		
		if (source != null) {
			//ExampleController test = new ExampleController();

			WithdrawMoneyController withdrawController = new WithdrawMoneyController();

			DepositMoneyController deposit_money = new DepositMoneyController(); //added by Sabrina
			
			DisplayBalanceController dispaly_balance = new DisplayBalanceController();
			
			
			// Once we are done, close the connection to the database 
			DatabaseConnectionSource.closeConncetion();
		} else {
			System.out.println("Could not make a connection to the database");
		}
	}

	@Override
	public void start(Stage primaryStage) throws Exception{
		System.out.println("start called");
		Parent root = FXMLLoader.load(getClass().getResource("GUI.fxml"));
		primaryStage.setTitle("MyMoney Application");
		primaryStage.setScene(new Scene(root, 650, 400));
		primaryStage.setResizable(false); // Prevent fullscreen and drag to resize
		primaryStage.show();
	}

}
