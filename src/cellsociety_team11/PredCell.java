package cellsociety_team11;

import java.util.ArrayList;

public class PredCell extends Cell {
	public static final double CELL_SIZE = 70;
	private static int EMPTY_STATE = 0;
	private static int SHARK_STATE = 1;
	private static int FISH_STATE = 2;

	private static int FISH_ENERGY = 2; // THIS CAN BE WHATEVER WE WANT

	// private Integer[] myStateInts = {0,1,2};
	private int numChronon;
	private int sharkEnergy;

	private static final int LIVES_REPRODUCE = 5;

	PredCell(State s, Location l) {
		super(s, l);

		numChronon = 0;
		if (s.getStateInt() == SHARK_STATE) {
			sharkEnergy = 5;
		}
		visited = false;

	}

	public int getNumChronon() {
		return numChronon;
	}

	public void setNumChronon(int x) {
		numChronon = x;
	}

	public void incrementChronon() {
		++numChronon;
	}

	public int getSharkEnergy() {
		return sharkEnergy;
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

	@Override
	public void determineNextState() { // This method is enormous and seems very
										// repetitive. Let's try to split it up
		// State nextState;

		if (visited) {
			return;
		}

		++numChronon;

		if (myState.getStateInt() == FISH_STATE) { // Move to empty cell
			ArrayList<Cell> emptyNeighbors = getNeighborsInState(new PredState(EMPTY_STATE));		// THIS WHOLE SECTION CAN BE TURNED INTO A METHOD
			int randNeighbor = (int) (Math.random() * emptyNeighbors.size());
			PredCell pc = (PredCell) emptyNeighbors.get(randNeighbor);

			for (int i = 0; i < emptyNeighbors.size(); i++) {
				if (!pc.getVisited()) {
					pc.getState().setNextState(FISH_STATE);
					pc.setVisited(true);
					pc.incrementChronon();
					if (pc.getNumChronon() < LIVES_REPRODUCE) {
						myState.setNextState(EMPTY_STATE);
					}
					this.setNumChronon(0);// reproductive state reset
					return;
				}
				randNeighbor = (randNeighbor == emptyNeighbors.size() - 1) ? 0 : randNeighbor + 1;
			}

		} else if (myState.getStateInt() == SHARK_STATE) {

			ArrayList<Cell> fishNeighbors = getNeighborsInState(new PredState(FISH_STATE));		// THIS WHOLE SECTION CAN BE TURNED INTO A METHOD
			int randFish = (int) (Math.random() * fishNeighbors.size());
			PredCell pc = (PredCell) fishNeighbors.get(randFish);

			for (int i = 0; i < fishNeighbors.size(); i++) {
				if (!pc.getVisited()) {
					pc.getState().setNextState(SHARK_STATE);
					pc.setSharkEnergy(sharkEnergy + FISH_ENERGY);
					pc.setNumChronon(++numChronon);
					pc.setVisited(true);
					if (pc.getNumChronon() < LIVES_REPRODUCE) {
						myState.setNextState(EMPTY_STATE);
					}
					this.setNumChronon(0); // reproductive state reset
					return;
				}
				randFish = (randFish == fishNeighbors.size() - 1) ? 0 : randFish + 1;
			}
			
			--sharkEnergy;
			
			if(sharkEnergy == 0) {
				myState.setNextState(EMPTY_STATE);
				this.setNumChronon(0);// reproductive state reset
				return;
			}

			ArrayList<Cell> emptyNeighbors = getNeighborsInState(new PredState(EMPTY_STATE));		// THIS WHOLE SECTION CAN BE TURNED INTO A METHOD
			int randNeighbor = (int) (Math.random() * emptyNeighbors.size());
			pc = (PredCell) emptyNeighbors.get(randNeighbor);

			for (int i = 0; i < emptyNeighbors.size(); i++) {
				if (!pc.getVisited()) {
					pc.getState().setNextState(SHARK_STATE);
					pc.setVisited(true);
					pc.setNumChronon(++numChronon);
					if (pc.getNumChronon() < LIVES_REPRODUCE) {
						myState.setNextState(EMPTY_STATE);
					}
					this.setNumChronon(0);// reproductive state reset
					return;
				}
				randNeighbor = (randNeighbor == emptyNeighbors.size() - 1) ? 0 : randNeighbor + 1;
			}
		}
	}

}
