/**
 * @author Mike Ma
 */
package grid;

import java.util.Set;

import cell.Cell;
import gui.CSViewer;

public class GridFactory {
	public static Grid makeGrid(int width,int height,Set<Cell> cells,CSViewer csGui){
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
