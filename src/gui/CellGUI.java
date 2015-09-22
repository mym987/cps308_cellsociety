package gui;

import javafx.scene.paint.Color;
import javafx.scene.shape.Shape;
import state.State;

public abstract class CellGUI {

	CellSocietyGUI myCSGUI;
	protected double myGridXPos;
	protected double myGridYPos;
	protected Shape myShape;

	public CellGUI(CellSocietyGUI CSGUI) {
		myCSGUI = CSGUI;
		myGridXPos = CSGUI.getGridX();
		myGridYPos = CSGUI.getGridY();
	}
	
	private void setColor(Color color) {
		myShape.setFill(color);
		myShape.setFill(Color.WHITE);
		myShape.setStroke(Color.GREY);
	}
	
	public void updateState(State state) {
		setColor(state.getColor());
	}
	
	public void remove() {
		myCSGUI.removeFromScreen(myShape);
	}
	
	protected void addShapeToScreen() {
		myShape.setStrokeWidth(0.2);
		myShape.setFill(Color.WHITE);
		myShape.setStroke(Color.GREY);
		myCSGUI.addToScreen(myShape);
	}
}
