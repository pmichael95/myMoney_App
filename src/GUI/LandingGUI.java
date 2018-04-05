package GUI;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import models.DatabaseConnectionSource;
import models.DepositMoneyModel;
import models.WithdrawMoneyModel;
import views.DepositMoneyView.DepositMoneyViewData;
import views.WithdrawMoneyView.WithdrawMoneyViewData;
import controllers.*;
import java.util.Optional;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.Date;
import java.util.List;
import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.jdbc.JdbcConnectionSource;

/**
 * The default landing GUI where the buttons (which would open other windows) can be found as well as the balance.
 * 
 * @author Philip Michael
 * @modifiedBy Tobi Decary-Larocque, Ramez Nahas
 * @created 3/5/2018
 * @lastUpdated 04/05/2018 
 */

public class LandingGUI {

	// Static GUI referencing to 'this' for use elsewhere
	public static LandingGUI _GUI;

	// To get confirmation from user
	Alert alert = new Alert(AlertType.CONFIRMATION);
	/**
	 * The controllers for our main functionalities
	 */
	private DisplayBalanceController displayBalance;
	private DepositMoneyController depositMoney;
	private ClearAccountController clear;
	private WithdrawMoneyController withdrawMoney;
	
	// to keep track of current transaction type
	private boolean isDeposit;

	/**
	 * GUI ELEMENTS
	 */
	// -- These @FXML private fields are associated to the fx:id of the individual components in the GUI.
	@FXML 
	private Text balance;
	@FXML
	private Button withdraw;
	@FXML
	private Button deposit;
	@FXML
	private Button showHistory;
	@FXML
	private Button clearAccount;
	@FXML
	private Button exportButton;
	@FXML
	private AnchorPane transactionScene;
	@FXML
	private TextField inputAmount;
	@FXML
	private TextField transactionReason;
	@FXML
	private TextField transactionType;
	@FXML
	private Text title;
	@FXML
	private Text transactionTitle;
	@FXML
	private Text errorMessage;
	@FXML
	private Button doneButton;
	@FXML
	private Button cancelButton;

	/**
	 * Initialize is called after every element/handler was fetched.
	 * The order is: Constructor > Tie the members/handlers > initialize
	 */
	@FXML
	public void initialize() {
		// Get a connection to our database before we start any ui
		JdbcConnectionSource source = DatabaseConnectionSource.getConnection();

		// Creates the static GUI object referencing to the current GUI shown on screen.
		// This is used in the views.
		if(_GUI == null) {
			_GUI = this;
		}

		if (source != null) {
			transactionScene.setVisible(false);
			
			displayBalance = new DisplayBalanceController();

			//load initial balance
			try {
				displayBalance.initialBalance();
			} catch (SQLException e) {
				System.out.println("Could not make a connection to the database");
				e.printStackTrace();
			}
			withdrawMoney = new WithdrawMoneyController();

			depositMoney = new DepositMoneyController();

			// Once we are done, close the connection to the database 
			DatabaseConnectionSource.closeConncetion();
		} else {
			System.out.println("Could not make a connection to the database");
		}
	}

	/**
	 * HELPER FUNCTIONS
	 */
	
	/**
	 * Updates the displayed balance on the main GUI screen.
	 * @param balance(float type) to update on the balance
	 */
//	public void UpdateBalance(float balance) {
//		this.balance.setText("$" + balance);
//	}
	
	/**
	 * Updates the displayed balance on the main GUI screen.
	 * @param balance(String type) to update on the balance
	 */
	public void UpdateBalance(String balance) {
		displayBalanceAction(balance);
		this.balance.setText("$" + balance);
	}
	
	/**
	 * Changes text color of balance depending on its value.
	 * @param balance(String type) to update on the balance
	 */
	private void displayBalanceAction(String balance) {
		Double bal = Double.parseDouble(balance);
		if (bal == 0) {
			this.balance.setFill(Color.BLACK);
		} else if (bal > 0) {
			this.balance.setFill(Color.GREEN);
		} else {
			this.balance.setFill(Color.RED);
		}
	}
	
	/**
	 * Clears the transaction scene of any previous values/data.
	 */
	private void clearTransactionScene() {
		inputAmount.clear();
		transactionReason.clear();
		transactionType.clear();
		errorMessage.setText("");
	}
	
	/**
	 * HANDLER FUNCTIONS
	 */
	// -- These handler functions are tied to the onAction="#[namehere]" of the buttons in the GUI.
	// In them, use the controllers respectively to initiate the functionality of each.

