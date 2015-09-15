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

		return myScene;
	}

	public String getTitle() {
		return TITLE;
	}
	
	public void addToScreen(Node e) {
		myRoot.getChildren().add(e);
	}
	
	public Button createAndPlaceButton(String text, double yCoord, double buttonHeight) {
		int buttonArea = myWindowWidth - BUTTON_AREA_WIDTH;
		Button button = new Button(text);
		addToScreen(button);
		button.applyCss();
		double width = button.prefWidth(-1);
		double height = button.prefHeight(-1);
		button.setLayoutX(buttonArea + (BUTTON_AREA_WIDTH - width) / 2);
		button.setLayoutY(yCoord + (buttonHeight - height) / 2);
		return button;
	}
}
