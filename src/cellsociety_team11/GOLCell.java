package cellsociety_team11;

import java.util.ArrayList;

import javafx.scene.paint.Color;

public class GOLCell extends Cell{
	public static final double CELL_SIZE = 70;
	
	private Integer[] myStateInts = {0,1};  //0=dead, 1=live
	private ArrayList<GOLCell> myNeighborCells;

	GOLCell(State s, Location l) {
		super(s, l);
		
		this.setNeighborCells();

		double x1 = myLoc.getX()*CELL_SIZE;
		double y1 = myLoc.getY()*CELL_SIZE;
		double x2 = myLoc.getX()*CELL_SIZE + CELL_SIZE;
		double y2 = myLoc.getY()*CELL_SIZE + CELL_SIZE;


		this.getPoints().addAll(new Double[]{
				x1, y1,
				x2, y1,
				x2, y2,
				x1, y2
		});
		this.setFill(Color.WHITE);
		this.setStroke(Color.BLACK);
	}
	
	
	public ArrayList<GOLCell> getNeighborCells(){
		return myNeighborCells;
	}

	@Override
	public void setNeighborCells(){ 

		ArrayList<GOLCell> neighbors = new ArrayList<GOLCell>();
		for (int i = -1; i < 2; i++) {
			for (int j = -1; j < 2; j++) {
				if (i != 0 || j != 0) {
					Location neighborLoc = new Location(this.getLocation().getX() + i,
							this.getLocation().getY() + j);
					GOLCell neighborCell = GOLGrid.getCell(neighborLoc);
					if (neighborLoc.isValid()) {
						neighbors.add(neighborCell);
					}
				}
			}
		}
		myNeighborCells = neighbors;
	}

	
	@Override
	public State determineNextState() {
		State nextState;
		int numLiveNeighbors = 0;
		
		if (this.getState().getStateInt() == myStateInts[0]) {  //dead cell with exactly 3 neighbors comes to life
			for(Cell c : myNeighborCells) {
				if (c.getState().getStateInt() == myStateInts[1]) {
					numLiveNeighbors ++;
				}
			}
			
			if(numLiveNeighbors == 3) {
				nextState = new GOLState(myStateInts[1]);
			} else {
				nextState = new GOLState(myStateInts[0]);
			}
			
		} else if (this.getState().getStateInt() == myStateInts[1]) {
			for(Cell c : myNeighborCells) {
				if (c.getState().getStateInt() == myStateInts[1]) {
					numLiveNeighbors ++;
				}
			}
			
			if (numLiveNeighbors < 2) {
				nextState = new GOLState(myStateInts[0]);
			} else if (numLiveNeighbors == 2 || numLiveNeighbors == 3){
				nextState = new GOLState(myStateInts[1]);
			} else {
				nextState = new GOLState(myStateInts[0]);
			}
					
		} else {
			return null;
		}
		
		return nextState;
		
	}

}
