package gui;

import javafx.event.EventHandler;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Shape;
import location.Location;
import state.State;

public abstract class CellGUI {

	private static final double STROKE_WIDTH = 0.5;
	
	protected CSViewer myCSGUI;
	protected double myGridXPos;
	protected double myGridYPos;
	protected Shape myShape;
	protected Circle myCircle;
	protected EventHandler<? super MouseEvent> myClickCallback;
	
	public static CellGUI makeCellGUI(CSViewer CSGUI,Location l){
		switch (CSGUI.myCellType) {
		case "square":
			return new SquareCellGUI(CSGUI,l);
		case "triangle":
			return new TriangleCellGUI(CSGUI, l);
		case "hexagon":
			return new HexagonCellGUI(CSGUI, l);
		default:
			return new SquareCellGUI(CSGUI,l);
		}
	}

	public CellGUI(CSViewer CSGUI) {
		myCSGUI = CSGUI;
		myGridXPos = CSGUI.getGridX();
		myGridYPos = CSGUI.getGridY();
	}
	
	/**
	 * Set the color of the cell to the specified color
	 * @param color The color to set the cell to
	 */
	private void setColor(Color color) {
		myShape.setFill(color);
	}
	
	/**
	 * Set the opacity of the cell to a specified double value
	 * @param double in range 0.0 to 1.0
	 */
	private void setOpacity(double opacity){
		myShape.setOpacity(opacity);
	}
	
	/**
	 * Update the cell's color to the specified state's color
	 * @param state The state
	 */
	public void updateState(State state) {
		setColor(state.getColor());
		setOpacity(state.getOpacity());
	}
	
	/**
	 * Remove the cell from the grid
	 */
	public void remove() {
		removeCircle();
		myCSGUI.removeFromScreen(myShape);
	}
	
	/**
	 * Remove the outlines of the cell
	 */
	public void removeOutlines() {
		myShape.setStrokeWidth(0);
	}
	
	/**
	 * Add outlines to the cell
	 */
	public void addOutlines() {
		myShape.setStrokeWidth(STROKE_WIDTH);
	}
	
	/**
	 * Add a the cell to teh screen
	 */
	protected void addShapeToScreen() {
		myShape.setStrokeWidth(STROKE_WIDTH); //Default on
		myShape.setFill(Color.WHITE);
		myShape.setStroke(Color.GREY);
		myCSGUI.addToScreen(myShape);
	}
	
	/**
	 * Draw a circle in the middle of the cell
	 * @param color The color to draw the circle
	 */
	public abstract void drawCircle(Color color);

	/**
	 * Remove the circle from the cell
	 */
	public void removeCircle() {
		myCSGUI.removeFromScreen(myCircle);
	}
	
	/**
	 * Add a click listener to the cell
	 * @param callback The function to call when clicked
	 */
	public void addClickListener(EventHandler<? super MouseEvent> callback) {
        myShape.setOnMouseClicked(callback);
	}
	
	/**
	 * Add a click listener to the circle in the cell
	 * @param callback The function to call when clicked
	 */
	public void addCircleClickListener(EventHandler<? super MouseEvent> callback) {
        myCircle.setOnMouseClicked(callback);
	}
}
