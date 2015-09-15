package gui;

import cellsociety_team11.State;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class GOLCellGUI {

	private Color myColor;
	private int myPosX;
	private int myPosY;
	private Rectangle myRect;
	CellSocietyGUI myGUI;

	public GOLCellGUI(CellSocietyGUI CS, State initialState, int xCoord, int yCoord) {
		myRect = new Rectangle();
		myGUI = CS;
		setX(xCoord, yCoord);
		setColor(initialState.getColor());
		CS.addToScreen(myRect);
	}
	
	private void setX(int xCoord, int yCoord) {
		myPosX = xCoord;
		myPosY = yCoord;
		myRect.setX(xCoord);
		myRect.setY(yCoord);
	}
	
	private void setColor(Color color) {
		myColor = color;
		myRect.setFill(color);
	}

}
