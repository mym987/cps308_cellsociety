package cellsociety_team11;

import java.util.Random;

import gui.CellSocietyGUI;
import gui.SquareCellGUI;

public class FireCell extends Cell{
	public static final double CELL_SIZE = 70;
	
	private static final int EMPTY_STATE = 0;
	private static final int LIVE_STATE = 1;
	private static final int BURN_STATE = 2;
	private static final double PROB_CATCHFIRE = 50;

	SquareCellGUI myCellGUI;

	FireCell(State s, Location l, CellSocietyGUI CSGUI) {
		super(s, l, CSGUI);
		myCellGUI = new SquareCellGUI(CSGUI, l);
		myCellGUI.updateState(s);
	}

	@Override
	public void determineNextState() {
		int numBurningNeighbors = getNeighborsInState(new FireState(BURN_STATE)).size();
		Random randomGenerator = new Random();
		if (isInState(LIVE_STATE) && numBurningNeighbors >= 1) {
			int randomInt = randomGenerator.nextInt(100);
			if (randomInt < PROB_CATCHFIRE) {
				myState.setNextState(BURN_STATE);
			} else {
				myState.setNextState(LIVE_STATE);
			}
		} else if (isInState(LIVE_STATE)) {
			myState.setNextState(LIVE_STATE);
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
