package xmlFactory;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

public class FireModel extends Model {
	
	private static final int EMPTY_STATE = 0;
	private static final int TREE_STATE = 1;
	private static final int FIRE_STATE = 2;

	FireModel(int width, int height) {
		super("FireModel", width, height);
	}

	@Override
	public List<Map<String, String>> getCells() {
		int mat[][] = new int[getWidth()][getHeight()];
		int total = getWidth()*getHeight();
		
		for(int i=0;i<mat.length;i++){
			Arrays.fill(mat[i], TREE_STATE);
		}
		mat[getWidth()/2][getHeight()/2] = FIRE_STATE;
		for(int i = 0;i<getWidth();i++){
			mat[i][0] = EMPTY_STATE;
			mat[i][getHeight()-1] = EMPTY_STATE;
		}
		for(int i = 0;i<getHeight();i++){
			mat[0][i] = EMPTY_STATE;
			mat[getWidth()-1][i] = EMPTY_STATE;
		}
		
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
