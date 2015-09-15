package gui;

import cellsociety_team11.State;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class SquareCellGUI {

	private Color myColor;
	private int myPosX;
	private int myPosY;
	private Rectangle myRect;
	CellSocietyGUI myGUI;

	public SquareCellGUI(CellSocietyGUI CS, State initialState, int xCoord, int yCoord) {
		myRect = new Rectangle();
		myGUI = CS;
		setPosition(xCoord, yCoord);
		updateState(initialState);
		CS.addToScreen(myRect);
	}
	
	private void setPosition(int xCoord, int yCoord) {
		myPosX = xCoord;
		myPosY = yCoord;
		myRect.setX(xCoord);
		myRect.setY(yCoord);
	}
	
	private void setColor(Color color) {
		myColor = color;
		myRect.setFill(color);
	}
	
	public void updateState(State state) {
		setColor(state.getColor());
	}
}
