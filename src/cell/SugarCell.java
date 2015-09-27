package cell;

import gui.CellGUI;
import gui.CellSocietyGUI;
import gui.SquareCellGUI;
import location.Location;
import state.State;
import state.SugarState;

public class SugarCell extends AbstractCell {

	private int mySugarGrowBackRate = 1;
	private int mySugarGrowBackInterval = 1;
	private int myMetabolism = 5;
	private int myMaxSugar = 5;
	private int myTick;

	public SugarCell(State s, Location l, CellSocietyGUI CSGUI) {
		super(s, 2, l, CSGUI);
		myCellGUI = CellGUI.makeCellGUI(CSGUI, l);
		myCellGUI.updateState(s);
	}

	public void setParameters(int sugarGrowBackRate, int sugarGrowBackInterval, int metabolism, int maxSugar) {
		mySugarGrowBackInterval = sugarGrowBackInterval;
		mySugarGrowBackRate = sugarGrowBackRate;
		myMetabolism = metabolism;
		myMaxSugar = maxSugar;
	}

	@Override
	public void determineNextState() {
		SugarState state = (SugarState) myState;
		if (!state.getAgent()) {
			if (myTick % mySugarGrowBackInterval == 0)
				state.setNextState(state.getStateInt() + mySugarGrowBackRate);
			if (state.getStateInt() > myMaxSugar)
				state.setNextState(myMaxSugar);
			myTick++;
		} else {
			myTick = 0;
		}
	}

}
