package cellsociety_team11;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class GOLModel extends Model {

	private SquareGrid myGrid;
	
	GOLModel(int rows, int columns) {
		super(rows, columns);
	}

	@Override
	public void determinNextState() {
		
	}

	@Override
	public void buildGrid(List<Map<String, String>> cells) {
		Map<Location,Cell> cellMap = new HashMap<>();
		cells.forEach(map -> {
			int x = Integer.parseInt(map.get("X"));
			int y = Integer.parseInt(map.get("Y"));
			int state = Integer.parseInt(map.get("State"));
			Cell cell = new GOLCell(new GOLState(state),new Location(x,y));
			cellMap.put(cell.getLocation(), cell);
		});
		// Why locations are double??!!
		myGrid = new SquareGrid(getColumns(), getRows(), cellMap);
	}

	@Override
	public Grid getMyGrid() {
		return myGrid;
	}
	
}
