package cellsociety_team11;

import java.util.List;
import java.util.Map;

import gui.CellSocietyGUI;

public abstract class Model {
	
	protected CellSocietyGUI myCSGUI;
	private final int myNumColumn;
	private final int myNumRow;
	private Grid myGrid;
	
	Model(int rows, int columns, CellSocietyGUI CSGUI){
		myNumColumn = rows;
		myNumRow = columns;
		myCSGUI = CSGUI;
	}

	public int getRows() {
		return myNumRow;
	}

	public int getColumns() {
		return myNumColumn;
	}
	

	public abstract void step();

	public void nextState(){
		myGrid.step();
	}
	
	public abstract void buildGrid(List<Map<String,String>> cells, CellSocietyGUI CSGUI);
	
	public Grid getMyGrid(){
		return myGrid;
	};
	
	public void setMyGrid(Grid grid){
		myGrid = grid;
	}

}
