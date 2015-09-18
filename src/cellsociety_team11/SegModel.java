package cellsociety_team11;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import gui.CellSocietyGUI;

public class SegModel extends Model {

	public SegModel(int rows, int columns, CellSocietyGUI CSGUI) {
		super(rows, columns, CSGUI);
	}

	@Override
	public void buildGrid(List<Map<String, String>> cells, CellSocietyGUI CSGUI) {
		Map<Location,Cell> cellMap = new HashMap<>();
		cells.forEach(map -> {
			int x = Integer.parseInt(map.get("row"));
			int y = Integer.parseInt(map.get("column"));
			int state = Integer.parseInt(map.get("state"));
			Cell cell = new GOLCell(new GOLState(state),new Location(x,y, getRows(), getColumns()), CSGUI);
			cellMap.put(cell.getLocation(), cell);
		});
		Grid grid = new SquareGrid(getColumns(), getRows(), cellMap);
		setMyGrid(grid);
	}
	
	@Override
	public void step() {
		getMyGrid().step();
	}
}
