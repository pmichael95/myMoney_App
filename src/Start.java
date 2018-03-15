import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.jdbc.JdbcConnectionSource;
import javafx.application.*;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import controllers.DisplayBalanceController;
import controllers.DepositMoneyController;
import controllers.WithdrawMoneyController;
import controllers.ExampleController;

// import controllers.ExampleController;

import models.DatabaseConnectionSource;
import models.DisplayBalanceModel;
import models.WithdrawMoneyModel;
import models.DepositMoneyModel;
import models.ExampleModel;

// import models.ExampleModel;

/**
 * Start is the main entry point to the software
 * 
 * @author Steven Tucci
 * @modifiedBy Johnny Mak, Sabrina D'Mello, Shunyu Wang, Philip Michael
 * @created 1/29/2018
 * @lastUpdated 3/15/2018 
 */
public class Start extends Application {
	
	public static void main(String[] args) {
		// Launches the GUI
		launch(args);
	}

	// Creates a 'stage' (think: window) with the given FXML file in the Parent object, using primaryStage.
	@Override
	public void start(Stage primaryStage) throws Exception{
		Parent root = FXMLLoader.load(getClass().getResource("Landing_GUI.fxml"));
		primaryStage.setTitle("MyMoney Application");
		primaryStage.setScene(new Scene(root, 625, 400));
		primaryStage.setResizable(false); // Prevent fullscreen and drag to resize
		primaryStage.show();
	}
}
