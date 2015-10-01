package grid;

import java.util.Set;

import cell.Cell;
import gui.CellSocietyGUI;

public class GridFactory {
	public static Grid makeGrid(int width,int height,Set<Cell> cells,CellSocietyGUI csGui){
		switch (csGui.getGridType()) {
		case "square":
			return new SquareGrid(width,height,cells);
		case "squareCardinal":
			return new SquareCardinalGrid(width,height,cells);
		default:
			return new SquareGrid(width,height,cells);
		}
	}
}
