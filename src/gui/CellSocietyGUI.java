package gui;

import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class CellSocietyGUI {
	private static final String TITLE = "Cell Society";
	private static final int BUTTON_AREA_WIDTH = 200;
	private static final double[] BUTTON_Y_POSITIONS = {100, 200, 300, 400};
	private static final String[] BUTTON_NAMES = {"LoadXML", "Start", "Pause", "Reset"};
	private static final double BUTTON_HEIGHT = 50;
	
	private Scene myScene;
	private Group myRoot;

	private int myWindowWidth, myWindowHeight;

	public CellSocietyGUI(Stage stage) {
	}
	
	public Scene init(int width, int height) {
		myWindowWidth = width;
		myWindowHeight = height;

		myRoot = new Group();
		myScene = new Scene(myRoot, width, height, Color.WHITE);
		
		addButtons();

		return myScene;
	}
	
	public void addToScreen(Node e) {
		myRoot.getChildren().add(e);
	}
	
	private void addButtons() {
		double topButtonX = (myWindowHeight - BUTTON_HEIGHT * BUTTON_NAMES.length) / 2;
		for(int i = 0; i < BUTTON_NAMES.length; i++) {
			createAndPlaceButton(BUTTON_NAMES[i], topButtonX + BUTTON_HEIGHT*i);
		}
	}
	
	private void createAndPlaceButton(String text, double yCoord) {
		int buttonArea = myWindowWidth - BUTTON_AREA_WIDTH;
		Button button = new Button(text);
		addToScreen(button);
		button.applyCss();
		double width = button.prefWidth(-1);
		double height = button.prefHeight(-1);
		button.setLayoutX(buttonArea + (BUTTON_AREA_WIDTH - width) / 2);
		button.setLayoutY(yCoord + (BUTTON_HEIGHT - height) / 2);
	}
}
