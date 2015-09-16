package cellsociety_team11;

import javafx.scene.paint.Color;

public abstract class State {
	protected Color[] myColors;
	protected Color myColor;
	protected int myStateInt;		// State maybe instead of int?
	
	State(int s){
		myStateInt = s;
		this.setColor(s);
	}
	
//	public Color getColor() {
//		return myColor;
//	}
//	
	public void setColor(int s) {
		myColor = myColors[s];
	}
	
	public int getStateInt(){
		return myStateInt;		// We might want to return a State instead of an int
	}							// Or make this abstract
	
}