package controllers;

import java.sql.SQLException;

import com.j256.ormlite.dao.Dao;


import models.WithdrawMoneyModel;
import views.WithdrawMoneyView;
import models.DatabaseConnectionSource;


/**
 * 
 * Example controller shows a very simple way of how a model/view/controller is made;
 * The controller is normally one use case.
 * 
 * A new controller should implement the Controller interface and implement the methods.
 * The example controller provides some simple examples of how to implement all the methods
 * and more is explained in detail in each method's comment
 * 
 * The controller holds a View and a Model or many Models(though many models are more complex)
 * In the Controller constructor, you should call initView(), and initModel() to setup the view and model state
 * 
 * The controller Also needs a Dao (Data access object) in order to execute sql queries
 * For new every table/model, we need to create a new Dao
 * @see models.ExampleModel#getDao()
 *
 * @author Steven Tucci 
 * @created 1/29/2018
 * @updated 1/29/2018
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
	 * Initialize any model loading from the database
	 * example, load model with id = 4
	 * 
	 * In here, you will need to get access to that model's created dao object
	 * if the there is no Dao object for that specific model, then you will need to create the corresponding
	 * getModelDao method in the DatabaseConnectionSource.
	 * 
	 * Once there is a reference to the model's dao, we can create a new model object, 
	 * or load a specific data from the database 
	 */
	@Override
	public void initModel() {
		// Get the example dao from the database connection
		dao = WithdrawMoneyModel.getDao();
		// Create a new empty model. This does not create an entry in the database yet.
		// In order to update the database, we need to call .create(), or update().
		// see http://ormlite.com/javadoc/ormlite-core/com/j256/ormlite/dao/Dao.html for more information on how to perform queries
		model = new WithdrawMoneyModel();
		
//	 	Or maybe, you want to get a specific model with id = 4	
//		try {
//			model = dao.queryForId(4);
//		} catch (SQLException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
	}
	
	/**
	 * Tell's the controller's attached view to update its ui.
	 * 
	 * The programmer must manually call this method. This method updates the attached view.
	 * For example lets says we just handled an event in the controller, the model data might have changed.
	 * In order for the view to reflect these changes, you must call this method.
	 */
	@Override
	public void updateView() {
		// Create the data object to pass back to the view to update it's ui with
		// The view should create a class that holds all the necessary view data
		WithdrawMoneyView.WithdrawMoneyViewData data = new WithdrawMoneyView.WithdrawMoneyViewData();
		// Set the view data's with the new updated information from our model
		data.amount = model.withdrawAmount;
		// Tell the view to update its ui using the data we just built
		view.updateUI(data);
	}
	
	/**
	 * This gets called when some event in the view changes
	 * This might either be a button event or some other event.
	 * When this even happens, instead of the view handling logic delegate the event to
	 * this controller. This update should handle some sort of command and delegate it to internal methods
	 * This method is the opposite of updateView().
	 * 
	 * @param data, the view data that has been passed by view.notifyObserver(data)
	 * the data is a snapshot of what the view wants the model to reflect/update with
	 */
	@Override
	public void update(Object data) {
		// Cast our data into the ExampleViewData
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
