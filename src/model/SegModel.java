package model;

import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Stack;

import location.Location;
import location.ToroidalLocation;
import state.SegState;
import cell.Cell;
import cell.SegCell;
import grid.SquareGrid;
import grid.TriangleGrid;
import gui.CellSocietyGUI;

public class SegModel extends AbstractModel {
	
	private double mySimilarity = 0.7;
	private static String STATE_NAMES[] = {"Empty", "Blue", "Yellow"};

	public SegModel(CellSocietyGUI csGui) {
		super(csGui);
	}
	
	@Override
	public void setParameters(Map<String,String> map){
		// TODO Auto-generated method stub
		super.setParameters(map);
		if(map.containsKey("PERCENT_SIMILAR"))
			mySimilarity = Double.parseDouble(map.get("PERCENT_SIMILAR"));
	}
	
	@Override
	public void step(){
		Stack<Cell> exchangeList = getDissatisfiedCells();
		exchangeList.addAll(getVacentCells());
		Collections.shuffle(exchangeList);
		
		while(exchangeList.size()>=2){
			SegCell cell1 = (SegCell)exchangeList.pop();
			SegCell cell2 = (SegCell)exchangeList.pop();
			cell2.determineNextState(cell1.getState());
			cell1.determineNextState(cell2.getState());
		}
		myCells.forEach(cell->{cell.goToNextState();});
		++myStepNum;
		updateGraph();
	}
	
	private Stack<Cell> getDissatisfiedCells(){
		Stack<Cell> list = new Stack<>();
		myCells.forEach(cell->{
			if(!((SegCell)cell).isEmpty() && !((SegCell)cell).isSatisfied())
				list.add(cell);
		});
		return list;
	}
	
	private Stack<Cell> getVacentCells(){
		Stack<Cell> list = new Stack<>();
		myCells.forEach(cell->{
			if(((SegCell)cell).isEmpty())
				list.add(cell);
		});
		return list;
	}

	@Override
	public void initialize(Map<String, String> parameters, List<Map<String, String>> cells) {
		myCells.clear();
		setBasicConfig(parameters);
		mySimilarity = myParameters.containsKey("similarity")?Double.parseDouble(myParameters.get("PERCENT_SIMILAR")):mySimilarity;
		cells.forEach(map -> {
			int x = Integer.parseInt(map.get("x"));
			int y = Integer.parseInt(map.get("y"));
			int state = Integer.parseInt(map.get("state"));
			addCell(x,y,state);
		}); 
		if (myCells.size() < getWidth() * getHeight())
			System.err.println("Missing Cell Info!");
		myGrid = new SquareGrid(getWidth(), getHeight(), myCells);
		myGrid.setNeighbors();
		System.out.println(myGrid);
		setupGraph(STATE_NAMES);
	}

	@Override
	public void intialize(Map<String, String> parameters) {
		// TODO Auto-generated method stub
		setupGraph(STATE_NAMES);
	}
	
	private void addCell(int x,int y,int state){
		SegCell cell = new SegCell(new SegState(state), new Location(x,y, myWidth, getHeight()), myCSGUI);
		cell.setSimilarity(mySimilarity);
		myCells.add(cell);
	}

}
