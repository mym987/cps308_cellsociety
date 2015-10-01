// This entire file is part of my masterpiece.
// Mike Ma (ym67)

package model;

import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.Stack;

import location.Location;
import state.SegState;
import cell.Cell;
import cell.SegCell;
import grid.GridFactory;
import gui.CellSocietyGUI;

public class SegModel extends AbstractModel {

	private static final int EMPTY_STATE = 0;
	private static final int STATE_A = 1;
	private static final int STATE_B = 2;

	private double mySimilarity = 0.7; // default value
	private double myDissatisfactionRate;

	public SegModel(CellSocietyGUI csGui) {
		super(csGui);
	}

	@Override
	public void step() {
		Stack<Cell> exchangeList = new Stack<>();
		exchangeList.addAll(getDissatisfiedCells());
		if (myCells != null && !myCells.isEmpty())
			myDissatisfactionRate = 100 - 100.0 * exchangeList.size() / myCells.size();
		exchangeList.addAll(getCellsInState(EMPTY_STATE));
		Collections.shuffle(exchangeList);

		while (exchangeList.size() >= 2) {
			SegCell cell1 = (SegCell) exchangeList.pop();
			SegCell cell2 = (SegCell) exchangeList.pop();
			cell2.determineNextState(cell1.getState());
			cell1.determineNextState(cell2.getState());
		}
		myCells.forEach(cell -> {
			cell.goToNextState();
		});
		myStepNum++;
		updateGraph();
	}
	
	private Set<Cell> getDissatisfiedCells(){
		Set<Cell> set = new HashSet<>();
		myCells.forEach(cell -> {
			SegCell tmp = (SegCell)cell;
			if (!tmp.isEmpty() && !tmp.isSatisfied())
				set.add(tmp);
		});
		return set;
	}
	
	
	private Set<Cell> getCellsInState(int s){
		Set<Cell> set = new HashSet<>();
		myCells.forEach(cell -> {
			SegCell tmp = (SegCell)cell;
			if (!tmp.isEmpty() && tmp.isInState(new SegState(s)))
				set.add(tmp);
		});
		return set;
	}

	@Override
	protected void setBasicConfig(java.util.Map<String, String> parameters) {
		super.setBasicConfig(parameters);
		if (parameters.containsKey("similarity")) {
			double tmp = Double.parseDouble(myParameters.get("similarity"));
			mySimilarity = (tmp <= 1 && tmp >= 0) ? tmp : mySimilarity;
		}
		Map<Integer, String> map = new HashMap<>();
		map.put(0, "Satisfaction Rate (%)");
		setupGraph(map);
	};

	@Override
	public void initialize(Map<String, String> parameters, List<Map<String, String>> cells) throws Exception{
		setBasicConfig(parameters);
		cells.forEach(map -> {
			int x = Integer.parseInt(map.get("x"));
			int y = Integer.parseInt(map.get("y"));
			int state = Integer.parseInt(map.get("state"));
			addCell(x, y, state);
		});
		if (myCells.size() < getWidth() * getHeight())
			throw new Exception("Missing Cell Info!");
		myGrid = GridFactory.makeGrid(getWidth(), getHeight(), myCells, myCSGUI);
		myGrid.setNeighbors();
	}

	@Override
	public void initialize(Map<String, String> parameters) {
		setBasicConfig(parameters);
		double percentA = 0.5, percentB = 0.5;
		if (myParameters.containsKey("percentA")) {
			double tmp = Double.parseDouble(myParameters.get("percentA"));
			percentA = (tmp >= 0 && tmp <= 1) ? tmp : percentA;
			percentB = 1 - percentA;
		}
		if (myParameters.containsKey("percentB")) {
			double tmp = Double.parseDouble(myParameters.get("percentB"));
			percentB = (tmp >= 0 && tmp <= percentB) ? tmp : percentB;
		}

		int total = myWidth * myHeight;
		int numTypeA = (int) (total * percentA), numTypeB = (int) (total * percentB);
		int mat[][] = new int[getWidth()][getHeight()];
		
		randomFillMatrix(mat, EMPTY_STATE, STATE_A, numTypeA);
		randomFillMatrix(mat, EMPTY_STATE, STATE_B, numTypeB);
		
		for (int x = 0; x < mat.length; x++)
			for (int y = 0; y < mat[x].length; y++)
				addCell(x, y, mat[x][y]);
		myGrid = GridFactory.makeGrid(getWidth(), getHeight(), myCells, myCSGUI);
		myGrid.setNeighbors();
	}

	private void addCell(int x, int y, int state) {
		Location loc = Location.makeLocation(x, y, getWidth(), getHeight(), myCSGUI);
		SegCell cell = new SegCell(new SegState(state), loc, myCSGUI);
		cell.setSimilarity(mySimilarity);
		myCells.add(cell);
	}

	@Override
	protected Map<Integer, Double> getDataPoints() {
		Map<Integer, Double> map = new HashMap<>();
		map.put(0, (double) myDissatisfactionRate);
		return map;
	}

}
