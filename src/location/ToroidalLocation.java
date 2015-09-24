package location;

public class ToroidalLocation extends Location {

	public ToroidalLocation(int x, int y, int width, int height) {
		super(x, y, width, height);
	}
	
	@Override
	public Location getLocation(int x, int y){
		ToroidalLocation loc = new ToroidalLocation(Math.floorMod(x, myWidth), 
				Math.floorMod(y, myHeight), myWidth, myHeight);
		return loc;
	}

}
