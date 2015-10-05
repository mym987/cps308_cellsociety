package grid;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import location.Location;
import cell.Cell;

public abstract class Grid{
	
	protected int myWidth;
	protected int myHeight;
	protected Map<Location, Cell> myCells;
	
	
	/**
	 * 
	 * @param width
	 * @param height
	 * @param cells
	 */
	public Grid(int width, int height, Set<Cell> cells){
		myWidth = width;
		myHeight = height;
		myCells = new HashMap<>();
		cells.forEach(cell->{myCells.put(cell.getLocation(), cell);});
	}
	
	/**
	 * Get the number of rows in the grid
	 * @return
	 */
	public int getHeight() {
		return myHeight;
	}

	/**
	 * Get the number of columns in the grid
	 * @return
	 */
	public int getWidth() {
		return myWidth;
	}
	
	/**
	 * Get the cell at the specified location
	 * @param loc a location in the grid
	 * @return The cell at that location
	 */
	public Cell getCell(Location loc){
		return myCells.get(loc);
	}
	
	/**
	 * Set the neighbors for each cell
	 */
	public void setNeighbors() {
		myCells.forEach((loc,cell)->{
			cell.setNeighborCells(getAdjacentCells(cell));
		});
	}
	
	public void setNeighbors(int radius) {
		myCells.forEach((loc,cell)->{
			cell.setNeighborCells(getAdjacentCells(cell,radius));
		});
	}

	/**
	 * Get the locations adjacent to the specified location
	 * @param loc The location
	 * @return A List of adjacent locations
	 */
	public abstract List<Location> getAdjacentLoc(Location loc);
	
	/**
	 * Get the locations with in a radius from the specified location
	 * @param loc The location
	 * @return A List of locations with in the radius
	 */
	public abstract List<Location> getAdjacentLoc(Location loc,int radius);

	
	/**
	 * Get cells adjacent to the specified cell
	 * @param cell The cell
	 * @return A List of the adjacent cells
	 */
	public List<Cell> getAdjacentCells(Cell cell) {
		List<Cell> neighbors = new ArrayList<>();
		getAdjacentLoc(cell.getLocation()).forEach(loc->{
			neighbors.add(getCell(loc));
		});
		return neighbors;
	}
	
	public List<Cell> getAdjacentCells(Cell cell,int radius) {
		List<Cell> neighbors = new ArrayList<>();
		getAdjacentLoc(cell.getLocation(),radius).forEach(loc->{
			neighbors.add(getCell(loc));
		});
		return neighbors;
	}
	
	/**
	 * Step each cell to it's next state
	 */
	public void step() {
		determineNextStates();
		goToNextStates();
	}
	
	/**
	 * Determine the next state of each cell in the grid
	 */
	private void determineNextStates() {
		for (Cell c : myCells.values()) {
			c.determineNextState();
		}
	}
	
	/**
	 * Go to the next state in each cell
	 */
	private void goToNextStates() {
		for (Cell c : myCells.values()) {
			c.goToNextState();
		}
	}
	
	/**
	 * Clear the cells out of the grid
	 */
	public final void clear() {
		for (Cell c : myCells.values()) {
			c.remove();
		}
	}
	
	/**
	 * Remove outlines from all cells
	 */
	public final void removeOutlines() {
		for (Cell c : myCells.values()) {
			c.removeOutlines();
		}
	}
	
	/**
	 * Add outlines to all cells
	 */
	public final void addOutlines() {
		for (Cell c : myCells.values()) {
			c.addOutlines();
		}
	}

	@Override
	public String toString(){
		StringBuilder sb = new StringBuilder();
		for(int x=0;x<getWidth();x++){
			for(int y=0;y<getHeight();y++){
				Cell cell = getCell(new Location(x,y,getWidth(),getHeight()));
				sb.append(cell.getState());
			}
			sb.append('\n');
		}
		return sb.toString();
	}
}
