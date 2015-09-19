package cellsociety_team11;

import java.io.File;

import gui.CellSocietyGUI;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.animation.Animation.Status;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Slider;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.util.Duration;

public class CellSociety extends Application {
	private static final int XSIZE = 1000;
	private static final int YSIZE = 800;
	private static final String[] BUTTON_NAMES = { "LoadXML", "Start", "Pause", "Reset", "Step", " fps" };
	private static final double BUTTON_HEIGHT = 40;
	private static final int FRAMES_PER_SECOND = 10;
	private static final int MILLISECOND_DELAY = 1000 / FRAMES_PER_SECOND;
	
	private CellSocietyGUI myCSGUI;
	private Model myModel;
	private Stage myStage;
	private Timeline myAnimation;
	private String myFileName;

	@Override
	public void start(Stage stage) {
		myStage = stage;
		myCSGUI = new CellSocietyGUI();

		Scene scene = myCSGUI.init(XSIZE, YSIZE);
		stage.setScene(scene);
		stage.setTitle(myCSGUI.getTitle());
		addButtons();
		stage.show();

		myCSGUI.createGridArea();

		KeyFrame frame = new KeyFrame(Duration.millis(MILLISECOND_DELAY), e -> myModel.step());
		myAnimation = new Timeline();
		myAnimation.setCycleCount(Timeline.INDEFINITE);
		myAnimation.getKeyFrames().add(frame);
	}

	private void createModel() {
		if (myFileName != null)
			myModel = SaxParser.getModel(myFileName, myCSGUI);
	}

	public void loadXML() {
		reset();
		FileChooser fileChooser = new FileChooser();
		fileChooser.setTitle("Open Resource File");
		File file = fileChooser.showOpenDialog(myStage);
		if (file != null) {
			myFileName = file.getAbsolutePath();
			createModel();
		}
	}

	public void start() {
		if (myModel != null)
			myAnimation.play();
	}

	public void pause() {
		myAnimation.pause();
	}

	public void reset() {
		pause();
		if (myModel != null)
			myModel.removeCells();
		createModel();
	}
	
	private void stepIfNotAnimating() {
		if(myModel != null && myAnimation.getStatus() == Status.PAUSED)
			myModel.step();
	}
	
	private void stepIfNotNull() {
		if(myModel != null)
			myModel.step();
	}

	public void changeTime(final double fps) {
		int timerInterval = (int) (1000 / fps);
		KeyFrame keyFrame = new KeyFrame(Duration.millis(timerInterval), e -> stepIfNotNull());

		myAnimation.stop();
		myAnimation.getKeyFrames().setAll(keyFrame);
		myCSGUI.updateSliderLabel((int)fps + BUTTON_NAMES[5]);
		start();
	}

	private void addButtons() {
		int index = 0;
		double topButtonY = (YSIZE - BUTTON_HEIGHT * (BUTTON_NAMES.length + 1)) / 2;
		Button button = myCSGUI.createAndPlaceButton(BUTTON_NAMES[index], topButtonY + BUTTON_HEIGHT * index, BUTTON_HEIGHT);
		button.setOnMouseClicked((e) -> loadXML());
		++index;
		button = myCSGUI.createAndPlaceButton(BUTTON_NAMES[index], topButtonY + BUTTON_HEIGHT * index, BUTTON_HEIGHT);
		button.setOnMouseClicked((e) -> start());
		++index;
		button = myCSGUI.createAndPlaceButton(BUTTON_NAMES[index], topButtonY + BUTTON_HEIGHT * index, BUTTON_HEIGHT);
		button.setOnMouseClicked((e) -> pause());
		++index;
		button = myCSGUI.createAndPlaceButton(BUTTON_NAMES[index], topButtonY + BUTTON_HEIGHT * index, BUTTON_HEIGHT);
		button.setOnMouseClicked((e) -> reset());
		++index;
		button = myCSGUI.createAndPlaceButton(BUTTON_NAMES[index], topButtonY + BUTTON_HEIGHT * index, BUTTON_HEIGHT);
		button.setOnMouseClicked((e) -> stepIfNotAnimating());
		++index;
		Slider slider = myCSGUI.addSlider(topButtonY + BUTTON_HEIGHT * index, FRAMES_PER_SECOND);
		slider.valueProperty().addListener((observable, oldValue, newValue) -> {changeTime(newValue.doubleValue());});
		myCSGUI.showSliderLabel(FRAMES_PER_SECOND + BUTTON_NAMES[index], topButtonY + BUTTON_HEIGHT  * (index + 1));
	}

	public static void main(String[] args) {
		launch(args);
	}
}