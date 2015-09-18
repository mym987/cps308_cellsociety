package gui;

import cellsociety_team11.Location;
import cellsociety_team11.State;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class SquareCellGUI {

	private double myCellWidth;
	private double myCellHeight;
	private double myGridXPos;
	private double myGridYPos;
	private Rectangle myRect;
	CellSocietyGUI myCSGUI;

	public SquareCellGUI(CellSocietyGUI CSGUI, Location loc) {
		myCSGUI = CSGUI;
		myCellWidth = CSGUI.getGridWidth() / loc.getNumCols();
		myCellHeight = CSGUI.getGridHeight() / loc.getWidth();
		myGridXPos = CSGUI.getGridX();
		myGridYPos = CSGUI.getGridY();
		createRectangle();
		setPosition(loc);
		myCSGUI.addToScreen(myRect);
	}
	
	private void createRectangle() {
		myRect = new Rectangle();
		myRect.setFill(Color.WHITE);		// These should be methods in CellGUI
		myRect.setStroke(Color.BLACK);
		myRect.setWidth(myCellWidth);
		myRect.setHeight(myCellHeight);
		myRect.setStrokeWidth(2);
	}
	
	private void setPosition(Location loc) {
		double xPos = myGridXPos + loc.getX() * myCellWidth;
		double yPos = myGridYPos + loc.getY() * myCellHeight;
		myRect.setX(xPos);
		myRect.setY(yPos);
	}
	
	private void setColor(Color color) {
		myRect.setFill(color);
	}
	
	public void updateState(State state) {
		setColor(state.getColor());
	}
	
	public void remove() {
		myCSGUI.removeFromScreen(myRect);
	}
}
