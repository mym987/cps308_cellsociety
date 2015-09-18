package cellsociety_team11;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class GOLModel extends Model {

	public GOLModel(int rows, int columns) {
		super(rows, columns);
	}

	@Override
	public void buildGrid(List<Map<String, String>> cells) {
		Map<Location,Cell> cellMap = new HashMap<>();
		cells.forEach(map -> {
			int x = Integer.parseInt(map.get("row"));
			int y = Integer.parseInt(map.get("column"));
			int state = Integer.parseInt(map.get("state"));
			Cell cell = new GOLCell(new GOLState(state),new Location(x,y));
			cellMap.put(cell.getLocation(), cell);
		});
		// Why locations are double??!!
		Grid grid = new SquareGrid(getColumns(), getRows(), cellMap);
		setMyGrid(grid);
	}
	
}
