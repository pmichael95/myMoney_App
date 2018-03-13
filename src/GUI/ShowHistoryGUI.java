package GUI;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import views.ShowHistoryView.ShowHistoryViewData;

/**
 * The controller to show the history of transaction.
 * 
 * @author Ramez Nahas
 * @created 13/03/2018
 *
 */
public class ShowHistoryGUI {

	// Static GUI referencing to 'this' for use elsewhere
	public static ShowHistoryGUI _ShowHistoryGUI;

	/**
	 * GUI ELEMENTS.
	 * These @FXML private fields are associated to the fx:id of the individual components in the GUI.
	 */
	@FXML
	private TableView<ShowHistoryViewData> table;
	@FXML
	private TableColumn<ShowHistoryViewData, String> transactionType;
	@FXML
	private TableColumn<ShowHistoryViewData, String> withdrawalType;
	@FXML
	private TableColumn<ShowHistoryViewData, String> depositType;
	@FXML
	private TableColumn<ShowHistoryViewData, Float> amount;

	public void initialize() {
		// Creates the static GUI object referencing to the current GUI shown on screen.
		// This is used in the views.
		if(_ShowHistoryGUI == null) {
			_ShowHistoryGUI = this;
		}

		transactionType.setCellValueFactory(new PropertyValueFactory<ShowHistoryViewData, String>("TransactionType"));
		withdrawalType.setCellValueFactory(new PropertyValueFactory<ShowHistoryViewData, String>("withdrawalType"));
		depositType.setCellValueFactory(new PropertyValueFactory<ShowHistoryViewData, String>("depositType"));
		amount.setCellValueFactory(new PropertyValueFactory<ShowHistoryViewData, Float>("amount"));
	}

	public void updateHistoryTable(ObservableList<ShowHistoryViewData> tableData) {
		table.setItems((ObservableList<ShowHistoryViewData>)tableData);
	}
}
