package controllers;

import java.sql.SQLException;

import com.j256.ormlite.dao.Dao;

import models.DepositMoneyModel;
import views.DepositMoneyView;

/**
 * 
 * Following the skeleton code created by Steven, I have created the DepositMoneyController.
 * Currently I have commented out the account attributes, because I have commented out from the 
 * DepositMoneyView the addAccount methods.
 * 
 * Added an extra field (Deposit Type) essential to our Database
 * 
 * @author Sabrina, Johnny
 * @created 2/1/2018
 * @updated 2/7/2018
 *
 */
public class DepositMoneyController implements Controller{

	private DepositMoneyView view;
	private DepositMoneyModel model;
	private Dao<DepositMoneyModel, Integer> dao;
	
	//Default DespositMoneyController Constructor
	public DepositMoneyController()
	{
		initView();
		initModel();
		//view.addAccount();
		//view.DepositEvent();		
	}
	
	
	@Override
	public void updateView() 
	{		
		DepositMoneyView.DepositMoneyViewData data = new DepositMoneyView.DepositMoneyViewData();
		
		// Set the view data's with the new updated information from our model
		data.amount = model.add_amount;
		//data.account = model.account;
		data.type = model.add_type;
		
		// Tell the view to update its ui using the data we just built
		view.updateUI(data);
		
	}

	@Override
	public void initView() 
	{	
		view = new DepositMoneyView();
		view.addObserver(this);
	}

	@Override
	public void initModel() 
	{
		dao = DepositMoneyModel.getDao();
		model = new DepositMoneyModel();
	}

	@Override
	public void update(Object data) 
	{
		
		updateModel(data);
		
		// Since our model has changed now, we need to tell the view to update its ui
		updateView();
		
	}


	@Override
	public void updateModel(Object data) {
		// TODO Auto-generated method stub
		
		DepositMoneyView.DepositMoneyViewData viewData  = ((DepositMoneyView.DepositMoneyViewData)data);
		
		// Update the model's data from the view's data
		model.add_amount = viewData.amount;
		//model.account  = viewData.account;
		model.add_type   = viewData.type;
		
		try 
		{
			dao.createOrUpdate(model);
		} 
		catch (SQLException e) 
		{
			e.printStackTrace();
		}
	}

}
