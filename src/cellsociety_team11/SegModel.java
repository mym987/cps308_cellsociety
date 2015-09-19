package cellsociety_team11;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.Stack;

import gui.CellSocietyGUI;

public class SegModel extends Model {
	
	private Map<Location,Cell> myCells;

	public SegModel(int rows, int columns, CellSocietyGUI CSGUI) {
		super(rows, columns, CSGUI);
	}

	@Override
	public void buildGrid(List<Map<String, String>> cells, CellSocietyGUI CSGUI) {
		myCells = new HashMap<>();
		cells.forEach(map -> {
			int x = Integer.parseInt(map.get("x"));
			int y = Integer.parseInt(map.get("y"));
			int state = Integer.parseInt(map.get("state"));
			Cell cell = new SegCell(new SegState(state), new Location(x, y, getWidth(), getHeight()), CSGUI);
			myCells.put(cell.getLocation(), cell);
		});
		if (myCells.size() < getWidth() * getHeight())
			System.err.println("Missing Cell Info!");
		SquareGrid grid = new SquareGrid(getWidth(), getHeight(), myCells);
		grid.setNeighbors();
		setMyGrid(grid);
	}
	
	@Override
	public void step(){
		Stack<Cell> dissatisfiedList = getDissatisfiedCells();
		Stack<Cell> vacentList = getVacentCells();
		Collections.shuffle(vacentList);
		while(!dissatisfiedList.isEmpty() && !vacentList.isEmpty()){
			SegCell dissatisfiedCell = (SegCell)dissatisfiedList.pop();
			SegCell vacentCell = (SegCell)vacentList.pop();
			vacentCell.determineNextState(dissatisfiedCell.getState());
			dissatisfiedCell.determineNextState(vacentCell.getState());
		}
		myCells.forEach((loc,cell)->{cell.goToNextState();});
	}
	
	private Stack<Cell> getDissatisfiedCells(){
		Stack<Cell> list = new Stack<>();
		myCells.forEach((loc,cell)->{
			if(!((SegCell)cell).isEmpty() && !((SegCell)cell).isSatisfied())
				list.add(cell);
		});
		return list;
	}
	
	private Stack<Cell> getVacentCells(){
		Stack<Cell> list = new Stack<>();
		myCells.forEach((loc,cell)->{
			if(((SegCell)cell).isEmpty())
				list.add(cell);
		});
		return list;
	}

}
