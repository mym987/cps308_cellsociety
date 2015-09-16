package cellsociety_team11;

import java.util.ArrayList;

import javafx.scene.paint.Color;

public class PredCell extends Cell{
	public static final double CELL_SIZE = 70;
	
	private ArrayList<PredCell> myNeighborCells;

	private Integer[] myStateInts = {0,1,2};
	private int numChronon;
	private int sharkEnergy;

	PredCell(State s, Location l) {
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
		
		numChronon = 0;
		if (s.getStateInt() == myStateInts[1]) {
			sharkEnergy = 5;
		}
		visited = false;
		
	}
	
	public int getNumChronon(){
		return numChronon;
	}
	
	public void setNumChronon(int x){
		numChronon = x;
	}
	
	public int getSharkEnergy(){
		return sharkEnergy;
	}
	
	public void setSharkEnergy(int x){
		sharkEnergy = x;
	}
	
//	public boolean getVisited(){
//		return visited;
//	}
//	
//	public void setVisited(boolean b){
//		visited = b;
//	}
	
	@Override
	public void setNeighborCells() {
		ArrayList<PredCell> neighbors = new ArrayList<PredCell>();
		for (int i = -1; i < 2; i++) {
			for (int j = -1; j < 2; j++) {
				if (i != 0 || j != 0) {
					Location neighborLoc = new Location(this.getLocation().getX() + i,
							this.getLocation().getY() + j);
					//how would you get the state of this neighboring cell?
					PredCell neighborCell = PredGrid.getCell(neighborLoc);
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
		// TODO Auto-generated method stub
		State nextState;
		int numLiveNeighbors = 0;
		
		//problem: if a fish moves to an empty cell that is after this cell in the myCells map,
		//it will reevaluate that cell again //fixed now using visited boolean
		if (this.getState().getStateInt() == myStateInts[2] && this.getVisited()==false) {  //fish moving to empty cell
			for(PredCell pc : myNeighborCells) {
				if (pc.getState().getStateInt() == myStateInts[0]) {
					//Location l = c.getLocation();  //move to this location
					State s = new PredState(myStateInts[2]); //change to fish state
					//make new pred cell?
					//Cell predCell = new PredCell(s,l);
					pc.setState(s);
					pc.setVisited(true);
					int currNumChronon = pc.getNumChronon();
					pc.setNumChronon(currNumChronon++);
					
					State emptyState = new PredState(myStateInts[0]); //the current cell will be empty
					this.setState(emptyState);
					this.numChronon ++; //fix
				}
			}
			
//			if(numLiveNeighbors == 3) {
//				nextState = new GOLState(myStateInts[1]);
//				//change color
//			} else {
//				nextState = new GOLState(myStateInts[0]);
//				//set color
//			}
		//need to work on still	
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
