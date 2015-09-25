package model;

import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.Set;

import cell.Cell;
import grid.Grid;
import gui.CellSocietyGUI;

public abstract class AbstractModel implements IModel {
	
	protected CellSocietyGUI myCSGUI;
	protected int myWidth;
	protected int myHeight;
	protected Grid myGrid;
	protected Set<Cell> myCells;
	protected Map<String,String> myParameters;
	protected Random myRandom;
	protected String myAuthor = "CPS308_Team11";
	protected int myStepNum = 0;
	
	AbstractModel(CellSocietyGUI CSGUI){
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
		return Collections.unmodifiableMap(myParameters);
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
		++myStepNum;
		myGrid.step();
		updateGraph();
	}
	
	public void clear() {
		myStepNum = 0;
		myGrid.clear();
		myCSGUI.resetGraph();
	}
	
	protected void setupGraph(String[] names) {
		myCSGUI.resetGraph();
		for(int i = 0; i < names.length; i++) {
			myCSGUI.addSeries(i, names[i]);
		}
		updateGraph();
	}
	
	protected void updateGraph() {
		HashMap<Integer, Integer> statemap = new HashMap<Integer, Integer>();
		for(Cell cell: myCells) {
			int state = cell.getState().getStateInt();
			Integer numInState = statemap.get(state);
			if(numInState == null)
				numInState = 0;
			++numInState;
			statemap.put(state, numInState);
		}
		for(int key: statemap.keySet()) {
			myCSGUI.addDataPoint(myStepNum, statemap.get(key), key);
		}
	}
	
	protected void setBasicConfig(Map<String, String> parameters){
		myParameters = parameters;
		int width = Integer.parseInt(myParameters.get("width"));
		int height = Integer.parseInt(myParameters.get("height"));
		setDimensions(width, height);
		myAuthor = myParameters.containsKey("author")?myParameters.get("author"):myAuthor;
	}
	
	public Set<Cell> getCells(){
		return Collections.unmodifiableSet(myCells);
	}
}
