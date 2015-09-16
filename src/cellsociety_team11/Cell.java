package cellsociety_team11;

import java.util.ArrayList;

public abstract class Cell{ 

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
	
	public boolean getVisited(){ //determines if a cell has been visited as a neighbor
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