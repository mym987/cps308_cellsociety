package gui;

import javafx.scene.shape.Polygon;
import location.Location;

public class TriangleCellGUI extends CellGUI {

	private double myCellWidth;
	private double myCellHeight;
	private boolean myPointUp;
	
	public TriangleCellGUI(CellSocietyGUI CSGUI, Location loc, boolean pointUp) {
		super(CSGUI);
		myPointUp = pointUp;
		myCellWidth = CSGUI.getGridWidth() / loc.getNumCols() * 2;
		myCellHeight = CSGUI.getGridHeight() / loc.getWidth();
		myShape = createTriangle(loc);
		addShapeToScreen();
	}
	
	private Polygon createTriangle(Location loc) {
		Polygon tri = new Polygon();
		double point1X = myGridXPos + (loc.getX() / 2) * myCellWidth + ((loc.getY() + 1) %2) * myCellWidth / 2;
		double point1Y = myGridYPos + loc.getY() * myCellHeight;
		double point2X = point1X + myCellWidth / 2;
		double point2Y = point1Y + myCellHeight;
		
		boolean pointingUp = (loc.getX() + loc.getY()) % 2 == 0;

		double point3X = pointingUp ? point2X - myCellWidth: point1X + myCellWidth;
		double point3Y = pointingUp ? point2Y: point1Y;
		
		tri.getPoints().addAll(new Double[]{
			    point1X, point1Y,
			    point2X, point2Y,
			    point3X, point3Y });
		return tri;
	}

}
