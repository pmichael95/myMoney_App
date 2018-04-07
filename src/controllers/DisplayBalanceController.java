package controllers;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import com.j256.ormlite.dao.CloseableIterator;
import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.stmt.UpdateBuilder;

import GUI.ShowHistoryGUI.HistoryData;
import models.DepositMoneyModel;
import models.DisplayBalanceModel;
import models.WithdrawMoneyModel;
import views.DisplayBalanceView;

/**
 * DisplayBalanceController
 * 
 * @author Shunyu Wang
 * @modifiedBy Johnny Mak, Philip Michael, Tobi Decary-Larocque, Shunyu Wang
 * @created 1/29/2018
 * @updated 3/5/2018
 */


public class DisplayBalanceController implements Controller{
	
	public static final String ACCOUNT_TYPE = "Chequing";
	public static final String DEPOSIT 		= "deposit";
	public static final String WITHDRAW 	= "withdraw";
	
	public DisplayBalanceView view;
	public DisplayBalanceModel model;
	private Dao<DisplayBalanceModel, Integer> dao;

	/**
	 * Inits the controller's attached view
	 * inits the model for this view.
	 */
	public DisplayBalanceController() {
		initView();
		initModel();
		
		view.DisplayEvent();
	}
	
	
	@Override
	public void initView() {
		view = new DisplayBalanceView();
		view.addObserver(this);
	}
	
	/**
	 * create initial model without select any account
	 */
	@Override
	public void initModel() {
		dao = DisplayBalanceModel.getDao();
		model = new DisplayBalanceModel();
		
		model.account = ACCOUNT_TYPE;
	}
	

	@Override
	public void updateView() {
		// Create the data object to pass back to the view to update it's ui with
		DisplayBalanceView.DisplayBalanceViewData data = new DisplayBalanceView.DisplayBalanceViewData();
		// Set the view data's with the new updated information from our model
		data.account = model.account;
		
		data.accountBalance = model.accountBalance;
		// Tell the view to update its ui using the data we just built
		view.updateUI(data);
	}
	

	/**
	 * 
	 * @param data, the view data that has been passed by view.notifyObserver(data)
	 * the data is a snapshot of what the view wants the model to reflect/update with
	 */
	@Override
	public void update(Object data) {
				
		updateModel(data);
		
		// Since our model has changed now, we need to tell the view to update its ui
		updateView();
	}

	/**
	 * Function to update the model once a change has been made 
	 */
	@Override
	public void updateModel(Object data) {
		// Cast our data into the ExampleViewData
		DisplayBalanceView.DisplayBalanceViewData viewData  = ((DisplayBalanceView.DisplayBalanceViewData)data);
		//the account user selected to show balance
		String account = viewData.account;
			
		// iterate through all rows in display_balance table until find the matched one
		CloseableIterator<DisplayBalanceModel> iterator = dao.closeableIterator();
		try {
			while (iterator.hasNext()) {
				DisplayBalanceModel model = iterator.next();
				if (model.account.equals(account)) {
					this.model = model;
				}
			}
		} finally {
			try {
				iterator.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		
	}

	/**
	 * Updates the Balance positively or negatively, depending on the type
	 * @param type, the type of update, can either be withdraw or deposit
	 * @param ammount, the ammount to be subtracted/added
	 * @throws SQLException 
	 */
	public void updateBalance(String type, float amount) throws SQLException {

		if (type.toLowerCase().equals(WITHDRAW) )
		{	
			amount *= -1;
		}
		else if (type.toLowerCase().equals(DEPOSIT))
		{
			//do nothing
		}
		else return; //if different type, it's ignored
		
		model.accountBalance += amount;
		
		UpdateBuilder<DisplayBalanceModel, Integer> updateBuilder = dao.updateBuilder();
		updateBuilder.updateColumnValue("accountBalance", model.accountBalance);
		updateBuilder.update();
		
		updateView();
	}
	
	
	/**
	 * Call this method when starting the application to show a historical balance on account
	 * @throws SQLException 
	 */
	public void initialBalance() throws SQLException {
		DisplayBalanceView.DisplayBalanceViewData data = new DisplayBalanceView.DisplayBalanceViewData();
		Dao<DepositMoneyModel, Integer> depositMoneyDao = DepositMoneyModel.getDao();
		Dao<WithdrawMoneyModel, Integer> withdrawMoneyDao = WithdrawMoneyModel.getDao();
		
		data.account = model.account;

		try {
			float depositSum = 0, withdrawSum = 0;
			
			// balance of all deposit records
			List<DepositMoneyModel> depositMoney = depositMoneyDao.queryForAll();
			for (int i = 0; i < depositMoney.size(); i++) {
				depositSum += depositMoney.get(i).add_amount;
			}

			// balance of all withdraw records
			List<WithdrawMoneyModel> withdrawMoney = withdrawMoneyDao.queryForAll();
			for (int i = 0; i < withdrawMoney.size(); i++) {
				withdrawSum += withdrawMoney.get(i).withdrawAmount;
			}
			
			data.accountBalance = depositSum - withdrawSum;
			model.accountBalance = data.accountBalance;
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		view.updateUI(data);
	}
}
