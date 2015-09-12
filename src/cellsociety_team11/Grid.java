package cellsociety_team11;

import javafx.scene.layout.GridPane;

public class Grid extends GridPane {

	public static int myNumRows = 5; //hardcoded and public rn
	public static int myNumCols = 5; //hardcoded and public rn

	private double myWidth;
	private double myHeight;

	private Location loc;
	private Cell cell;

	//	//different model cells
	//    private Cell[] myPossibleCells = { 
	//            new GameOfLifeCell(loc)
	//    };

	//	Group group = new Group();

	// private GridPane gp = new GridPane();

	public Grid() {
		myWidth = myNumCols*Cell.CELL_SIZE;
		myHeight = myNumRows*Cell.CELL_SIZE;
		createGrid();
		//getChildren().add(this);
	}
	
	public int getNumRows(){
		return myNumRows;
	}

	public int getNumCols(){
		return myNumCols;
	}
	
	protected void createGrid() {
		this.setLayoutX(50);
		this.setLayoutY(50);

		if (myWidth != this.getWidth() || myHeight != this.getHeight()) {
			//this.setWidth(myWidth);
			//this.setHeight(myHeight);

			for (int x = 0; x < myNumRows; x ++) {
				for (int y = 0; y < myNumCols; y ++) {
					loc = new Location(x,y);
					cell = new Cell(loc);
					this.add(cell, x, y);           	
				}
			}

		}
	}
}