	/**
	 * For withdraw, we'd need to use the Withdraw controller to initiate the withdrawal.
	 * For consistency's sake, we will need to also update the amount in display_balance table.
	 * For that, we will need to also call the DisplayBalance's controller's DAO to update the amount.
	 * Thereafter, the view is updated again for us.
	 * @param event triggered by clicking on the button.
	 * @throws SQLException 
	 */
	@FXML
	protected void withdrawButtonAction(ActionEvent event) throws SQLException {
		isDeposit = false;
		clearTransactionScene();
		title.setVisible(false);
		transactionTitle.setText("WITHDRAWAL");
		transactionType.setPromptText("Type of Withdrawal");
		transactionScene.setVisible(true);
	}

	@FXML
	protected void depositButtonAction(ActionEvent event) throws SQLException {
		isDeposit = true;
		clearTransactionScene();
		title.setVisible(false);
		transactionTitle.setText("DEPOSIT");
		transactionType.setPromptText("Type of Deposit");
		transactionScene.setVisible(true);
	}
	
	@FXML
	protected void doneButtonAction(ActionEvent event) {
		try 
		{
			float amount = Float.parseFloat(inputAmount.getText());
			String transactionReason = this.transactionReason.getText();
			String transactionType = this.transactionType.getText();
			Date date = new Date();
			
			if (isDeposit) {
				DepositMoneyViewData input = new DepositMoneyViewData(amount, transactionType, transactionReason, date);
				depositMoney.update(input);
				displayBalance.updateBalance("deposit", input.amount);
			} else {
				WithdrawMoneyViewData input = new WithdrawMoneyViewData(amount, transactionType, transactionReason, date);
				withdrawMoney.update(input);
				displayBalance.updateBalance("withdraw", input.amount);
			}
			transactionScene.setVisible(false);
			title.setVisible(true);
		}
		catch(Exception e) 
		{
			errorMessage.setText("Value entered must be a number!");
			inputAmount.clear();
		} 
	}
	
	@FXML
	protected void cancelButtonAction(ActionEvent event) {
		transactionScene.setVisible(false);
		title.setVisible(true);
	}

	@FXML
	protected void showHistoryButtonAction(ActionEvent event) {
		// Load and open the new stage.
		try {
			Parent root = FXMLLoader.load(getClass().getResource("../ShowHistory_GUI.fxml"));
			Stage stage = new Stage();
			stage.setTitle("Transaction History");
			stage.setScene(new Scene(root, 1000.0, 700.0));
			stage.setMinWidth(1000.0);
			stage.setMinHeight(700.0);
			stage.show();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@FXML
	protected void clearAccountButtonAction(ActionEvent event) {
		alert.setTitle("Clear Account - Confirmation");
		alert.setHeaderText("This action will permanently delete your account.");
		alert.setContentText("Are you sure you wish to proceed?");

		Optional<ButtonType> result = alert.showAndWait();
		if (result.get() == ButtonType.OK){
			clear = new ClearAccountController(); //the controller automatically calls the delete function
			displayBalance.initModel();
			UpdateBalance("0.00");
		}
	}
	
	@FXML
	protected void exportHistoryButtonAction(ActionEvent event) {
		Dao<DepositMoneyModel, Integer> depositMoneyDao = DepositMoneyModel.getDao();
		Dao<WithdrawMoneyModel, Integer> withdrawMoneyDao = WithdrawMoneyModel.getDao();
		
		StringBuffer history = new StringBuffer("DATE, TRANSACTION TYPE, DESCRIPTION, AMOUNT, TYPE OF WITHDRAWAL, TYPE OF DEPOSIT\r\n");

		try {
			// Get all the data from the deposit money table and add it to history.
			List<DepositMoneyModel> depositMoney = depositMoneyDao.queryForAll();
			for (int i = 0; i < depositMoney.size(); i++) {
				String depositType = depositMoney.get(i).add_type;
				float amount = depositMoney.get(i).add_amount;
				String reason = depositMoney.get(i).transactionReason;
				String date = depositMoney.get(i).date;
				history.append(date + ", ");
				history.append("Deposit, ");
				history.append(reason + ", ");
				history.append(amount + ", ,");
				history.append(depositType + "\r\n");
			}

			// Get all the data from the withdraw money table and add it to history.
			List<WithdrawMoneyModel> withdrawMoney = withdrawMoneyDao.queryForAll();
			for (int i = 0; i < withdrawMoney.size(); i++) {
				String withdrawalType = withdrawMoney.get(i).withdrawType;
				float amount = withdrawMoney.get(i).withdrawAmount;
				String reason = withdrawMoney.get(i).transactionReason;
				String date = withdrawMoney.get(i).date;
				history.append(date + ", ");
				history.append("Withdrawal, ");
				history.append(reason + ", ");
				history.append(amount + ", ");
				history.append(withdrawalType + ", ,\r\n");
			}
			File transactionHistory = new File("Transaction_History.csv");
			PrintWriter pw = new PrintWriter(transactionHistory);
			pw.print(history);
			pw.close();
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}
}
