package cellsociety_team11;

import java.util.List;
import java.util.Map;

public abstract class Model {
	final int myNumColumn;
	final int myNumRow;
	
	Model(int rows, int columns){
		myNumColumn = rows;
		myNumRow = columns;
	}

	public int getRows() {
		return myNumRow;
	}

	public int getColumns() {
		return myNumColumn;
	}
	
	public abstract void determinNextState();
	
	public abstract void buildGrid(List<Map<String,String>> cells);
	
	public abstract Grid getMyGrid();

}
