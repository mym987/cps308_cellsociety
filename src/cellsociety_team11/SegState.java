package cellsociety_team11;

import javafx.scene.paint.Color;

public class SegState extends State {

	SegState(int s) {
		super(s);
		
		myColors[0] = Color.WHITE; //empty
		myColors[1] = Color.BLUE; //group1
		myColors[2] = Color.YELLOW; //group2
		this.setColor(s);
	}

}
