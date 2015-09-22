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
	}
	
	public void updateState(State state) {
		setColor(state.getColor());
	}
	
	public void remove() {
		myCSGUI.removeFromScreen(myShape);
	}
}
