package cellsociety_team11;

public class Location {
	
	private int myX, myY;
	private int myWidth, myHeight;
	
	Location(int x, int y, int width, int height){
		myX = x;
		myY = y;
		myWidth = width;
		myHeight = height;
	}
	
	public Location getLocation(int x, int y){
		Location loc = new Location(x,y,myWidth,myHeight);
		return loc.isValid()?loc:null;
	}
	
	public int getX(){
		return myX;
	}
	
	public int getY(){
		return myY;
	}
	
	public boolean isValid(){														// Karen... I just want to point out that if we were to turn
																					// in this method as it was, we would all fail the class,
//		if(getX() < 0 || getX() > myNumCols || getY() < 0 || getY() > myNumRows){	// according to what duvall said the other day.
//			return false;															// I'm leaving it here so you see it and don't make the same
//		}else{																		// mistake again.
//			return true;
//		}
		return (getX() >= 0 && getX() < myWidth && getY() >= 0 && getY() < myHeight);
	}
	
	public int getWidth(){
		return myWidth;
	}
	
	public int getNumCols(){
		return myHeight;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		long temp;
		temp = Double.doubleToLongBits(myX);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		temp = Double.doubleToLongBits(myY);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (obj == null || getClass() != obj.getClass())
			return false;
		Location other = (Location) obj;
		if (myX != other.myX || myY != other.myY)
			return false;
		return true;
	}
}
