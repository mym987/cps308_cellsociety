package grid;

import java.util.Map;

import cell.Cell;
import location.Location;

public class TriangleGrid extends SquareGrid {

	public TriangleGrid(int width, int height, Map<Location, Cell> cells) {
		super(width, height, cells);
	}
}
