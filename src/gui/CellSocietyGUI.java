package gui;

import java.util.HashMap;
import java.util.ResourceBundle;

import javax.imageio.ImageIO;

import BrowserView.ShowPage;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.chart.XYChart.Data;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class CellSocietyGUI {
	private static final String TITLE = "Cell Society";
	private static final int BUTTON_AREA_WIDTH = 400;
	private static final int GRAPH_HEIGHT = 300;
	private static final double BUTTON_HEIGHT = 40;
	
	private static final double GRID_MARGIN = 50;

	private static final double MIN_FPS = 1;
	private static final double MAX_FPS = 60;
	private static final double INCREMENT = 0.5;
	
	private Scene myScene;
	private Group myRoot;
	private ResourceBundle myResources;
	
	private HashMap<Integer, XYChart.Series<Number, Number>> mySeriesMap;
	private LineChart<Number,Number> myLineChart;
	
	private Label mySliderLabel;

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
	
	public Button createAndPlaceButton(String property, double yIndex, EventHandler<ActionEvent> handler) {
		int buttonArea = myWindowWidth - BUTTON_AREA_WIDTH;
		String label = myResources.getString(property);
		Button button = new Button(label);
		addToScreen(button);
		button.applyCss();
		double width = button.prefWidth(-1);
		//double height = button.prefHeight(-1);
		button.setLayoutX(buttonArea + (BUTTON_AREA_WIDTH - GRID_MARGIN - width) / 2);
		button.setLayoutY(GRID_MARGIN + BUTTON_HEIGHT * yIndex);
		button.setOnAction(handler);
		return button;
	}
	
	public Slider addSlider(double yIndex, double fps) {
		 Slider slider = new Slider(MIN_FPS, MAX_FPS, fps);
		 slider.setMajorTickUnit(1);
		 slider.setBlockIncrement(INCREMENT);
		 slider.setLayoutX(myWindowWidth - BUTTON_AREA_WIDTH);
		 slider.setLayoutY(GRID_MARGIN + BUTTON_HEIGHT * yIndex);
		 slider.setPrefWidth(BUTTON_AREA_WIDTH - GRID_MARGIN);
		 addToScreen(slider);
		 return slider;
	}
	
	public void showSliderLabel(String text, double yIndex) {
		mySliderLabel = new Label();
		myRoot.getChildren().add(mySliderLabel);
		mySliderLabel.applyCss();
		mySliderLabel.setLayoutY(GRID_MARGIN + BUTTON_HEIGHT * yIndex - mySliderLabel.prefHeight(-1));
		updateSliderLabel(text);
	}
	
	public void updateSliderLabel(String text) {
		mySliderLabel.setText(text);
		mySliderLabel.applyCss();
		double xPos = myWindowWidth - BUTTON_AREA_WIDTH + (BUTTON_AREA_WIDTH - GRID_MARGIN - mySliderLabel.prefWidth(-1)) / 2;
		mySliderLabel.setLayoutX(xPos);
	}
	
	public void addGraph() {
        //defining the axes
        final NumberAxis xAxis = new NumberAxis();
        final NumberAxis yAxis = new NumberAxis();
        xAxis.setLabel("Frame Number");
        //creating the chart
        myLineChart = 
                new LineChart<Number,Number>(xAxis,yAxis);
        mySeriesMap = new HashMap<Integer, XYChart.Series<Number, Number>>();
                
        myLineChart.setTitle("Number of Cells in Each State");
        myLineChart.setLayoutX(myWindowWidth - BUTTON_AREA_WIDTH);
        myLineChart.setLayoutY(myWindowHeight - GRAPH_HEIGHT - GRID_MARGIN);
        myLineChart.setPrefWidth(BUTTON_AREA_WIDTH - GRID_MARGIN);
        myLineChart.setPrefHeight(GRAPH_HEIGHT);
        myLineChart.setCreateSymbols(false);
        addToScreen(myLineChart);
	}
	
	public void addSeries(int index, String name) {
        XYChart.Series<Number, Number> series = new XYChart.Series<Number, Number>();
        series.setName(name);
        
        myLineChart.getData().add(series);
        mySeriesMap.put(index, series);
	}
	
	public void resetGraph() {
        myLineChart.getData().clear();
	}
	
	public void addDataPoint(Number xVal, Number yVal, int seriesNum) {
		ObservableList<Data<Number, Number>> list = mySeriesMap.get(seriesNum).getData();
        list.add(new XYChart.Data<Number, Number>(xVal, yVal));
	}
	
	public void createGridArea() {
		Rectangle rect = new Rectangle();
		rect.setX(GRID_MARGIN);
		rect.setY(GRID_MARGIN);
		rect.setWidth(getGridWidth());
		rect.setHeight(getGridHeight());
		rect.setFill(Color.WHITE);
		rect.setStroke(Color.BLACK);
		rect.setStrokeWidth(1);
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
