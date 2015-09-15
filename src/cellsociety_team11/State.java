package cellsociety_team11;

import javafx.scene.paint.Color;

public abstract class State {
	protected Color[] myColors;
	protected int myStateInt;
	
	State(int s){
		myStateInt = s;
	}
	
	public abstract Color getColor(int s);
	
	public int getStateInt(){
		return myStateInt;
	}
	
	
}