package cell;

import java.util.Random;

import location.Location;
import state.FireState;
import state.State;
import gui.CellSocietyGUI;
import gui.SquareCellGUI;

public class FireCell extends Cell{
	
	private static final int EMPTY_STATE = 0;
	private static final int LIVE_STATE = 1;
	private static final int BURN_STATE = 2;
	
	private double myProbCatchFire = 0.5;
	private SquareCellGUI myCellGUI;

	public FireCell(State s, Location l, CellSocietyGUI CSGUI) {
		super(s, l, CSGUI);
		myCellGUI = new SquareCellGUI(CSGUI, l);
		myCellGUI.updateState(s);
	}

	
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

	@Override
	public void goToNextState() {
		super.goToNextState();
		myCellGUI.updateState(myState);
	}
	
	public void remove() {
		myCellGUI.remove();
	}

}