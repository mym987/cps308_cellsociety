package cellsociety_team11;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import gui.CellSocietyGUI;

public class GOLModel extends Model {
	
	GOLModel(int rows, int columns, CellSocietyGUI CSGUI) {
		super(rows, columns, CSGUI);
	}

	@Override
	public void buildGrid(List<Map<String, String>> cells, CellSocietyGUI CSGUI) {
		Map<Location,Cell> cellMap = new HashMap<>();
		cells.forEach(map -> {
			int r = Integer.parseInt(map.get("row"));
			int c = Integer.parseInt(map.get("column"));
			int state = Integer.parseInt(map.get("state"));
			Cell cell = new GOLCell(new GOLState(state), new Location(r,c, getRows(), getColumns()), CSGUI);
			cellMap.put(cell.getLocation(), cell);
		});
		// Why locations are double??!!
			// Because that's what the methods to draw take
		SquareGrid grid = new SquareGrid(getColumns(), getRows(), cellMap);
		grid.setNeighbors();
		setMyGrid(grid);
	}
}
