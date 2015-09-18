package gui;

import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class CellSocietyGUI {
	private static final String TITLE = "Cell Society";
	private static final int BUTTON_AREA_WIDTH = 200;
	
	private static final double GRID_MARGIN = 50;
	
	private Scene myScene;
	private Group myRoot;

	private int myWindowWidth, myWindowHeight;

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
	
	public void removeFromScreen(Node e) {
		myRoot.getChildren().remove(e);
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
	
	public void createGridArea() {
		Rectangle rect = new Rectangle();
		rect.setX(GRID_MARGIN);
		rect.setY(GRID_MARGIN);
		rect.setWidth(getGridWidth());
		rect.setHeight(getGridHeight());
		rect.setFill(Color.WHITE);
		rect.setStroke(Color.BLACK);
		rect.setStrokeWidth(2);
		addToScreen(rect);
	}
	
	public double getGridWidth() {
		return myWindowWidth - GRID_MARGIN * 2 - BUTTON_AREA_WIDTH;
	}
	
	public double getGridHeight() {
		return myWindowHeight - GRID_MARGIN * 2;
	}
	
	public double getGridX() {
		return GRID_MARGIN;
	}
	
	public double getGridY() {
		return GRID_MARGIN;
	}
}
