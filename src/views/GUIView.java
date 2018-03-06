package views;

import javafx.event.*;
import javafx.scene.text.*;
import javafx.stage.Stage;
import javafx.fxml.*;
import javafx.scene.control.*;
import javafx.application.*;
import javafx.scene.Parent;
import javafx.scene.Scene;

public class GUIView extends View {
/**
 * 
 * @author Philip Michael
 * @created 3/5/2018
 * @updated 3/5/2018
 * 
 */
	
	// The data that the GUI holds (UI elements/fields)
	public static class GUIViewData {
		@FXML 
		private Text _balance;
		@FXML
		private Button withdrawBtn;
		@FXML
		private Button depositBtn;
		@FXML
		private Button editTransactionBtn;
		@FXML
		private Button recurringPaymentBtn;
		@FXML
		private Button deleteAccountBtn;
	}
	
	@FXML
	protected void handleWithdrawBtnAction(ActionEvent event) {
		// Handles pressing the withdraw amount button
	}
	
	@FXML
	protected void handleDepositBtnAction(ActionEvent event) {
		// Handles pressing the deposit amount button
	}
	
	@FXML
	protected void handleEditTransactionBtnAction(ActionEvent event) {
		// Handles pressing the edit transaction button
	}
	
	@FXML
	protected void handleRecurringPaymentBtnAction(ActionEvent event) {
		// Handles pressing the recurring payment button
	}
	
	@FXML
	protected void handleDeleteAccountBtnAction(ActionEvent event) {
		// Handles pressing the delete account button
	}

	@Override
	public void updateUI(Object data) {
		// TODO Auto-generated method stub
		
	}
}
