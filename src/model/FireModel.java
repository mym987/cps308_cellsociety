package model;

import java.util.List;
import java.util.Map;

import location.Location;
import location.ToroidalLocation;
import state.FireState;
import cell.Cell;
import cell.FireCell;
import grid.FireGrid;
import gui.CellSocietyGUI;

public class FireModel extends AbstractModel {
	
	private double myProbCatchFire = 0.5; //default value
	private static String STATE_NAMES[] = {"Empty", "Tree", "Burning"};

	public FireModel(CellSocietyGUI csGui) {
		super(csGui);
	}
	
	@Override
	public void setParameters(Map<String,String> map){
		// TODO Auto-generated method stub
		super.setParameters(map);
		if(map.containsKey("PROB_CATCH_FIRE"))
			myProbCatchFire = Double.parseDouble(map.get("PROB_CATCH_FIRE"));
	}

	@Override
	public void initialize(Map<String, String> parameters, List<Map<String, String>> cells) {
		myCells.clear();
		setBasicConfig(parameters);
		if(parameters.containsKey("PROB_CATCH_FIRE"))
			myProbCatchFire = Double.parseDouble(parameters.get("PROB_CATCH_FIRE"));
		cells.forEach(map -> {
			int x = Integer.parseInt(map.get("x"));
			int y = Integer.parseInt(map.get("y"));
			int state = Integer.parseInt(map.get("state"));
			addCell(x,y,state);
		});
		if(myCells.size()<getWidth()*getHeight())
			System.err.println("Missing Cell Info!");
		myGrid = new FireGrid(getWidth(), getHeight(), myCells);
		myGrid.setNeighbors();
		setupGraph(STATE_NAMES);
	}

	@Override
	public void intialize(Map<String, String> parameters) {
		// TODO Auto-generated method stub
	}
	
	private void addCell(int x,int y,int state){
		FireCell cell = new FireCell(new FireState(state), new Location(x,y, getWidth(), getHeight()), myCSGUI);
		cell.setProbCatchFire(myProbCatchFire);
		myCells.add(cell);
	}
}
