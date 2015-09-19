package cellsociety_team11;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import gui.CellSocietyGUI;

public class PredModel extends Model {
	
	private Map<Location,Cell> myCells;
	private int myFishEnergy;
	private int myMaxSharkEnergy;
	private int myLivesReproduce;
	
	public PredModel(int rows, int columns, CellSocietyGUI CSGUI) {
		super(rows, columns, CSGUI);
		myCells = new HashMap<>();
	}
	
	@Override
	public void setParameters(Map<String,String> map){
		super.setParameters(map);
		if(map.containsKey("FISH_ENERGY"))
			myFishEnergy = Integer.parseInt(map.get("FISH_ENERGY"));
		if(map.containsKey("SHARK_ENERGY"))
			myMaxSharkEnergy = Integer.parseInt(map.get("SHARK_ENERGY"));
		if(map.containsKey("LIVES_REPRODUCE"))
			myLivesReproduce = Integer.parseInt(map.get("LIVES_REPRODUCE"));
	}

	@Override
	public void buildGrid(List<Map<String, String>> cells,  CellSocietyGUI CSGUI) {
		myCells.clear();
		cells.forEach(map -> {
			int x = Integer.parseInt(map.get("x"));
			int y = Integer.parseInt(map.get("y"));
			int state = Integer.parseInt(map.get("state"));
			PredCell cell = new PredCell(new PredState(state), new Location(x,y, getWidth(), getHeight()), CSGUI);
			cell.setParameters(myFishEnergy, myMaxSharkEnergy, myLivesReproduce);
			myCells.put(cell.getLocation(), cell);
		});
		if(myCells.size()<getWidth()*getHeight())
			System.err.println("Missing Cell Info!");
		SquareGrid grid = new SquareGrid(getWidth(), getHeight(), myCells);
		grid.setNeighbors();
		setMyGrid(grid);
	}

}
