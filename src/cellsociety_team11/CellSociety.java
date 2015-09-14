package cellsociety_team11;

import gui.CellSocietyGUI;
import javafx.application.Application;
import javafx.stage.Stage;


public class CellSociety extends Application {
 
    @Override public void start(Stage stage) throws Exception {
    	new CellSocietyGUI(stage);
    }
 
    public static void main(String[] args) {
        launch(args);
    }
}