package gui;

import cellsociety_team11.Location;
import cellsociety_team11.State;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class SquareCellGUI {

	private double myCellWidth;
	private double myCellHeight;
	private Rectangle myRect;
	CellSocietyGUI myGUI;

	public SquareCellGUI(CellSocietyGUI CSGUI, Location loc) {
		myGUI = CSGUI;
		myCellWidth = CSGUI.getWindowWidth() / loc.getNumCols();
		myCellHeight = CSGUI.getWindowHeight() / loc.getNumRows();
		createRectangle();
		setPosition(loc);
		CSGUI.addToScreen(myRect);
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
		double xPos = loc.getX()*myCellWidth;
		double yPos = loc.getY()*myCellHeight;
		myRect.setX(xPos);
		myRect.setX(yPos);
	}
	
	private void setColor(Color color) {
		myRect.setFill(color);
	}
	
	public void updateState(State state) {
		setColor(state.getColor());
	}
}
