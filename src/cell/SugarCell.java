package cell;

import gui.CellSocietyGUI;
import location.Location;
import state.State;
import state.SugarState;

public class SugarCell extends Cell{
	private static final int PATCH_STATE_0 = 0;
	private static final int PATCH_STATE_1 = 1;
	private static final int PATCH_STATE_2 = 2;
	private static final int PATCH_STATE_3 = 3;
	private static final int PATCH_STATE_4 = 4;
	
	private static final int AGENT_STATE = 1;
	private static final int NO_AGENT_STATE = 0;
	
	private static final int MAX_SUGAR_CAPACITY = 4;
	private static final int SUGAR_GROWBACK_RATE = 1;
	private static final int SUGAR_GROWBACK_INTERVAL = 1;
	
	private int myAgentState;
	
	private int myPatchAmntSugar;
	private int myAgentAmntSugar;  //arbitrary number
	private int myAgentSugarMetabolism = 1;
	private int myAgentVision = 1; //don't really understand what this represents in SugarScape
	
	

	SugarCell(State s, Location l, CellSocietyGUI CSGUI) {
		super(s, l, CSGUI);
		// TODO Auto-generated constructor stub
		myPatchAmntSugar = s.getStateInt();
		
		SugarState sugarState = (SugarState) s;
		myAgentState = sugarState.getAgent();
	}

	@Override
	public void determineNextState() {
		if (myAgentState == AGENT_STATE) {
			getNeighborsInState(s)
		}
	}

	@Override
	public void remove() {
		// TODO Auto-generated method stub
		
	}

}
