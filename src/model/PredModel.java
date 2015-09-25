package model;

import java.util.List;
import java.util.Map;

import location.Location;
import location.ToroidalLocation;
import state.PredState;
import cell.PredCell;
import grid.SquareGrid;
import grid.TriangleGrid;
import gui.CellSocietyGUI;

public class PredModel extends AbstractModel {
	
	private int myFishEnergy = 5;
	private int myMaxSharkEnergy = 5;
	private int myLivesReproduce = 5;
	private static String STATE_NAMES[] = {"Empty", "Shark", "Fish"};
	
	public PredModel(CellSocietyGUI CSGUI) {
		super(CSGUI);
	}
	
	@Override
	public void setParameters(Map<String,String> map){
		// TODO Auto-generated method stub
		super.setParameters(map);
	}

	@Override
	public void initialize(Map<String, String> parameters, List<Map<String, String>> cells) {
		myCells.clear();
		setBasicConfig(parameters);
		if(parameters.containsKey("FISH_ENERGY"))
			myFishEnergy = Integer.parseInt(parameters.get("FISH_ENERGY"));
		if(parameters.containsKey("SHARK_ENERGY"))
			myMaxSharkEnergy = Integer.parseInt(parameters.get("SHARK_ENERGY"));
		if(parameters.containsKey("LIVES_REPRODUCE"))
			myLivesReproduce = Integer.parseInt(parameters.get("LIVES_REPRODUCE"));
		cells.forEach(map -> {
			int x = Integer.parseInt(map.get("x"));
			int y = Integer.parseInt(map.get("y"));
			int state = Integer.parseInt(map.get("state"));
			addCell(x,y,state);
			
		});
		if(myCells.size()<getWidth()*getHeight())
			System.err.println("Missing Cell Info!");
		myGrid = new SquareGrid(getWidth(), getHeight(), myCells);
		myGrid.setNeighbors();
		setupGraph(STATE_NAMES);
	}

	@Override
	public void intialize(Map<String, String> parameters) {
		// TODO Auto-generated method stub
		
	}
	
	private void addCell(int x,int y,int state){
		PredCell cell = new PredCell(new PredState(state), new Location(x,y, getWidth(), getHeight()), myCSGUI);
		cell.setParameters(myFishEnergy, myMaxSharkEnergy, myLivesReproduce);
		myCells.add(cell);
	}

}
