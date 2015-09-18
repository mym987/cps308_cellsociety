package cellsociety_team11;

import java.util.List;
import java.util.Map;

public abstract class Model {
	
	private final int myNumColumn;
	private final int myNumRow;
	private Grid myGrid;
	
	public Model(int rows, int columns){
		myNumColumn = rows;
		myNumRow = columns;
	}

	public int getRows() {
		return myNumRow;
	}

	public int getColumns() {
		return myNumColumn;
	}
	
	public void nextState(){
		myGrid.step();
	}
	
	public abstract void buildGrid(List<Map<String,String>> cells);
	
	public Grid getMyGrid(){
		return myGrid;
	};
	
	public void setMyGrid(Grid grid){
		myGrid = grid;
	}

}
