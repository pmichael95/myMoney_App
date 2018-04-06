import javafx.application.*;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 * Start is the main entry point to the software
 * 
 * @author Steven Tucci
 * @modifiedBy Johnny Mak, Sabrina D'Mello, Shunyu Wang, Philip Michael, Ramez nahas
 * @created 1/29/2018
 * @lastUpdated 04/05/2018 
 */
public class Start extends Application {
	
	public static void main(String[] args) {
		// Launches the GUI
		launch(args);
	}

	// Creates a 'stage' (think: window) with the given FXML file in the Parent object, using primaryStage.
	@Override
	public void start(Stage primaryStage) throws Exception{
		Parent root = FXMLLoader.load(getClass().getResource("Landing_GUI.fxml"));
		primaryStage.setTitle("MyMoney Application");
		primaryStage.setScene(new Scene(root, 625, 400));
		primaryStage.setResizable(false); // Prevent fullscreen and drag to resize
		primaryStage.show();
	}
}
