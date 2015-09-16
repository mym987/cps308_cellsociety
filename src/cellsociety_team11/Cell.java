package cellsociety_team11;

import java.util.ArrayList;

import javafx.scene.shape.Polygon;

public abstract class Cell extends Polygon{ // This should not extend Polygon.  SquareCellGUI should be the class that updates the GUI

	protected State myState;
	protected Location myLoc;
	protected boolean visited;
	protected ArrayList<Cell> myNeighborCells;
	
	Cell(State s, Location l){
		myState = s;
		myLoc = l;
		visited = false;
	}

	public State getState(){
		return myState;
	}

	public void setState(State s){
		myState = s;
	}
	
	public Location getLocation(){
		return myLoc;
	}
	
	public boolean getVisited(){ // What's the purpose of this method?  All methods should have comments
		return visited;
	}
	
	public void setVisited(boolean b){
		visited = b;
	}

	public ArrayList<Cell> getNeighborCells(){
		return myNeighborCells;
	}
	
	public void setNeighborCells(ArrayList<Cell> neighbors) {
		myNeighborCells = neighbors;		
	}
	
	public abstract State determineNextState();

	public void setNextState(){  // What is the difference between this and goToNextState?
		myState = this.determineNextState(); // MyState should not be updated until goToNextState
		this.setState(myState);				 // because other cells rely on the previous state
	}

	public void goToNextState(){
		
	}

}
