/**
 * @author Mike Ma
 */
package model;

import java.lang.reflect.Constructor;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.Set;
import java.util.Stack;

import cell.Cell;
import grid.Grid;
import gui.CSViewer;

public abstract class Model {
	
	protected CSViewer myCSGUI;
	protected int myWidth;
	protected int myHeight;
	protected Grid myGrid;
	protected Set<Cell> myCells;
	protected Map<String,String> myParameters;
	protected Random myRandom;
	protected String myAuthor = "CPS308Team11";
	protected int myStepNum = 0;
	
	/**
	 * Create a model with specified model name
	 * 
	 * @param name
	 * @param csGui
	 * @return an instance of a subclass implementing model
	 */
	@SuppressWarnings("rawtypes")
	public static Model getModel(String name, CSViewer csGui) throws Exception {
		name = Model.class.getPackage().getName() + "." + name;
		Class[] types = { CSViewer.class };
		Constructor constructor;
		constructor = Class.forName(name).getDeclaredConstructor(types);
		return (Model) constructor.newInstance(csGui);
	}
	
	Model(CSViewer CSGUI){
		myCSGUI = CSGUI;
		myRandom = new Random();
		myCells = new HashSet<>();
	}
	/**
	 * Set current model parameters
	 * 
	 * @param parameters
	 */
	public void setParameters(Map<String,String> parameters){
		myParameters = parameters;
	}
	/**
	 * Get current model parameters, read only
	 * 
	 * @param parameters
	 */
	public Map<String,String> getParameters(){
		return Collections.unmodifiableMap(myParameters);
	}
	
	protected void setDimensions(int width, int height){
		myWidth = width;
		myHeight = height;
	}
	/**
	 * Get # of rows of this model
	 * 
	 * @return height
	 */
	public int getHeight() {
		return myHeight;
	}
	/**
	 * Get # of columns of this model
	 * 
	 * @return width
	 */
	public int getWidth() {
		return myWidth;
	}
	/**
	 * Go to next frame
	 */
	public void step(){
		++myStepNum;
		myGrid.step();
		updateGraph();
	}
	/**
	 * clear the grid
	 */
	public void clear() {
		myStepNum = 0;
		if(myGrid!=null)
			myGrid.clear();
		if(myCells!=null)
			myCells.clear();
		myCSGUI.resetGraph();
	}
	
	protected void randomFill(int[][] mat, int initialState, int finalState, int totalToFill) {
		Stack<Integer> stack = new Stack<>();
		for (int x = 0; x < mat.length; x++)
			for (int y = 0; y < mat[x].length; y++) {
				if (mat[x][y] == initialState)
					stack.push(1000 * x + y);
			}
		Collections.shuffle(stack, myRandom);
		int i = 0;
		while (!stack.isEmpty() && i < totalToFill) {
			int t = stack.pop();
			int x = t / 1000, y = t % 1000;
			mat[x][y] = finalState;
			i++;
		}
	}
	
	protected void setupGraph(Map<Integer,String> names) {
		myCSGUI.resetGraph();
		names.forEach((k,v)->{myCSGUI.addSeries(k, v);});
	}
	
	protected void updateGraph() {
		getDataPoints().forEach((series,value)->{myCSGUI.addDataPoint(myStepNum,value,series);});
	}
	/**
	 * 
	 * @return Map from Series to Y value
	 */
	protected abstract Map<Integer, Double> getDataPoints();
	
	protected void setBasicConfig(Map<String, String> parameters){
		clear();
		myParameters = parameters;
		int width = Integer.parseInt(myParameters.get("width"));
		int height = Integer.parseInt(myParameters.get("height"));
		setDimensions(width, height);
		myAuthor = myParameters.containsKey("author")?myParameters.get("author"):myAuthor;
	}
	/**
	 * get an immutable set of cells
	 * 
	 * @return Set<Cell>
	 */
	public Set<Cell> getCells(){
		return Collections.unmodifiableSet(myCells);
	}
	
	@Override
	public final String toString() {
		String name = "model";
		if(myParameters!=null && myParameters.containsKey("name")){
			name = myParameters.get("name");
		}
		return name+"_"+myWidth+"_"+myHeight+"_"+myAuthor;
	}

	/**
	 * Initialize this model with a set of given parameters and a list of cell
	 * attributes
	 * 
	 * @param parameters
	 * @param cells
	 * @throws Exception
	 */
	public abstract void initialize(Map<String, String> parameters, List<Map<String, String>> cells) throws Exception;

	/**
	 * Initialize this model with a set of given parameters, generating cells
	 * randomly
	 * 
	 * @param parameters
	 * @throws Exception
	 */
	public abstract void initialize(Map<String, String> parameters) throws Exception;
}
