package cellsociety_team11;

import javafx.scene.paint.Color;

public class GOLState extends State {
	
	Color myColor;
	
	GOLState(int s) {
		super(s);
	}

	@Override
	public Color getColor() {
		return Color.RED;
	}
}