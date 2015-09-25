package cellsociety_team11;

import javafx.application.Application;
import javafx.stage.Stage;

public class CellSociety extends Application {
	
	@Override
	public void start(Stage stage) {
		CellSsocietyGUI
		CSManager manager = new CSManager(stage);
	}

	public static void main(String[] args) {
		launch(args);
	}
}