package controllers;
import java.io.IOException;
import java.sql.SQLException;

import com.j256.ormlite.dao.CloseableIterator;
import com.j256.ormlite.dao.Dao;

import models.DisplayBalanceModel;
import views.DisplayBalanceView;

/*
 * DisplayBalanceController
 * 
 * @author Steven Tucci, Shunyu Wang
 * @created 1/29/2018
 * @updated 1/29/2018
 */


public class DisplayBalanceController implements Controller{
	
	private DisplayBalanceView view;
	private DisplayBalanceModel model;
	private Dao<DisplayBalanceModel, Integer> dao;
	

	/**
	 * Inits the controller's attached view
	 * inits the model for this view.
	 */
	public DisplayBalanceController() {
		initView();
		initModel();
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
		// Since our model has changed now, we need to tell the view to update its ui
		updateView();
	}
}
