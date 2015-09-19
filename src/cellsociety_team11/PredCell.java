package cellsociety_team11;

import java.util.ArrayList;
import java.util.List;

import gui.CellSocietyGUI;
import gui.SquareCellGUI;

public class PredCell extends Cell {
	private static final double CELL_SIZE = 70;
	private static int EMPTY_STATE = 0;
	private static int SHARK_STATE = 1;
	private static int FISH_STATE = 2;
	private static int FISH_ENERGY = 2; // THIS CAN BE WHATEVER WE WANT
	private static int DEFAULT_SHARK_ENERGY = 5; // THIS CAN BE WHATEVER WE WANT

	private int numChronon = 0;
	private int sharkEnergy = 0;
	private boolean visited;

	private static final int LIVES_REPRODUCE = 5;
	SquareCellGUI myCellGUI;

	PredCell(State s, Location l, CellSocietyGUI CSGUI) {
		super(s, l, CSGUI);
		myCellGUI = new SquareCellGUI(CSGUI, l);
		myCellGUI.updateState(s);
		if (s.getStateInt() == SHARK_STATE) {
			setSharkEnergy(DEFAULT_SHARK_ENERGY);
		}
	}

	public void setNumChronon(int chronon) {
		numChronon = chronon;
	}

	public void setSharkEnergy(int x) {
		sharkEnergy = x;
	}

	public boolean getVisited() { // determines if a cell has been visited as a
									// neighbor
		return visited;
	}

	public void setVisited(boolean b) {
		visited = b;
	}

	private PredCell getRandomNeighborInState(int state) {
		State stateVar = new PredState(state);
		List<Cell> neighbors = getNeighborsInState(stateVar);
		if (neighbors.size() == 0)
			return null;
		int randNeighbor = (int) (Math.random() * neighbors.size());
		PredCell potentialCell = (PredCell) neighbors.get(randNeighbor);

		for (int i = 0; i < neighbors.size(); i++) {
			if (!potentialCell.getVisited()) {
				return potentialCell;
			}
			randNeighbor = (randNeighbor == neighbors.size() - 1) ? 0 : randNeighbor + 1;
			potentialCell = (PredCell) neighbors.get(randNeighbor);
		}
		return null;
	}

	private boolean moveMyStateToCell(PredCell chosenCell) {
		if (chosenCell == null)
			return false;
		chosenCell.getState().setNextState(myState.getStateInt()); // This can
																	// be
																	// improved
		chosenCell.setNumChronon(numChronon);
		chosenCell.setVisited(true);
		if(chosenCell.isInState(FISH_STATE))
			chosenCell.setSharkEnergy(sharkEnergy + FISH_ENERGY);
		if (numChronon < LIVES_REPRODUCE) {
			myState.setNextState(EMPTY_STATE);
			setSharkEnergy(0);
		} else {
			myState.setNextState(myState.getStateInt());
			if (myState.getStateInt() == SHARK_STATE)
				setSharkEnergy(DEFAULT_SHARK_ENERGY);
			else
				setSharkEnergy(0);
		}
		setNumChronon(0);// reproductive state reset
		return true;
	}

	@Override
	public void determineNextState() {
		if (visited || isInState(EMPTY_STATE)) {
			return;
		}
		++numChronon;
		PredCell chosenCell;

		if (myState.getStateInt() == SHARK_STATE) {
			--sharkEnergy;
			if (sharkEnergy <= 0) {
				myState.setNextState(EMPTY_STATE);
				setNumChronon(0);
				setSharkEnergy(0);
				return;
			}
			chosenCell = getRandomNeighborInState(FISH_STATE);
			if (moveMyStateToCell(chosenCell)) {
				return;
			}
		}

		chosenCell = getRandomNeighborInState(EMPTY_STATE);
		moveMyStateToCell(chosenCell);
	}

	public void remove() {
		myCellGUI.remove();
	}

	@Override
	public void goToNextState() {
		super.goToNextState();
		myCellGUI.updateState(myState);
		visited = false;
	}
}