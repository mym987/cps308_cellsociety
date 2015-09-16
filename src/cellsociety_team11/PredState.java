package cellsociety_team11;

import javafx.scene.paint.Color;

public class PredState extends State{

	PredState(int s) {
		super(s);
		myColors[0] = Color.WHITE; ///Empty
		myColors[1] = Color.RED; //Shark
		myColors[2] = Color.AQUAMARINE; //Fish
		this.setColor(s);
	}

}
