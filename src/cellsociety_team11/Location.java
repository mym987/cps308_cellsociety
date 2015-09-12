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
	
	public boolean isValid(){
		if(getX() < 0 || getX() > Grid.myNumCols || getY() < 0 || getY() > Grid.myNumCols){
			return false;
		}else{
			return true;
		}
	}
}
