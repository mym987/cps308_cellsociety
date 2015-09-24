package model;

import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.Set;

import cell.Cell;
import grid.Grid;
import gui.CellSocietyGUI;

public abstract class Model {
	
	protected CellSocietyGUI myCSGUI;
	protected int myWidth;
	protected int myHeight;
	protected Grid myGrid;
	protected Set<Cell> myCells;
	protected Map<String,String> myParameters;
	protected Random myRandom;
	protected String myAuthor = "CPS308_Team11";
	
	Model(CellSocietyGUI CSGUI){
		myCSGUI = CSGUI;
		myRandom = new Random();
		myCells = new HashSet<>();
	}
	
	public abstract void initialize(Map<String,String> parameters,List<Map<String,String>> cells);

	public abstract void intialize(Map<String,String> parameters);
	
	public void setParameters(Map<String,String> parameters){
		myParameters = parameters;
	}
	
	public Map<String,String> getParameters(){
		return myParameters;
	}
	
	protected void setDimensions(int width, int height){
		myWidth = width;
		myHeight = height;
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
	
	//public abstract void buildGrid(List<Map<String,String>> cells, CellSocietyGUI CSGUI);

	public void clear() {
		myGrid.clear();
	}
	
	protected void setBasicConfig(Map<String, String> parameters){
		myParameters = parameters;
		int width = Integer.parseInt(myParameters.get("width"));
		int height = Integer.parseInt(myParameters.get("height"));
		setDimensions(width, height);
		myAuthor = myParameters.containsKey("author")?myParameters.get("author"):myAuthor;
		
	}
}
