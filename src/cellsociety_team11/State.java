package cellsociety_team11;

import javafx.scene.paint.Color;

public abstract class State {
	protected Color[] myColors;
	protected Color myColor;
	protected int myStateInt;
	
	State(int s){
		myStateInt = s;
		this.setColor(s);
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
	
}
