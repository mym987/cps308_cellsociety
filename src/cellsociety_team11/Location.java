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
	
	public boolean isValid(){														// Karen... I just want to point out that if we were to turn
																					// in this method as it was, we would all fail the class,
//		if(getX() < 0 || getX() > myNumCols || getY() < 0 || getY() > myNumRows){	// according to what duvall said the other day.
//			return false;															// I'm leaving it here so you see it and don't make the same
//		}else{																		// mistake again.
//			return true;
//		}
		
		return (getX() >= 0 && getX() < myNumCols && getY() >= 0 && getY() < myNumRows);
	}
	
	public int getNumRows(){
		return myNumRows;
	}
	
	public int getNumCols(){
		return myNumCols;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
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

	/* (non-Javadoc)
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Location other = (Location) obj;
		if (Double.doubleToLongBits(myX) != Double.doubleToLongBits(other.myX))
			return false;
		if (Double.doubleToLongBits(myY) != Double.doubleToLongBits(other.myY))
			return false;
		return true;
	}
}
