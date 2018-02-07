package controllers;

import java.sql.SQLException;

import com.j256.ormlite.dao.Dao;


import models.WithdrawMoneyModel;
import views.WithdrawMoneyView;
import models.DatabaseConnectionSource;


/**
 * 
 * The controller for withdraw which will handle changes to the model as well as updating the view
 *
 * @author Steven Tucci || Jason Kalec
 * @created 2/6/2018
 * @updated 2/7/2018
 */
public class WithdrawMoneyController implements Controller {
	
	// the ui/view that this controller manipulates
	private WithdrawMoneyView view;
	// The data/model that this controller manipulates
	private WithdrawMoneyModel model;
	// The data access object to the model
	private Dao<WithdrawMoneyModel, Integer> dao;
	
	/**
	 * Inits the controller's attached view
	 * inits the model for this view.
	 */
	public WithdrawMoneyController() {
		initView();
		initModel();
		
		view.WithdrawEvent();
	}
	
	/**
	 * Initialize any view setup
	 * example, load specific view with certain parameters
	 */
	@Override
	public void initView() {
		// Create the example view
		view = new WithdrawMoneyView();
		// Add the controller as an observer to the view
		view.addObserver(this);
	}
	
	/**
	 * Initialize withdraw model
	 * 
	 */
	@Override
	public void initModel() {
		// Get the waithdraw dao from the database connection
		dao = WithdrawMoneyModel.getDao();
		// Create a new empty model. This does not create an entry in the database yet.
		model = new WithdrawMoneyModel();
	}
	
	/**
	 * Tell's the controller's attached view to update its ui.
	 * 
	 */
	@Override
	public void updateView() {
		// Create the data object to pass back to the view to update it's ui with
		WithdrawMoneyView.WithdrawMoneyViewData data = new WithdrawMoneyView.WithdrawMoneyViewData();
		// Set the view data's with the new updated information from our model
		data.amount = model.withdrawAmount;
		// Tell the view to update its ui using the data we just built
		view.updateUI(data);
	}
	
	/**
	 * This gets called when some event in the view changes
	 */
	@Override
	public void update(Object data) {
		// Cast our data into the WithdrawMoneyViewData
		WithdrawMoneyView.WithdrawMoneyViewData viewData  = ((WithdrawMoneyView.WithdrawMoneyViewData)data);
		// Update the model's data from the view's data
		model.withdrawAmount = viewData.amount;
		
		try {
			// Tell the dao to create or update the model with our new data
			// This will create the query, execute it and place our new object into the database
			dao.createOrUpdate(model);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		// Since our model has changed now, we need to tell the view to update its ui
		updateView();
	}

	

}
