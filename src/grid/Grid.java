// This entire file is part of my masterpiece.
// Mike Ma (ym67)

package grid;

import location.Location;
import cell.Cell;

import java.util.List;

public interface Grid {
	/**
	 * Get the number of rows in the grid
	 * @return height in integer
	 */
	public int getHeight();
	
	/**
	 * Get the number of columns in the grid
	 * @return width in integer
	 */
	public int getWidth();
	
	/**
	 * Get the cell at the specified location
	 * @param loc a location in the grid
	 * @return The cell at that location
	 */
	public Cell getCell(Location loc);
	
	/**
	 * Set the neighbors for each cell
	 */
	public void setNeighbors();
	
	/**
	 * Set the neighbors within a specified radius for each cell
	 */
	public void setNeighbors(int radius);

	/**
	 * Get the locations adjacent to the specified location
	 * @param loc The location
	 * @return A List of adjacent locations
	 */
	public List<Location> getAdjacentLoc(Location loc);
	
	/**
	 * Get the locations with in a radius from the specified location
	 * @param loc The location
	 * @return A List of locations with in the radius
	 */
	public List<Location> getAdjacentLoc(Location loc,int radius);

	/**
	 * Get cells adjacent to the specified cell
	 * @param cell The cell
	 * @return A List of the adjacent cells
	 */
	public List<Cell> getAdjacentCells(Cell cell);
	
	/**
	 * Get cells within a specified radius to the specified cell
	 * @param cell The cell
	 * @return A List of cells
	 */
	public List<Cell> getAdjacentCells(Cell cell,int radius);
	
	/**
	 * Step each cell to it's next state
	 */
	public void step();
	
	/**
	 * Clear the cells out of the grid
	 */
	public void clear();
	
	/**
	 * Remove outlines from all cells
	 */
	public void removeOutlines();
	
	/**
	 * Add outlines to all cells
	 */
	public void addOutlines();
}
