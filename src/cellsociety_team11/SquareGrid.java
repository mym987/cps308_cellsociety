package cellsociety_team11;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class SquareGrid extends Grid {
	public static final int EMPTY_STATE = 0;
	
	SquareGrid(int width, int height, Map<Location, Cell> cells) {
		super(width, height, cells);
	}

	public void setNeighbors() {
		myCells.forEach((loc,cell)->{
			cell.setNeighborCells(getAdjacentCells(cell));
		});
	}
	
	public void setEmpty() {
		myCells.forEach((loc,cell)->{
			cell.setEmptyCells(getEmptyCells());
		});
	}
	
	@Override
	public List<Location> getAdjacentLoc(Location loc) {
		int[] x = {-1,-1,-1, 0, 0, 1, 1, 1};
		int[] y = {-1, 0, 1,-1, 1,-1, 0, 1};
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
	
	public List<Cell> getEmptyCells() {
		List<Cell> emptyCells = new ArrayList<>();
		myCells.forEach((loc,cell) -> {
			if(cell.isInState(EMPTY_STATE)) {
				emptyCells.add(cell);
			}
		});
		return emptyCells;
	}
	
	@Override
	public String toString(){
		StringBuilder sb = new StringBuilder();
		for(int x=0;x<getWidth();x++){
			for(int y=0;y<getHeight();y++){
				Cell cell = getCell(new Location(x,y,getWidth(),getHeight()));
				sb.append(cell.getState().getStateInt());
			}
			sb.append('\n');
		}
		return sb.toString();
	}
}
