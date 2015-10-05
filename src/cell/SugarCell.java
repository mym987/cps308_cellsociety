/**
 * @author Mike Ma
 */
package cell;

import java.util.Map;

import gui.CellGUI;
import gui.CSViewer;
import location.Location;
import state.State;
import state.SugarState;

public class SugarCell extends Cell {

	private int mySugarGrowBackRate = 1;
	private int mySugarGrowBackInterval = 1;
	private int myMetabolism = 5;
	private int myMaxSugar = 5;
	private int myTick;

	public SugarCell(State s, Location l, CSViewer CSGUI) {
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

	@Override
	public Map<String, String> getAttributes() {
		Map<String, String> map = super.getAttributes();
		map.put("agent", ((SugarState)myState).getAgent()?"1":"0");
		map.put("max",Integer.toString(myMaxSugar));
		return map;
	}
}
