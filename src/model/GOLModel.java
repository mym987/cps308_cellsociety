package model;

import java.util.List;
import java.util.Map;

import location.ToroidalLocation;
import state.GOLState;
import cell.Cell;
import cell.GOLCell;
import grid.TriangleGrid;
import gui.CellSocietyGUI;

public class GOLModel extends AbstractModel {
	
	private static final double DEFAULT_PERCENT_LIVE_CELLS = 0.5;
	private static final int DEAD_STATE = 0;
	private static final int LIVE_STATE = 1;
	private static String STATE_NAMES[] = {"Dead", "Alive"};
	
	public GOLModel(CellSocietyGUI csGui) {
		super(csGui);
	}

	@Override
	public void setParameters(Map<String,String> parameters){
		initialize(parameters);
	}
	
	@Override
	public void initialize(Map<String, String> parameters, List<Map<String, String>> cells) {
		myCells.clear();
		setBasicConfig(parameters);
		cells.forEach(map -> {
			int x = Integer.parseInt(map.get("x"));
			int y = Integer.parseInt(map.get("y"));
			int state = Integer.parseInt(map.get("state"));
			addCell(x,y,state);
		});
		if(myCells.size()<getWidth()*getHeight())
			System.err.println("Missing Cell Info!");
		myGrid = new TriangleGrid(getWidth(), getHeight(), myCells);
		myGrid.setNeighbors();
		setupGraph(STATE_NAMES);
	}

	@Override
	public void initialize(Map<String, String> parameters) {
		setBasicConfig(parameters);
		double percentLive = DEFAULT_PERCENT_LIVE_CELLS;
		if(myParameters.containsKey("percentLive")){
			double tmp = Double.parseDouble(myParameters.get("percentLive"));
			if(tmp>=0 && tmp<=1)
				percentLive = tmp;
		}
		int total = myWidth*myHeight;
		int numLiveCells = (int)(total*percentLive);
		int mat[][] = new int[getWidth()][getHeight()];
		
		int i = 0;
		while(i < numLiveCells){
			int t = myRandom.nextInt(total);
			int x = t % getWidth(), y = t / getWidth();
			if(mat[x][y]==DEAD_STATE){
				mat[x][y] = LIVE_STATE;
				i++;
			}
		}
		for (int x = 0; x < mat.length; x++)
			for (int y = 0; y < mat[x].length; y++)
				addCell(x,y,mat[x][y]);	
		myGrid = new TriangleGrid(getWidth(), getHeight(), myCells);
		myGrid.setNeighbors();
		setupGraph(STATE_NAMES);
	}
	
	private void addCell(int x,int y,int state){
		Cell cell = new GOLCell(new GOLState(state), new ToroidalLocation(x,y, myWidth, getHeight()), myCSGUI);
		myCells.add(cell);
	}
}
