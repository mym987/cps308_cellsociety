package gui;

import javafx.scene.shape.Polygon;
import location.Location;

public class TriangleCellGUI extends CellGUI {

	private double myCellWidth;
	private double myCellHeight;
	
	public TriangleCellGUI(CellSocietyGUI CSGUI, Location loc) {
		super(CSGUI);
		myCellWidth = CSGUI.getGridWidth() / (loc.getNumCols() + 1) * 2;
		myCellHeight = CSGUI.getGridHeight() / loc.getWidth();
		myShape = createTriangle(loc);
		addShapeToScreen();
	}
	
	private Polygon createTriangle(Location loc) {
		Polygon tri = new Polygon();
		double point1X = myGridXPos + ((loc.getX() + loc.getY() % 2) / 2) * myCellWidth + ((loc.getY() + 1) %2) * myCellWidth / 2;
		double point1Y = myGridYPos + loc.getY() * myCellHeight;
		double point2X = point1X + myCellWidth / 2;
		double point2Y = point1Y + myCellHeight;
		
		boolean pointingUp = (loc.getX() + loc.getY()) % 2 == 0;

		double point3X = pointingUp ? (point2X - myCellWidth): (point1X + myCellWidth);
		double point3Y = pointingUp ? point2Y: point1Y;
		
		tri.getPoints().addAll(new Double[]{
			    point1X, point1Y,
			    point2X, point2Y,
			    point3X, point3Y });

//		Label sliderLabel = new Label(loc.getX() + ", " + loc.getY());
//		double lblPosX = pointingUp ? (point1X) : (point2X);
//		double lblPosY = point1Y + myCellHeight / 2;
//		sliderLabel.setLayoutX(lblPosX);
//		sliderLabel.setLayoutY(lblPosY);
//		myCSGUI.addToScreen(sliderLabel);
		
		return tri;
	}
}
