package gui.dialogue;

public class GOLDialog extends Dialog {

	public GOLDialog() {
		super("Game of Life");
	}

	@Override
	protected void addTexts() {
		super.addTexts();
		addTextField("percentLive", "% Live Cells:", "0", "Percentage of Live Cells", "Double between 0 and 1");
	};

	@Override
	protected boolean validate() {
		try {
			int width = Integer.parseInt(myTexts.get("width").getText().trim());
			int height = Integer.parseInt(myTexts.get("height").getText().trim());
			double percentLive = Double.parseDouble(myTexts.get("percentLive").getText().trim());
			return width <= 100 && width > 0 && height <= 100 && height > 0 && percentLive <= 1 && percentLive >= 0;
		} catch (Exception e) {
			return false;
		}
	}

	@Override
	protected String getName() {
		return "GOLModel";
	}

}
