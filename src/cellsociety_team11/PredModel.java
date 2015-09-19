package cellsociety_team11;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import gui.CellSocietyGUI;

public class PredModel extends Model {
	
	public PredModel(int rows, int columns, CellSocietyGUI CSGUI) {
		super(rows, columns, CSGUI);
	}

	@Override
	public void buildGrid(List<Map<String, String>> cells,  CellSocietyGUI CSGUI) {
		Map<Location,Cell> cellMap = new HashMap<>();
		cells.forEach(map -> {
			int x = Integer.parseInt(map.get("x"));
			int y = Integer.parseInt(map.get("y"));
			int state = Integer.parseInt(map.get("state"));
			Cell cell = new PredCell(new PredState(state), new Location(x,y, getWidth(), getHeight()), CSGUI);
			cellMap.put(cell.getLocation(), cell);
		});
		if(cellMap.size()<getWidth()*getHeight())
			System.err.println("Missing Cell Info!");
		SquareGrid grid = new SquareGrid(getWidth(), getHeight(), cellMap);
		grid.setNeighbors();
		setMyGrid(grid);
		//System.out.println(grid.toString());
	}

}
