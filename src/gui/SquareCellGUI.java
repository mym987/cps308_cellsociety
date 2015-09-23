package gui;

import javafx.scene.shape.Rectangle;
import location.Location;

public class SquareCellGUI extends CellGUI {

	private double myCellWidth;
	private double myCellHeight;

	public SquareCellGUI(CellSocietyGUI CSGUI, Location loc) {
		super(CSGUI);
		myCellWidth = CSGUI.getGridWidth() / loc.getNumCols();
		myCellHeight = CSGUI.getGridHeight() / loc.getWidth();
		myShape = createRectangle(loc);
		addShapeToScreen();
	}
	
	private Rectangle createRectangle(Location loc) {
		Rectangle rect = new Rectangle();
		rect.setWidth(myCellWidth);
		rect.setHeight(myCellHeight);
		double xPos = myGridXPos + loc.getX() * myCellWidth;
		double yPos = myGridYPos + loc.getY() * myCellHeight;
		rect.setX(xPos);
		rect.setY(yPos);
		return rect;
	}
}
