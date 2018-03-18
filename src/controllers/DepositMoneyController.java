package controllers;

import java.sql.SQLException;

import com.j256.ormlite.dao.Dao;

import models.DepositMoneyModel;
import views.DepositMoneyView;

/**
 * 
 * The controller to deposit money into the account.
 * 
 * @author Sabrina D'Mello
 * @modifiedBy Johnny Mak, Philip Michael, Shunyu Wang
 * @created 2/1/2018
 * @updated 3/5/2018
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
		view.DepositEvent();		
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

	/**
	 * Function to update the model once a change has been made 
	 */
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
			// Tell the dao to create a new table row, which is a deposit transaction record
			// This will create the query, execute it and place our new object into the database
			dao.create(model);
		} 
		catch (SQLException e) 
		{
			e.printStackTrace();
		}
	}

}
