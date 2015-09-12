package cellsociety_team11;

import java.util.ArrayList;
import javafx.scene.paint.Color;
import javafx.scene.shape.Polygon;

public class Cell extends Polygon{
	public static final double CELL_SIZE = 70;

	private State myState;
	private Location myLoc;
	private ArrayList<Cell> myNeighborCells;

	Cell(Location l){
		myLoc = l;

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

	//	public double getSize(){
	//		return CELL_SIZE;
	//	}

	public State getState(){
		return myState;
	}

	public void setState(State s){
		myState = s;
	}

	public Location getLocation(){
		return myLoc;
	}

	public ArrayList<Cell> getNeighborCells(){
		return myNeighborCells;
	}

	public void setNeighborCells(){ //
		Location currLoc = getLocation();

		for(int i = -1; i < 2; i++){
			for(int j = -1; j < 2; j++){
				if(i != 0 && j != 0){
					Location neighborLoc = new Location(currLoc.getX() + i , currLoc.getY() + j);
					if(neighborLoc.isValid()){
						Cell neighbor = new Cell(neighborLoc);
						myNeighborCells.add(neighbor);
					}
				}
			}
		}
	}

//	public abstract State determineNextState();

	public void setNextState(){

	}

	public void goToNextState(){

	}
}
