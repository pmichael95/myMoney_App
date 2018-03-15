package GUI;

import java.sql.SQLException;
import java.util.List;
import com.j256.ormlite.dao.Dao;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import models.DepositMoneyModel;
import models.WithdrawMoneyModel;
//import views.ShowHistoryView.ShowHistoryViewData;

/**
 * The Show History GUI which displays a table containing the transaction history.
 * 
 * @author Ramez Nahas
 * @created 13/03/2018
 *
 */
public class ShowHistoryGUI {

	// Static GUI referencing to 'this' for use elsewhere
	public static ShowHistoryGUI _ShowHistoryGUI;

	/**
	 * Inner class representing the data to be displayed by the table.
	 * @author Ramez Nahas
	 * @created 13/03/2018
	 */
	public static class HistoryData {
		private String transactionType;
		private String withdrawalType;
		private String depositType;
		private float amount;

		public HistoryData(String transactionType, String withdrawalType, String depositType, float amount) {
			this.transactionType = transactionType;
			this.withdrawalType = withdrawalType;
			this.depositType = depositType;
			this.amount = amount;
		}

		public String getTransactionType() {
			return transactionType;
		}
		public void setTransactionType(String transactionType) {
			this.transactionType = transactionType;
		}
		public String getWithdrawalType() {
			return withdrawalType;
		}
		public void setWithdrawalType(String withdrawalType) {
			this.withdrawalType = withdrawalType;
		}
		public String getDepositType() {
			return depositType;
		}
		public void setDepositType(String depositType) {
			this.depositType = depositType;
		}
		public float getAmount() {
			return amount;
		}
		public void setAmount(float amount) {
			this.amount = amount;
		}
	}

	/**
	 * GUI ELEMENTS.
	 * These @FXML private fields are associated to the fx:id of the individual components in the GUI.
	 */
	@FXML
	private TableView<HistoryData> table;
	@FXML
	private TableColumn<HistoryData, String> transactionType;
	@FXML
	private TableColumn<HistoryData, String> withdrawalType;
	@FXML
	private TableColumn<HistoryData, String> depositType;
	@FXML
	private TableColumn<HistoryData, Float> amount;

	public void initialize() {
		// Creates the static GUI object referencing to the current GUI shown on screen.
		// This is used in the views.
		if(_ShowHistoryGUI == null) {
			_ShowHistoryGUI = this;
		}
		
		// ObservableList needed in order to populate the table elements.
		ObservableList<HistoryData> history = FXCollections.observableArrayList();
		populateHistory(history);

		// Associate data with columns.
		transactionType.setCellValueFactory(new PropertyValueFactory<HistoryData, String>("TransactionType"));
		withdrawalType.setCellValueFactory(new PropertyValueFactory<HistoryData, String>("withdrawalType"));
		depositType.setCellValueFactory(new PropertyValueFactory<HistoryData, String>("depositType"));
		amount.setCellValueFactory(new PropertyValueFactory<HistoryData, Float>("amount"));
		
		// Add data to the table.
		table.setItems(history);
	}

	/**
	 * Populates history with the deposit and withdraw transactions.
	 */
	private void populateHistory(ObservableList<HistoryData> history) {
		Dao<DepositMoneyModel, Integer> depositMoneyDao = DepositMoneyModel.getDao();
		Dao<WithdrawMoneyModel, Integer> withdrawMoneyDao = WithdrawMoneyModel.getDao();

		try {
			// Get all the data from the deposit money table and add it to history.
			List<DepositMoneyModel> depositMoney = depositMoneyDao.queryForAll();
			for (int i = 0; i < depositMoney.size(); i++) {
				String depositType = depositMoney.get(i).add_type;
				float amount = depositMoney.get(i).add_amount;
				history.add(new HistoryData("Deposit", "", depositType, amount));
			}

			// Get all the data from the withdraw money table and add it to history.
			List<WithdrawMoneyModel> withdrawMoney = withdrawMoneyDao.queryForAll();
			for (int i = 0; i < withdrawMoney.size(); i++) {
				String withdrawalType = withdrawMoney.get(i).withdrawType;
				float amount = withdrawMoney.get(i).withdrawAmount;
				history.add(new HistoryData("Withdawal", withdrawalType, "", amount));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
