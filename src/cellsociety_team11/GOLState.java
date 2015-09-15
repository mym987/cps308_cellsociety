package cellsociety_team11;

import javafx.scene.paint.Color;

public class GOLState extends State {

	GOLState(int s) {
		super(s);
	}

	@Override
	public Color getColor() {
		return Color.RED;
	}
	
}