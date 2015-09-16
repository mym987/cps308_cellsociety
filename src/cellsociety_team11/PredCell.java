package cellsociety_team11;

import javafx.scene.paint.Color;

public class PredCell extends Cell{
	public static final double CELL_SIZE = 70;

	private Integer[] myStateInts = {0,1,2};
	private int numChronon;
	private int sharkEnergy;
	
	private static final int CHRONON_REPR = 5;

	PredCell(State s, Location l) {
		super(s, l);
		
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
	public State determineNextState() {
		State nextState;

		//problem: if a fish moves to an empty cell that is after this cell in the myCells map,
		//it will reevaluate that cell again //fixed now using visited boolean
		if (this.getState().getStateInt() == myStateInts[2] && this.getVisited()==false) {  //fish moving to empty cell
			for(Cell c : myNeighborCells) {
				PredCell pc = (PredCell) c;
				if (pc.getState().getStateInt() == myStateInts[0]) {
					State s = new PredState(myStateInts[2]); //change to fish state
					pc.setState(s);
					pc.setVisited(true);
					int currNumChronon = pc.getNumChronon();
					pc.setNumChronon(currNumChronon++);

					if (pc.getNumChronon() >= CHRONON_REPR) {
						State fishState = new PredState(myStateInts[2]); //the current cell will be a fish
						this.setNumChronon(0); //reproductive state reset
						//this.setState(fishState);
						nextState = fishState;
						return nextState;
					} else {
						State emptyState = new PredState(myStateInts[0]); //the current cell will be empty
						this.setNumChronon(0);
						//this.setState(emptyState);
						nextState = emptyState;
						return nextState;
					}
				}
			}
		} else if (this.getState().getStateInt() == myStateInts[1] && this.getVisited()==false) { //shark
			for(Cell c : myNeighborCells) {
				PredCell pc = (PredCell) c;
				if (pc.getState().getStateInt() == myStateInts[2]) { //if one of neighbors is fish
					State s = new PredState(myStateInts[1]); //change to shark state
					pc.setState(s);

					int currSharkEnergy = pc.getSharkEnergy();
					pc.setSharkEnergy(currSharkEnergy++);
					
					int currNumChronon = pc.getNumChronon();
					pc.setNumChronon(currNumChronon++);
					pc.setVisited(true);

					if (pc.getNumChronon() >= CHRONON_REPR) {
						State sharkState = new PredState(myStateInts[1]); //the current cell will be a shark
						this.setNumChronon(0); //reproductive state reset
						//this.setState(sharkState);
						nextState = sharkState;
						return nextState;
					} else {
						State emptyState = new PredState(myStateInts[0]); //the current cell will be empty
						this.setNumChronon(0);
						//this.setState(emptyState);
						nextState = emptyState;
						return nextState;
					}

				}
			}

			for(Cell c : myNeighborCells) {
				PredCell pc = (PredCell) c;
				if (pc.getState().getStateInt() == myStateInts[0]) { //if one of neighbors is empty		
					State s = new PredState(myStateInts[1]); //change to shark state
					pc.setState(s);
					
					int currSharkEnergy = pc.getSharkEnergy();
					pc.setSharkEnergy(currSharkEnergy--);
					
					int currNumChronon = pc.getNumChronon();
					pc.setNumChronon(currNumChronon++);
					pc.setVisited(true);

					if(pc.getSharkEnergy() <=0 ) {  //checking shark energy
						State emptyState = new PredState(myStateInts[0]); //the current cell will be empty
						this.setNumChronon(0);
						//this.setState(emptyState);
						return emptyState;
					}


					if (pc.getNumChronon() >= CHRONON_REPR) {
						State sharkState = new PredState(myStateInts[1]); //the current cell will be a shark
						this.setNumChronon(0); //reproductive state reset
						//this.setState(sharkState);
						return sharkState;
					} else {
						State emptyState = new PredState(myStateInts[0]); //the current cell will be empty
						this.setNumChronon(0);
						//this.setState(emptyState);
						return emptyState;
					}

				}
			}

		}
		State emptyState = new PredState(myStateInts[0]);
		return emptyState;

	}

}
