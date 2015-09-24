package grid;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import location.Location;
import cell.Cell;

public class SugarGrid extends SquareGrid{

	public SugarGrid(int width, int height, Set<Cell> cells) {
		super(width, height, cells);
	}
	
	public void setNeighbors(int vision) {
		myCells.forEach((loc,cell)->{
			cell.setNeighborCells(getAdjacentCellsSugar(cell, vision));
		});
	}
	
	
	public List<Location> getAdjacentLocSugar(Location loc, int vision) {
		ArrayList<Integer> x = new ArrayList<Integer>();
		ArrayList<Integer> y = new ArrayList<Integer>();
		
		for (int i = -vision; i < vision + 1; i++){
			if(i != 0){
				x.add(i);
				y.add(i);
			}
		}
		
		for (int i=0; i<4*vision; i++){
			if(i%2 == 0){
				y.add(i,0);
			}else{
				x.add(i,0);
			}
		}
		
		
		List<Location> list = new ArrayList<>(x.size());
		for(int i=0;i<x.size();i++){
			Location neighbor = loc.getLocation(loc.getX()+x.get(i), loc.getY()+y.get(i));
			if(neighbor!=null){
				list.add(neighbor);
			}
		}
		return list;
	}

	
	public List<Cell> getAdjacentCellsSugar(Cell cell, int vision) {
		List<Cell> neighbors = new ArrayList<>(8);
		getAdjacentLocSugar(cell.getLocation(), vision).forEach(loc->{
			neighbors.add(getCell(loc));
		});
		return neighbors;
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
