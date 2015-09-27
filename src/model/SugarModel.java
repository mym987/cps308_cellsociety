package model;

import java.util.HashMap;
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

	SugarModel(CellSocietyGUI CSGUI) {
		super(CSGUI);
	}
	
	@Override
	protected void setBasicConfig(Map<String, String> parameters){
		super.setBasicConfig(parameters);
		if(parameters.containsKey("FISH_ENERGY")){
			int tmp = Integer.parseInt(parameters.get("FISH_ENERGY"));
			myFishEnergy = tmp>0?tmp:myFishEnergy;
		}
		if(parameters.containsKey("SHARK_ENERGY")){
			int tmp = Integer.parseInt(parameters.get("SHARK_ENERGY"));
			myMaxSharkEnergy = tmp>0?tmp:myMaxSharkEnergy;
		}	
		if(parameters.containsKey("LIVES_REPRODUCE")){
			int tmp = Integer.parseInt(parameters.get("LIVES_REPRODUCE"));
			myLivesReproduce = tmp>0?tmp:myLivesReproduce;
		}
		Map<Integer, String> map = new HashMap<>();
		map.put(FISH_STATE, "Fish");
		map.put(SHARK_STATE, "Shark");
		setupGraph(map);	
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
	public void initialize(Map<String, String> parameters) {
		// TODO Auto-generated method stub
		
	}
	
	private void addCell(int x,int y,int state){
		Cell cell = new SugarCell(new SugarState(state), new Location(x,y, getWidth(), getHeight()), myCSGUI);
		myCells.add(cell);
	}

}
