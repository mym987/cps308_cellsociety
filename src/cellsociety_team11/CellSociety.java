package cellsociety_team11;

import gui.CellSocietyGUI;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

public class CellSociety extends Application {
	private static final int XSIZE = 1000;
	private static final int YSIZE = 1000;
	private static final String[] BUTTON_NAMES = { "LoadXML", "Start", "Pause", "Reset" };
	private static final double BUTTON_HEIGHT = 40;
	public static final int FRAMES_PER_SECOND = 60;
	private static final int MILLISECOND_DELAY = 1000 / FRAMES_PER_SECOND;
	private static final double SECOND_DELAY = 1.0 / FRAMES_PER_SECOND;

	private CellSocietyGUI myCSGUI;

	@Override
	public void start(Stage stage) {
		myCSGUI = new CellSocietyGUI(stage);

		Scene scene = myCSGUI.init(XSIZE, YSIZE);
		stage.setScene(scene);
		stage.setTitle(myCSGUI.getTitle());
		addButtons();
		stage.show();
	}

	public void loadXML() {
		System.out.println("Load XML");
	}

	public void start() {
		System.out.println("Start");
	}

	public void pause() {
		System.out.println("Pause");
	}

	public void reset() {
		System.out.println("Reset");
	}

	private void addButtons() {
		double topButtonX = (YSIZE - BUTTON_HEIGHT * BUTTON_NAMES.length) / 2;
		Button button = myCSGUI.createAndPlaceButton(BUTTON_NAMES[0], topButtonX, BUTTON_HEIGHT);
		button.setOnMouseClicked((e) -> loadXML());
		button = myCSGUI.createAndPlaceButton(BUTTON_NAMES[1], topButtonX + BUTTON_HEIGHT, BUTTON_HEIGHT);
		button.setOnMouseClicked((e) -> start());
		button = myCSGUI.createAndPlaceButton(BUTTON_NAMES[2], topButtonX + BUTTON_HEIGHT * 2, BUTTON_HEIGHT);
		button.setOnMouseClicked((e) -> pause());
		button = myCSGUI.createAndPlaceButton(BUTTON_NAMES[3], topButtonX + BUTTON_HEIGHT * 3, BUTTON_HEIGHT);
		button.setOnMouseClicked((e) -> reset());
	}

	public static void main(String[] args) {
		launch(args);
	}
}