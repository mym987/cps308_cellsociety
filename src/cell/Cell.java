package cell;

import java.util.List;

import location.Location;
import state.State;

public interface Cell {

	public State getState();

	public boolean isInState(State s);

	public void setState(State s);

	public Location getLocation();

	public void setNeighborCells(List<Cell> neighbors);
	
	public abstract void determineNextState();

	public void goToNextState();

	public void remove();

	public void incrementState();

	public void removeOutlines();

	public void addOutlines();
}
