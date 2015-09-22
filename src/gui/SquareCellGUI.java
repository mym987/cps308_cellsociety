package gui;

import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import location.Location;

public class SquareCellGUI extends CellGUI {

	private double myCellWidth;
	private double myCellHeight;

	public SquareCellGUI(CellSocietyGUI CSGUI, Location loc) {
		super(CSGUI);
		myCellWidth = CSGUI.getGridWidth() / loc.getNumCols();
		myCellHeight = CSGUI.getGridHeight() / loc.getWidth();
		createRectangle(loc);
		myCSGUI.addToScreen(myShape);
	}
	
	private void createRectangle(Location loc) {
		Rectangle rect = new Rectangle();
		rect.setFill(Color.WHITE);		// These should be methods in CellGUI
		rect.setStroke(Color.GREY);
		rect.setWidth(myCellWidth);
		rect.setHeight(myCellHeight);
		rect.setStrokeWidth(0.2);
		double xPos = myGridXPos + loc.getX() * myCellWidth;
		double yPos = myGridYPos + loc.getY() * myCellHeight;
		rect.setX(xPos);
		rect.setY(yPos);
		myShape = rect;
	}
}
