package cellsociety_team11;

import javafx.scene.paint.Color;

public abstract class State {
	protected Color[] myColors;
	protected Color myColor;
	protected int myStateInt = 0;
	protected int myNextStateInt = 0;

	State(int state){ //This will need to take CellSocietyGUI as a parameter so it can create a SquareCellGUI.
		myStateInt = state;
		this.setColor(state);
	}
	
	public Color getColor() {
		return myColor;
	}
	
	public void setColor(int s) {
		myColor = myColors[s];
	}
	
	public int getStateInt(){
		return myStateInt;
	}
	
	public void setNextState(int nextStateInt) {
		myNextStateInt = nextStateInt;
	}
	
	public void goToNextState() {
		myStateInt = myNextStateInt;
		setColor(myStateInt);
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + myStateInt;
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
		State other = (State) obj;
		if (myStateInt != other.myStateInt)
			return false;
		return true;
	}	
}
