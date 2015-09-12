package cellsociety_team11;

import javafx.scene.paint.Color;

public class GameOfLifeCell extends Cell{
	
	private Integer[] states = {0,1};
	private Color[] symbols = {Color.AQUAMARINE , Color.RED};

	GameOfLifeCell(Location l) {
		super(l);
	}

	@Override
	public State determineNextState() {
		// TODO Auto-generated method stub
		this.getState();
		
		return null;
	}

}
