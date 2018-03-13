package controllers;

import java.sql.SQLException;
import com.j256.ormlite.dao.Dao;
import javafx.collections.ObservableList;
import models.ShowHistoryModel;
import views.ShowHistoryView;
import views.ShowHistoryView.ShowHistoryViewData;

/**
 * The controller to show the history of transaction.
 * 
 * @author Ramez Nahas
 * @created 13/03/2018
 *
 */
public class ShowHistoryController implements Controller {

	private ShowHistoryView view;
	private ShowHistoryModel model;
	private Dao<ShowHistoryModel, Integer> dao;

	public ShowHistoryController() {
		initView();
		initModel();
		view.showHistoryEvent();
	}

	@Override
	public void initView() {
		view = new ShowHistoryView();
		view.addObserver(this);
	}

	@Override
	public void initModel() {
		dao = ShowHistoryModel.getDao();
		model = new ShowHistoryModel();
	}

	@Override
	public void update(Object data) {
		updateModel(data);
		updateView();
	}

	@Override
	public void updateView() {
		view.updateUI(view.getHistory());
	}

	//TODO: fix this part, as db table for this model is not updating correctly.
	@Override
	public void updateModel(Object data) {

		// check data's type before casting
		if (data == null || !(data instanceof ObservableList))
			return;

		ObservableList<ShowHistoryViewData> historyData = (ObservableList<ShowHistoryViewData>)data;

		for (int i = 0; i < historyData.size(); i++) {
			model.transactionType = historyData.get(i).getTransactionType();
			model.withdrawalType = historyData.get(i).getWithdrawalType();
			model.depositType = historyData.get(i).getDepositType();
			model.amount = historyData.get(i).getAmount();

			try 
			{
				//				dao.createOrUpdate(model);
				dao.createIfNotExists(model);
			} 
			catch (SQLException e) 
			{
				e.printStackTrace();
			}
		}
	}


}
