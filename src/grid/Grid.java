package grid;

import java.util.List;
import java.util.Map;

import location.Location;
import cell.Cell;

public abstract class Grid {
	
	protected int myWidth;		// This should be in squareGrid.  Grid doesn't necessarily have rows and columns
	protected int myHeight;
	protected Map<Location, Cell> myCells;
	
	/**
	 * 
	 * @param width
	 * @param height
	 * @param cells
	 */
	public Grid(int width, int height, Map<Location, Cell> cells){
		myWidth = width;
		myHeight = height;
		myCells = cells;
	}
	
	public int getHeight() {
		return myHeight;
	}

	public int getWidth() {
		return myWidth;
	}
	
	public Cell getCell(Location loc){
		return myCells.get(loc);
	}

	public abstract List<Location> getAdjacentLoc(Location loc);
	
	public abstract List<Cell> getAdjacentCells(Cell cell);
	
	public void step() {
		determineNextStates();
		goToNextStates();
	}
	
	private void determineNextStates() {
		for (Cell c : myCells.values()) {
			c.determineNextState();
		}
	}
	
	private void goToNextStates() {
		for (Cell c : myCells.values()) {
			c.goToNextState();
		}
	}
	
	public void removeCells() {
		for (Cell c : myCells.values()) {
			c.remove();
		}
	}

}
