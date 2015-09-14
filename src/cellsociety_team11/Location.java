package cellsociety_team11;

public class Location {
	
	private double myX;
	private double myY;
	
	Location(double x, double y){
		myX = x;
		myY = y;
	}
	
	public double getX(){
		return myX;
	}
	
	public double getY(){
		return myY;
	}
	
	public boolean isValid(){ // This method is very specific to squareGrid.  Let's try to make it more general
		if(getX() < 0 || getX() > SquareGrid.myNumCols || getY() < 0 || getY() > SquareGrid.myNumCols){
			return false;
		}else{
			return true;
		}
	}
}
