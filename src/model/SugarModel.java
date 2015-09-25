package model;

import java.util.List;
import java.util.Map;


import location.Location;
import location.ToroidalLocation;
import state.SugarState;
import cell.Cell;
import cell.SugarCell;
import grid.SugarGrid;
import gui.CellSocietyGUI;

public class SugarModel extends AbstractModel{
	
	private static String STATE_NAMES[] = {"State 0", "State 1", "State 2", "State 3", "State 4"};

	SugarModel(CellSocietyGUI CSGUI) {
		super(CSGUI);
	}
	
	@Override
	public void initialize(Map<String, String> parameters, List<Map<String, String>> cells) {
		myCells.clear();
		cells.forEach(map -> {
			int x = Integer.parseInt(map.get("x"));
			int y = Integer.parseInt(map.get("y"));
			int state = Integer.parseInt(map.get("state"));
			addCell(x,y,state);
		});
		if(myCells.size()<getWidth()*getHeight())
			System.err.println("Missing Cell Info!");
		myGrid = new SugarGrid(getWidth(), getHeight(), myCells);
		
		int vision = 0; //must get vision from XML file somehow
		//myGrid.setNeighbors(vision);
		setupGraph(STATE_NAMES);
	}

	@Override
	public void intialize(Map<String, String> parameters) {
		// TODO Auto-generated method stub
		
	}
	
	private void addCell(int x,int y,int state){
		Cell cell = new SugarCell(new SugarState(state), new Location(x,y, getWidth(), getHeight()), myCSGUI);
		myCells.add(cell);
	}

}
