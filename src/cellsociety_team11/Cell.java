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

//	public ArrayList<Cell> getNeighborCells(){
//		return myNeighborCells;
//	}
	
	public void setNeighborCells(ArrayList<Cell> neighbors) {
		myNeighborCells = neighbors;		
	}
	
	protected ArrayList<Cell> getNeighborsInState(State s) {
		ArrayList<Cell> neighbors = new ArrayList<Cell>();
		for(Cell c : myNeighborCells) {
			if (c.getState().equals(s)) {
				neighbors.add(c);
			}
		}
		return neighbors;
	}
	
	protected Cell getRandomNeighborInState(State s) {
		return null;
	}
	
	public abstract void determineNextState();
	
	public void goToNextState() {
		myState.goToNextState();
	}

}
