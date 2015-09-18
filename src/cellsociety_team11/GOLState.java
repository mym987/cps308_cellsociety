package cellsociety_team11;

import javafx.scene.paint.Color;

public class GOLState extends State {
	
	GOLState(int s) {
		super(s);
		Color[] colors = {Color.AQUAMARINE,Color.RED}; //0=dead,1=live
		setAvailableColors(colors);
		setColor(s);
	}
}