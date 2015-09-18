package cellsociety_team11;

import javafx.scene.paint.Color;

public class FireState extends State {
	
	public FireState(int s) {
		super(s);
		Color[] colors = {Color.GREEN,Color.YELLOW,Color.BROWN}; //Live,burning,dead tree
		this.setAvailableColors(colors);
		this.setColor(s);
	}

}
