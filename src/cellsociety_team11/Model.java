package cellsociety_team11;

import java.util.List;
import java.util.Map;

import gui.CellSocietyGUI;

public abstract class Model {
	final int myNumColumn;
	final int myNumRow;
	
	protected Map<Location, Cell> myCellMap;
	protected CellSocietyGUI myCSGUI;
	
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
	
	public abstract void buildGrid(List<Map<String,String>> cells, CellSocietyGUI CSGUI);
	
	public abstract Grid getMyGrid();

}
