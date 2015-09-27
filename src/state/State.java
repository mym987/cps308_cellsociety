package state;

import javafx.scene.paint.Color;

public interface State {
	
	public Color getColor();
	
	public double getOpacity();
	
	public int getStateInt();
	
	public void setNextState(int nextStateInt);
	
	public void setNextState(State s);
	
	public void goToNextState();

}
