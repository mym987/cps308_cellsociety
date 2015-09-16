package cellsociety_team11;

import javafx.scene.paint.Color;

public class FireState extends State {

	FireState(int s) {
		super(s);
		
		myColors[0] = Color.GREEN; //live tree
		myColors[1] = Color.YELLOW; //burning tree
		myColors[2] = Color.BROWN; //dead tree
		this.setColor(s);
	}

}
