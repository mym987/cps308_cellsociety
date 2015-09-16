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
	
	protected int getNumNeighborsInState(State s) {
		int numNeighbors = 0;
		for(Cell c : myNeighborCells) {
			GOLCell golCell = (GOLCell) c;
			if (golCell.getState().equals(s)) {
				++numNeighbors;
			}
		}
		return numNeighbors;
	}
	
	public abstract void determineNextState();
	
	public void goToNextState() {
		myState.goToNextState();
	}

}
