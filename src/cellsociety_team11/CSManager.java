package cellsociety_team11;

import java.io.File;

import gui.CellSocietyGUI;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.animation.Animation.Status;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Slider;
import javafx.scene.input.MouseEvent;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.util.Duration;
import model.Model;
import model.SaxParser;

public class CSManager {
	
	private static final int XSIZE = 1000;
	private static final int YSIZE = 800;
	private static final String[] BUTTON_NAMES = { "LoadXML", "Start", "Pause", "Reset", "Step", " fps" };
	private static final int FRAMES_PER_SECOND = 10;
	private static final int MILLISECOND_DELAY = 1000 / FRAMES_PER_SECOND;
	
	private CellSocietyGUI myCsGui;
	private SaxParser myParser;
	private Model myModel;
	
	private Timeline myAnimation;
	private Stage myStage;
	
	public CSManager(Stage stage){
		myCsGui = new CellSocietyGUI();
		myParser = new SaxParser(myCsGui);

		Scene scene = myCsGui.init(XSIZE, YSIZE);
		stage.setScene(scene);
		stage.setTitle(myCsGui.getTitle());
		addButtons();
		stage.show();
		
		myCsGui.addGraph();
		myCsGui.createGridArea();
		createAnimation();
	}
	
	private void createAnimation() {
		KeyFrame frame = new KeyFrame(Duration.millis(MILLISECOND_DELAY), e -> myModel.step());
		myAnimation = new Timeline();
		myAnimation.setCycleCount(Timeline.INDEFINITE);
		myAnimation.getKeyFrames().add(frame);
	}

	private void createModel() {
		myModel = myParser.getModel();
	}

	public void loadXML() {
		pause();
		if (myModel != null)
			myModel.clear();
		FileChooser fileChooser = new FileChooser();
		fileChooser.setTitle("Open Resource File");
		File file = fileChooser.showOpenDialog(myStage);
		if (file != null) {
			myParser.initialize(file);
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
			myModel.clear();
		createModel();
	}
	
	private void stepIfNotAnimating() {
		if(myModel != null && (myAnimation.getStatus() == Status.PAUSED || myAnimation.getStatus() == Status.STOPPED))
			myModel.step();
	}
	
	private void stepIfNotNull() {
		if(myModel != null)
			myModel.step();
	}

	public void changeTime(final double fps) {
		int timerInterval = (int) (1000 / fps);
		KeyFrame keyFrame = new KeyFrame(Duration.millis(timerInterval), e -> stepIfNotNull());

		Status prevStat = myAnimation.getStatus();
		myAnimation.stop();
		myAnimation.getKeyFrames().setAll(keyFrame);
		myCsGui.updateSliderLabel((int)fps + BUTTON_NAMES[5]);
		if(prevStat == Status.RUNNING)
			start();
	}

	private void addButtons() {
		EventHandler<? super MouseEvent>[] events = new EventHandler[5];
		events[0] = (e) -> loadXML();
		events[1] = (e) -> start();
		events[2] = (e) -> pause();
		events[3] = (e) -> reset();
		events[4] = (e) -> stepIfNotAnimating();
		int index;
		for(index = 0; index < events.length; index++) {
			Button button = myCsGui.createAndPlaceButton(BUTTON_NAMES[index], index);
			button.setOnMouseClicked(events[index]);
		}
		Slider slider = myCsGui.addSlider(index, FRAMES_PER_SECOND);
		slider.valueProperty().addListener((observable, oldValue, newValue) -> {changeTime(newValue.doubleValue());});
		myCsGui.showSliderLabel(FRAMES_PER_SECOND + BUTTON_NAMES[index], ++index);
	}

}
