package cellsociety_team11;

import java.util.ArrayList;
import java.util.List;

import gui.CellSocietyGUI;

public abstract class Cell{ 

	protected State myState;
	protected Location myLoc;
	protected List<Cell> myNeighborCells;
	protected CellSocietyGUI myCSGUI;
	
	Cell(State s, Location l, CellSocietyGUI CSGUI){
		myState = s;
		myLoc = l;
		myCSGUI = CSGUI;
	}

	public State getState(){
		return myState;
	}
	
	public boolean isInState(State s){
		return getState().equals(s);
	}
	
	public boolean isInState(int s){
		return getState().getStateInt()==s;
	}

	public void setState(State s){
		myState = s;
	}
	
	public Location getLocation(){
		return myLoc;
	}
	
	public void setNeighborCells(List<Cell> neighbors) {
		myNeighborCells = neighbors;		
	}
	
	protected List<Cell> getNeighborsInState(State s) {
		List<Cell> neighbors = new ArrayList<>();
		myNeighborCells.forEach(cell->{
			if(cell.isInState(s))
				neighbors.add(cell);
		});
		return neighbors;
	}
	
	public abstract void determineNextState();
	
	public void goToNextState() {
		myState.goToNextState();
	}
	
	public abstract void remove();

}
