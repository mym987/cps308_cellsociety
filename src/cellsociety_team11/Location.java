package cellsociety_team11;

public class Location {
	
	private double myX;
	private double myY;
	private int myNumRows, myNumCols;
	
	Location(double x, double y, int numRows, int numCols){
		myX = x;
		myY = y;
		myNumRows = numRows;
		myNumCols = numCols;
	}
	
	public double getX(){
		return myX;
	}
	
	public double getY(){
		return myY;
	}
	
	public boolean isValid(){	
		if(getX() < 0 || getX() > myNumCols || getY() < 0 || getY() > myNumRows){
			return false;
		}else{
			return true;
		}
	}
	
	public int getNumRows(){
		return myNumRows;
	}
	
	public int getNumCols(){
		return myNumCols;
	}
}
