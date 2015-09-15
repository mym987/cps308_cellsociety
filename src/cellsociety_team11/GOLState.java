package cellsociety_team11;

import javafx.scene.paint.Color;

public class GOLState extends State {
	
	GOLState(int s) {
		super(s);
		myColors[0] = Color.AQUAMARINE; //dead
		myColors[1] = Color.RED; //live
		this.setColor(s);
	}
}