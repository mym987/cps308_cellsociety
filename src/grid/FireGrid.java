package grid;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import location.Location;
import cell.Cell;

public class FireGrid extends SquareGrid{

	public FireGrid(int width, int height, Map<Location, Cell> cells) {
		super(width, height, cells);
	}
	
	public void setNeighbors() {
		myCells.forEach((loc,cell)->{
			cell.setNeighborCells(getAdjacentCells(cell));
		});
	}
	
	@Override
	public List<Location> getAdjacentLoc(Location loc) {
		int[] x = {-1, 0, 0, 1};
		int[] y = {0, -1, 1, 0};
		List<Location> list = new ArrayList<>(x.length);
		for(int i=0;i<x.length;i++){
			Location neighbor = loc.getLocation(loc.getX()+x[i], loc.getY()+y[i]);
			if(neighbor!=null){
				list.add(neighbor);
			}
		}
		return list;
	}

	@Override
	public List<Cell> getAdjacentCells(Cell cell) {
		List<Cell> neighbors = new ArrayList<>(8);
		getAdjacentLoc(cell.getLocation()).forEach(loc->{
			neighbors.add(getCell(loc));
		});
		return neighbors;
	}
	
	@Override
	public String toString(){
		StringBuilder sb = new StringBuilder();
		for(int r=0;r<getHeight();r++){
			for(int c=0;c<getWidth();c++){
				//Cell cell = getCell(new Location(c,r,))
			}
		}
		return "";
	}

}
