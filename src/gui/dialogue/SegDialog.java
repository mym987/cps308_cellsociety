package gui.dialogue;

public class SegDialog extends Dialog {
	public SegDialog() {
		super("Fire Spreading");
	}

	@Override
	protected void addTexts() {
		super.addTexts();
		addTextField("numBurning", "Num of Burning Cells:", "0", "Num of Burning Cells", "Cannot exceed width*height");
		addTextField("probCatchFire", "Probability of Catching Fire:", "0.5", "Probability of Catching Fire", "Double between 0 and 1");
	};

	@Override
	protected boolean validate() {
		try {
			int width = Integer.parseInt(myTexts.get("width").getText().trim());
			int height = Integer.parseInt(myTexts.get("height").getText().trim());
			int numBurning = Integer.parseInt(myTexts.get("numBurning").getText().trim());
			double probCatchFire = Double.parseDouble(myTexts.get("probCatchFire").getText().trim());
			return width <= 100 && width > 0 && height <= 100 && height > 0 
					&& numBurning>=0 && numBurning <= width*height
					&& probCatchFire <= 1 && probCatchFire >= 0;
		} catch (Exception e) {
			return false;
		}

	}

	@Override
	protected String getName() {
		return "FireModel";
	}

}