package cell;

import java.util.ArrayList;
import java.util.List;

import location.Location;
import state.State;
import gui.CellGUI;
import gui.CellSocietyGUI;
import gui.SquareCellGUI;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.input.MouseEvent;

public abstract class Cell {

	protected State myState;
	protected Location myLoc;
	protected List<Cell> myNeighborCells;
	protected CellSocietyGUI myCSGUI;
	protected CellGUI myCellGUI;
	protected int myNumStates;

	Cell(State s, int numStates, Location l, CellSocietyGUI CSGUI) {
		myState = s;
		myLoc = l;
		myCSGUI = CSGUI;
		myNumStates = numStates;
	}
	
	protected void addClickListener() {
		myCellGUI.addClickListener(e->incrementState());
	}

	public State getState() {
		return myState;
	}

	public boolean isInState(State s) {
		return getState().equals(s);
	}

	public boolean isInState(int s) {
		return getState().getStateInt() == s;
	}

	public void setState(State s) {
		myState = s;
	}

	public Location getLocation() {
		return myLoc;
	}

	public void setNeighborCells(List<Cell> neighbors) {
		myNeighborCells = neighbors;
	}

	protected List<Cell> getNeighborsInState(State s) {
		List<Cell> neighbors = new ArrayList<>();
		myNeighborCells.forEach(cell -> {
			if (cell.isInState(s))
				neighbors.add(cell);
		});
		return neighbors;
	}
	
	protected List<Cell> getNeighborsInStateInt(int stateInt) {
		List<Cell> neighbors = new ArrayList<>();
		myNeighborCells.forEach(cell->{
			if(cell.getState().getStateInt() == stateInt)
				neighbors.add(cell);
		});
		return neighbors;
	}
	
	public abstract void determineNextState();

	public void goToNextState() {
		myState.goToNextState();
		myCellGUI.updateState(myState);
	}

	public abstract void remove();

	public void incrementState() {
		int nextState = (myState.getStateInt() + 1) % myNumStates;
		myState.setNextState(nextState);
		myState.goToNextState();
		myCellGUI.updateState(myState);
	}

	public void removeOutlines() {
		myCellGUI.removeOutlines();
	}

	public void addOutlines() {
		myCellGUI.addOutlines();
	}
}
