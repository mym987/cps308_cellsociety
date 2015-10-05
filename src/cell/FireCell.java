package cell;

import java.util.Random;

import location.Location;
import state.FireState;
import state.State;
import gui.CellGUI;
import gui.CSViewer;

public class FireCell extends Cell{
	
	private static final int EMPTY_STATE = 0;
	private static final int LIVE_STATE = 1;
	private static final int BURN_STATE = 2;
	private static final int NUM_STATES = 3;
	
	private double myProbCatchFire;

	public FireCell(State s, Location l, CSViewer CSGUI) {
		super(s, NUM_STATES, l, CSGUI);
		myCellGUI = CellGUI.makeCellGUI(CSGUI, l);
		myCellGUI.updateState(s);
		addClickListener();
	}

	/**
	 * Set the probability of catching fire
	 * @param probCatchFire
	 */
	public void setProbCatchFire(double probCatchFire) {
		myProbCatchFire = probCatchFire;
	}
	 
	@Override
	public void determineNextState() {
		int numBurningNeighbors = getNeighborsInState(new FireState(BURN_STATE)).size();
		Random randomGenerator = new Random();
		if (isInState(LIVE_STATE) && numBurningNeighbors >= 1) {
			if (randomGenerator.nextDouble() < myProbCatchFire) {
				myState.setNextState(BURN_STATE);
			} else {
				myState.setNextState(LIVE_STATE);
			}
		} else if(isInState(BURN_STATE)) {
			myState.setNextState(EMPTY_STATE);
		}
	}
}
