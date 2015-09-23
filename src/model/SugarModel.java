package model;

import java.util.HashMap;
import java.util.List;
import java.util.Map;


import location.Location;
import state.SugarState;
import cell.Cell;
import cell.SugarCell;
import grid.SugarGrid;
import gui.CellSocietyGUI;

public class SugarModel extends Model{
	
	private Map<Location,Cell> myCells;

	SugarModel(int width, int height, CellSocietyGUI CSGUI) {
		super(width, height, CSGUI);
		// TODO Auto-generated constructor stub
		myCells = new HashMap<>();
	}

	@Override
	public void buildGrid(List<Map<String, String>> cells, CellSocietyGUI CSGUI) {
		myCells.clear();
		cells.forEach(map -> {
			int x = Integer.parseInt(map.get("x"));
			int y = Integer.parseInt(map.get("y"));
			int state = Integer.parseInt(map.get("state"));
			Cell cell = new SugarCell(new SugarState(state), new Location(x,y, getWidth(), getHeight()), CSGUI);
			myCells.put(cell.getLocation(), cell);
		});
		if(myCells.size()<getWidth()*getHeight())
			System.err.println("Missing Cell Info!");
		SugarGrid grid = new SugarGrid(getWidth(), getHeight(), myCells);
		
		int vision = null; //must get vision from XML file somehow
		grid.setNeighbors(vision);
		setMyGrid(grid);
		
	}

}
