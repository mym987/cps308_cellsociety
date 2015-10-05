/**
 * @author Mike Ma
 */
package xmlFactory;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

public class AntModel extends Model {
	
	private static final int EMPTY_STATE = 0;
	private static final int NEST_STATE = 1;
	private static final int FOOD_SOURCE_STATE = 2;
	private static final int HOME_PHEROMONE_STATE = 3;
	private static final int FOOD_PHEROMONE_STATE = 4;
	
	private static final int ANT_STATE = 5;
	
	private int myNumAnts;

	AntModel(int width, int height, double percentAnts) {
		super("AntModel", width, height);
		int total = width * height;
		percentAnts = (percentAnts < 0) ? 0 : percentAnts;
		int numAnts = (int) (percentAnts * total);
		myNumAnts = numAnts <= total ? numAnts : total;
		setModelParameters(0.25,0.25);
		
	}
	
	public void setModelParameters(double evapRate, double diffusionRate){
		addModelParameter("evapRate", evapRate);
		addModelParameter("diffusionRate", diffusionRate);
	}

	private void fillCells(int[][] mat, int limit, int state){
		Random rand = new Random(System.currentTimeMillis());
		int total = mat.length*mat[0].length;
		
		double percentNestWidth = 0.3;
		int nestWidth = (int) (getWidth()*percentNestWidth);
		
		int t = rand.nextInt(total);
		int x = t % getWidth(), y = t / getWidth();
		if(mat[x][y]==EMPTY_STATE){
			mat[x][y] = NEST_STATE;
		}
		
		int i=0;
		while(i < limit){
			
			for(int p=0; p<=nestWidth; p++){

				if ((x+p) < getWidth()){
					if(mat[x+p][y]==EMPTY_STATE){
						mat[x+p][y] = NEST_STATE;
						i++;
					}
				} else {
					break;
				}
			}
			x = t % getWidth();
			if (y < getHeight()-1){y++;}
			else{ break;}
		}
		
		
		
		double percentFood = 0.1;
		int numFood = (int) (total*percentFood);
		
		int j = 0;
		while(j < numFood){
			int t1 = rand.nextInt(total);
			int x1 = t1 % getWidth(), y1 = t1 / getWidth();
			if(mat[x1][y1]==EMPTY_STATE){
				mat[x1][y1] = FOOD_SOURCE_STATE;
				j++;
			}
		}
		
	}
	
	@Override
	public List<Map<String, String>> getCells() {
		int mat[][] = new int[getWidth()][getHeight()];		
		fillCells(mat,myNumAnts,NEST_STATE);

		List<Map<String, String>> list = new ArrayList<>();
		
		for (int x = 0; x < mat.length; x++) {
			for (int y = 0; y < mat[x].length; y++) {
				list.add(createCell(x, y, mat[x][y]));
			}
		}
		return list;
	}


	private Map<String, String> createCell(int x, int y, int state) {
		Map<String, String> map = new HashMap<>();
		map.put("x", Integer.toString(x));
		map.put("y", Integer.toString(y));
		map.put("state", Integer.toString(state));
		return map;
	}
	
	@Override
	public String toString(){
		return getClass().getSimpleName()+"_"+getWidth()+"_"+getHeight();
	}

}
