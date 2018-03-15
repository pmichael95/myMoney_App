package views;

import java.util.Scanner;

import models.ClearAccountModel;
import views.DepositMoneyView.DepositMoneyViewData;
import views.ExampleView.ExampleViewData;

/**
 * 
 * 
 * @author Alissa Bellerose
 * @modifiedBy 
 * @created 3/11/2018
 * @updated 
 */

public class ClearAccountView extends View{
		
		
		
		//Constructor 
		public ClearAccountView()
		{
			//empty 
		}
		
		//Method will allow the user to delete (clear) the account
		public void ClearAccountEvent()
		{
			onClearAccountEvent();
		}
		

		public void onClearAccountEvent()
		{
			ClearAccountModel clearAccount = new ClearAccountModel();	
			clearAccount.ClearAccount();


		}

	@Override
	public void updateUI(Object data) {
		//empty
	}

}
