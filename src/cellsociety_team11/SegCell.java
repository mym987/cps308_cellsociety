package cellsociety_team11;

import gui.CellSocietyGUI;
import javafx.scene.paint.Color;

public class SegCell extends Cell {
	public static final double CELL_SIZE = 70;
	
	private Integer[] myStateInts = {0,1,2}; 

	SegCell(State s, Location l, CellSocietyGUI CSGUI) {
		super(s, l, CSGUI);
		
		double x1 = myLoc.getX()*CELL_SIZE;
		double y1 = myLoc.getY()*CELL_SIZE;
		double x2 = myLoc.getX()*CELL_SIZE + CELL_SIZE;
		double y2 = myLoc.getY()*CELL_SIZE + CELL_SIZE;

	}

	@Override
	public void determineNextState() {
		// TODO Auto-generated method stub
	}

}
