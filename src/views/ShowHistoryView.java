package views;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import com.j256.ormlite.dao.Dao;
import GUI.ShowHistoryGUI;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import models.DepositMoneyModel;
import models.WithdrawMoneyModel;

/**
 * The controller to show the history of transaction.
 * 
 * @author Ramez Nahas
 * @created 13/03/2018
 *
 */
public class ShowHistoryView extends View {

	/**
	 * Nested class representing the data present in this view.
	 * @author Ramez Nahas
	 * @created 13/03/2018
	 *
	 */
	public static class ShowHistoryViewData {
		private String transactionType;
		private String withdrawalType;
		private String depositType;
		private float amount;

		public ShowHistoryViewData(String transactionType, String withdrawalType, String depositType, float amount) {
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

	// ObservableList needed in order to populate the table elements.
	private ObservableList<ShowHistoryViewData> history;

	public ShowHistoryView() {
		history = FXCollections.observableArrayList();
	}

	public void showHistoryEvent() {
		fetchHistory();
		notifyObservers(history);
	}
	
	/**
	 * Populates history with the deposit and withdraw transactions.
	 */
	private void fetchHistory() {
		Dao<DepositMoneyModel, Integer> depositMoneyDao = DepositMoneyModel.getDao();
		Dao<WithdrawMoneyModel, Integer> withdrawMoneyDao = WithdrawMoneyModel.getDao();

		try {
			// Get all the data from the deposit money table and add it to history.
			List<DepositMoneyModel> depositMoney = depositMoneyDao.queryForAll();
			for (int i = 0; i < depositMoney.size(); i++) {
				String depositType = depositMoney.get(i).add_type;
				float amount = depositMoney.get(i).add_amount;
				history.add(new ShowHistoryViewData("Deposit", "", depositType, amount));
			}

			// Get all the data from the withdraw money table and add it to history.
			List<WithdrawMoneyModel> withdrawMoney = withdrawMoneyDao.queryForAll();
			for (int i = 0; i < withdrawMoney.size(); i++) {
				String withdrawalType = withdrawMoney.get(i).withdrawType;
				float amount = withdrawMoney.get(i).withdrawAmount;
				history.add(new ShowHistoryViewData("Withdawal", withdrawalType, "", amount));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	/**
	 * Regular getter.
	 * @return history, of type ObservableList<ShowHistoryViewData>
	 */
	public ObservableList<ShowHistoryViewData> getHistory() {
		return history;
	}

	@Override
	public void updateUI(Object data) {
		// check data's type before casting
		if (data == null || !(data instanceof ObservableList))
			return;
		
		ObservableList<ShowHistoryViewData> historyData = (ObservableList<ShowHistoryViewData>)data;
		try {
			Parent root = FXMLLoader.load(getClass().getResource("../ShowHistory_GUI.fxml"));
			Stage stage = new Stage();
			stage.setTitle("Transaction History");
			stage.setScene(new Scene(root, 600, 700));
			stage.show();
		} catch (IOException e) {
			e.printStackTrace();
		}
		ShowHistoryGUI._ShowHistoryGUI.updateHistoryTable(historyData);
	}

}
