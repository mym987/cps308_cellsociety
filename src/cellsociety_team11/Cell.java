package cellsociety_team11;

import java.util.ArrayList;

import javafx.scene.shape.Polygon;

public abstract class Cell extends Polygon{

	protected State myState;
	protected ArrayList<Cell> myNeighborCells;
	protected Location myLoc;

	Cell(State s, Location l){
		myState = s;
		myLoc = l;
	}

	public State getState(){
		return myState;
	}

	public void setState(State s){
		myState = s;
	}

	public ArrayList<Cell> getNeighborCells(){
		return myNeighborCells;
	}

	public void setNeighborCells(ArrayList<Cell> neighbors){ 
		myNeighborCells = neighbors;
	}
	
	public Location getLocation(){
		return myLoc;
	}

	public abstract State determineNextState();

	public void setNextState(State s){
		myState = this.determineNextState();
	}

	public void goToNextState(){
		
	}

}
