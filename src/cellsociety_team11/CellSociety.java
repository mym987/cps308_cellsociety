package cellsociety_team11;

import gui.CellSocietyGUI;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class CellSociety extends Application {
    public static final int XSIZE = 1000;
    public static final int YSIZE = 1000;
    public static final int FRAMES_PER_SECOND = 60;
    private static final int MILLISECOND_DELAY = 1000 / FRAMES_PER_SECOND;
    private static final double SECOND_DELAY = 1.0 / FRAMES_PER_SECOND;

	@Override public void start(Stage stage) {
    	CellSocietyGUI myCSGUI = new CellSocietyGUI(stage);
    	
        Scene scene = myCSGUI.init(XSIZE, YSIZE);
        stage.setScene(scene);
        stage.setTitle(myCSGUI.getTitle());
        stage.show();
    }
	
	public void loadXML() {}
	public void start() {}
	public void pause() {}
	public void reset() {}
	

    public static void main(String[] args) {
        launch(args);
    }
}