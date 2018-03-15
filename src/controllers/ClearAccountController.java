package controllers;

import java.sql.SQLException;

import com.j256.ormlite.dao.Dao;


import models.ExampleModel;
import views.ClearAccountView;
import views.ExampleView;
import models.ClearAccountModel;
import models.DatabaseConnectionSource;


/**
 * 
 *
 * @author Alissa Bellerose 
 * @created 3/13/2018
 * @updated 
 */
public class ClearAccountController implements Controller {
	
	// the ui/view that this controller manipulates
	private ClearAccountView view;
	// The data/model that this controller manipulates
	private ClearAccountModel model;
	
	// The data access object to the model
    //private Dao<ClearAccountModel, Integer> dao;
	
	/**
	 * Inits the controller's attached view
	 * inits the model for this view.
	 */
	public ClearAccountController() {
		initView();
		initModel();
		
		view.ClearAccountEvent();
	}
	
	/**
	 * Initialize any view setup
	 * example, load specific view with certain parameters
	 */
	@Override
	public void initView() {
		// Create the example view
		view = new ClearAccountView();
		
		// Add the controller as an observer to the view
		view.addObserver(this);
	}
	
	@Override
	public void initModel() {

		model = new ClearAccountModel();

	}
	
	@Override
	public void updateView() {
		//EMPTY
	}
	
	@Override
	public void update(Object data) {
		
		//EMPTY
		updateView();
	}

	@Override
	public void updateModel(Object data) {
		// TODO Auto-generated method stub
		
	}

	

}