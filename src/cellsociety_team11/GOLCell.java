package cellsociety_team11;

public class GOLCell extends Cell{
	public static final double CELL_SIZE = 70;
	
	private Integer[] myStateInts = {0,1};  //0=dead, 1=live 
											// Maybe this should be an ENUM to make it more readable?

	GOLCell(State s, Location l) {
		super(s, l);

//		double x1 = myLoc.getX()*CELL_SIZE;
//		double y1 = myLoc.getY()*CELL_SIZE;
//		double x2 = myLoc.getX()*CELL_SIZE + CELL_SIZE;
//		double y2 = myLoc.getY()*CELL_SIZE + CELL_SIZE;
//
//
//		this.getPoints().addAll(new Double[]{
//				x1, y1,
//				x2, y1,
//				x2, y2,
//				x1, y2
//		});
//		this.setFill(Color.WHITE);		// These should be methods in CellGUI
//		this.setStroke(Color.BLACK);
	}
	
	@Override
	public void determineNextState() { // This should be split up into smaller functions.
		GOLState nextState = new GOLState(0);
		int numLiveNeighbors = getNeighborsInState(nextState).size();
		
		if(numLiveNeighbors > 3 || numLiveNeighbors < 2) {
			myState.setNextState(myStateInts[0]);
		} else if (numLiveNeighbors == 3) {
			myState.setNextState(myStateInts[1]);
		}
	}
}
