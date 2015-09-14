package cellsociety_team11;

import java.util.ArrayList;
import javafx.scene.paint.Color;
import javafx.scene.shape.Polygon;

public abstract class Cell extends Polygon{

	private State myState;
	private ArrayList<? extends Cell> myNeighborCells;

	Cell(){

	}

	//	public double getSize(){
	//		return CELL_SIZE;
	//	}

	public State getState(){
		return myState;
	}

	public void setState(State s){
		myState = s;
	}

	public ArrayList<? extends Cell> getNeighborCells(){
		return myNeighborCells;
	}

	public void setNeighborCells(ArrayList<? extends Cell> neighbors){ //
		myNeighborCells = neighbors;
		
//		Location currLoc = getLocation();


	}

//	public abstract State determineNextState();

	public void setNextState(){

	}

	public void goToNextState(){

	}

	public State determineNextState() {
		// TODO Auto-generated method stub
		return null;
	}
}
