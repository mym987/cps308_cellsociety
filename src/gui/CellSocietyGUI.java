package gui;

import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;

public class CellSocietyGUI {
	private static final String TITLE = "Cell Society";
	private static final int BUTTON_AREA_WIDTH = 200;
	
	private static final double GRID_MARGIN = 50;

	private static final double MIN_FPS = 1;
	private static final double MAX_FPS = 60;
	private static final double INCREMENT = 0.5;
	
	private Scene myScene;
	private Group myRoot;
	
	Label mySliderLabel;

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
	
	public Slider addSlider(double yPos, double fps) {
		 Slider slider = new Slider(MIN_FPS, MAX_FPS, fps);
		 slider.setMajorTickUnit(1);
		 slider.setBlockIncrement(INCREMENT);
		 slider.setLayoutX(myWindowWidth - BUTTON_AREA_WIDTH);
		 slider.setLayoutY(yPos);
		 slider.setPrefWidth(BUTTON_AREA_WIDTH);
		 addToScreen(slider);
		 return slider;
	}
	
	public void showSliderLabel(String text, double yPos) {
		mySliderLabel = new Label(text);
		myRoot.getChildren().add(mySliderLabel);
		//lbl.setFont(new Font(myFont, fontSize));
		mySliderLabel.applyCss();
		double xPos = myWindowWidth - BUTTON_AREA_WIDTH + (BUTTON_AREA_WIDTH - mySliderLabel.prefWidth(-1)) / 2;
		mySliderLabel.setLayoutX(xPos);
		mySliderLabel.setLayoutY(yPos);
	}
	
	public void updateSliderLabel(String text) {
		mySliderLabel.setText(text);
		mySliderLabel.applyCss();
		double xPos = myWindowWidth - BUTTON_AREA_WIDTH + (BUTTON_AREA_WIDTH - mySliderLabel.prefWidth(-1)) / 2;
		mySliderLabel.setLayoutX(xPos);
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
