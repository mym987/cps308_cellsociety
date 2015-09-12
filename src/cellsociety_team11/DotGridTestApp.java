package cellsociety_team11;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.stage.Stage;


public class DotGridTestApp extends Application {
 
    @Override public void start(Stage stage) throws Exception {
        stage.setScene(new Scene(new Grid(), Color.WHITE));
        stage.show();
    }
 
    public static void main(String[] args) {
        launch(args);
    }
}