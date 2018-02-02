package controllers;

import java.sql.SQLException;

import com.j256.ormlite.dao.Dao;

import models.DepositMoneyModel;
import views.DepositMoneyView;

public class DepositMoneyController implements Controller{

	private DepositMoneyView view;
	private DepositMoneyModel model;
	private Dao<DepositMoneyModel, Integer> dao;
	
	//Default DespositMoneyController Constructor
	public DepositMoneyController()
	{
		initView();
		initModel();
	}
	
	
	@Override
	public void updateView() 
	{		
		DepositMoneyView.DepositMoneyViewData data = new DepositMoneyView.DepositMoneyViewData();
		
		// Set the view data's with the new updated information from our model
		data.amount = model.add_amount;
		data.account = model.account;
		
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
		
		DepositMoneyView.DepositMoneyViewData viewData  = ((DepositMoneyView.DepositMoneyViewData)data);
		
		// Update the model's data from the view's data
		model.add_amount = viewData.amount;
		model.account = viewData.account;
		
		try 
		{
			dao.createOrUpdate(model);
		} 
		catch (SQLException e) 
		{
			e.printStackTrace();
		}
		
		// Since our model has changed now, we need to tell the view to update its ui
		updateView();
		
	}

}
