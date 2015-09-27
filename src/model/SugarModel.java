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
	
	private int mySugarGrowBackRate = 1;
	private int mySugarGrowBackInterval = 1;
	private int myVision = 5;
	private int myMetabolism = 5;
	

	SugarModel(CellSocietyGUI CSGUI) {
		super(CSGUI);
	}
	
	
	@Override
	protected void setBasicConfig(Map<String, String> parameters){
		super.setBasicConfig(parameters);
		if(parameters.containsKey("sugarGrowBackRate")){
			int tmp = Integer.parseInt(parameters.get("sugarGrowBackRate"));
			mySugarGrowBackRate = tmp>0?tmp:mySugarGrowBackRate;
		}
		if(parameters.containsKey("sugarGrowBackInterval")){
			int tmp = Integer.parseInt(parameters.get("sugarGrowBackInterval"));
			mySugarGrowBackInterval = tmp>0?tmp:mySugarGrowBackInterval;
		}	
		if(parameters.containsKey("vision")){
			int tmp = Integer.parseInt(parameters.get("vision"));
			myVision = tmp>0?tmp:myVision;
		}
		if(parameters.containsKey("metabolism")){
			int tmp = Integer.parseInt(parameters.get("metabolism"));
			myMetabolism = tmp>0?tmp:myMetabolism;
		}
		Map<Integer, String> map = new HashMap<>();
		map.put(0, "Agents");
		map.put(1, "Avg Sugar per Agent");
		setupGraph(map);	
	}
	
	@Override
	public void initialize(Map<String, String> parameters, List<Map<String, String>> cells) {
		myCells.clear();
		cells.forEach(map -> {
			int x = Integer.parseInt(map.get("x"));
			int y = Integer.parseInt(map.get("y"));
			int sugar = Integer.parseInt(map.get("state"));
			boolean isAgent = Integer.parseInt(map.get("agent"))!=0;
			int maxSugar = Integer.parseInt(map.get("max"));
			addCell(x,y,sugar,maxSugar,isAgent);
		});
		if(myCells.size()<getWidth()*getHeight())
			System.err.println("Missing Cell Info!");
		myGrid = new SugarGrid(getWidth(), getHeight(), myCells);
		myGrid.setNeighbors(myVision);
	}

	@Override
	public void initialize(Map<String, String> parameters) {
		setBasicConfig(parameters);
		int numAgent = 0, maxSugar = 1,initialAgentSugar = 10;
		if(myParameters.containsKey("numAgent")){
			int tmp = Integer.parseInt(myParameters.get("numAgent"));
			if (tmp>=0 && tmp <= myHeight*myWidth) numAgent = tmp;
		}
		if(myParameters.containsKey("maxSugar")){
			int tmp = Integer.parseInt(myParameters.get("maxSugar"));
			if (tmp>0) maxSugar = tmp;
		}
		if(myParameters.containsKey("initialAgentSugar")){
			int tmp = Integer.parseInt(myParameters.get("initialAgentSugar"));
			if (tmp>0) initialAgentSugar = tmp;
		}
		int mat[][] = new int[getWidth()][getHeight()];
		randomFillMatrix(mat, 0, -1, numAgent);
		for(int x = 0;x<mat.length;x++){
			for(int y=0;y<mat[x].length;y++){
				if(mat[x][y]==-1)
					addCell(x,y,initialAgentSugar,myRandom.nextInt(maxSugar),true);
				else{
					int randomMaxSugar = myRandom.nextInt(maxSugar);
					addCell(x,y,randomMaxSugar,randomMaxSugar,false);
				}
			}
		}
	}
	
	private void addCell(int x,int y,int sugar, int max, boolean isAgent){
		SugarCell cell = new SugarCell(new SugarState(sugar,isAgent), new Location(x,y, getWidth(), getHeight()), myCSGUI);
		myCells.add(cell);
	}

	@Override
	protected Map<Integer, Double> getDataPoints() {
		int[] states = new int[3];
		myCells.forEach(cell->{
			if(((SugarState)cell.getState()).getAgent()){
				states[0]++;
				states[1]+=cell.getState().getStateInt();
			}
			states[2]+=cell.getState().getStateInt();
		});
		Map<Integer, Double> stateMap = new HashMap<>();
		stateMap.put(0, (double)states[0]);
		stateMap.put(1, 1.0*states[1]/states[0]);
		stateMap.put(2, 1.0*states[2]/myCells.size());
		return stateMap;
	}

}
