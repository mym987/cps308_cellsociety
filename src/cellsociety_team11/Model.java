package cellsociety_team11;

import java.util.List;
import java.util.Map;

import gui.CellSocietyGUI;

public abstract class Model {
	
	protected CellSocietyGUI myCSGUI;
	private final int myWidth;
	private final int myHeight;
	private Grid myGrid;
	private String myAuthor;
	
	Model(int width, int height, CellSocietyGUI CSGUI){
		myWidth = width;
		myHeight = height;
		myCSGUI = CSGUI;
	}
	
	public void setParameters(Map<String,String> map){
		myAuthor = map.containsKey("author")?map.get("author"):"";
	}

	public int getHeight() {
		return myHeight;
	}

	public int getWidth() {
		return myWidth;
	}
	

	public void step(){
		myGrid.step();
	}
	
	public abstract void buildGrid(List<Map<String,String>> cells, CellSocietyGUI CSGUI);
	
	public Grid getMyGrid(){
		return myGrid;
	};
	
	public void setMyGrid(Grid grid){
		myGrid = grid;
	}

	public void removeCells() {
		myGrid.removeCells();
	}
}
