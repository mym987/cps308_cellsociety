package cellsociety_team11;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import gui.CellSocietyGUI;

public class FireModel extends Model {
	
	private double myProbCatchFire = 0.5;
	private Map<Location,Cell> myCellMap;

	public FireModel(int rows, int columns, CellSocietyGUI CSGUI) {
		super(rows, columns, CSGUI);
		myCellMap = new HashMap<>();
	}
	
	@Override
	public void setParameters(Map<String,String> map){
		super.setParameters(map);
		if(map.containsKey("PROB_CATCH_FIRE"))
			myProbCatchFire = Double.parseDouble(map.get("PROB_CATCH_FIRE"));
	}

	@Override
	public void buildGrid(List<Map<String, String>> cells, CellSocietyGUI CSGUI) {
		myCellMap.clear();
		cells.forEach(map -> {
			int x = Integer.parseInt(map.get("x"));
			int y = Integer.parseInt(map.get("y"));
			int state = Integer.parseInt(map.get("state"));
			FireCell cell = new FireCell(new FireState(state), new Location(x,y, getWidth(), getHeight()), CSGUI);
			cell.setProbCatchFire(myProbCatchFire);
			myCellMap.put(cell.getLocation(), cell);
		});
		if(myCellMap.size()<getWidth()*getHeight())
			System.err.println("Missing Cell Info!");
		FireGrid grid = new FireGrid(getWidth(), getHeight(), myCellMap);
		grid.setNeighbors();
		setMyGrid(grid);
	}
}
