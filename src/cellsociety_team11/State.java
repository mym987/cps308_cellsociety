package cellsociety_team11;

import javafx.scene.paint.Color;

public abstract class State {
	protected Color myColor;
	protected int myStateInt;
	
	State(int s){
		myStateInt = s;
	}
	
	public abstract Color getColor();
	
	public int getStateInt(){
		return myStateInt;
	}
	
}
