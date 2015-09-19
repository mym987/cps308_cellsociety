package xmlFactory;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

public class GOLModel extends Model {
	
	private final int myNumLiveCells;
	private static final int DEAD_STATE = 0;
	private static final int LIVE_STATE = 1;

	GOLModel(int width, int height, double percentLive) {
		super("GOLModel", width, height);
		percentLive = (percentLive<0)?0:percentLive;
		percentLive = (percentLive>1)?1:percentLive;
		myNumLiveCells = (int)(width*height*percentLive);
	}

	@Override
	public List<Map<String, String>> getCells() {
		int mat[][] = new int[getWidth()][getHeight()];
		Random rand = new Random(System.currentTimeMillis());
		int total = getWidth()*getHeight();
		
		int i = 0;
		while(i < myNumLiveCells){
			int t = rand.nextInt(total);
			int x = t % getWidth(), y = t / getWidth();
			if(mat[x][y]==DEAD_STATE){
				mat[x][y] = LIVE_STATE;
				i++;
			}
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

	private void getCells(List<Map<String, String>> list) {
		list.clear();
		int mat[][] = { 
				{ 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 },
				{ 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 },
				{ 0, 1, 1, 0, 0, 0, 0, 0, 0, 0 },
				{ 0, 1, 1, 0, 0, 0, 0, 0, 0, 0 }, 
				{ 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 },
				{ 0, 0, 0, 0, 0, 1, 1, 1, 0, 0 }, 
				{ 0, 1, 0, 1, 0, 0, 0, 0, 0, 0 }, 
				{ 0, 1, 0, 0, 0, 0, 0, 0, 0, 0 },
				{ 0, 1, 1, 0, 0, 0, 0, 0, 0, 0 }, 
				{ 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 } };
		for (int y = 0; y < mat.length; y++) {
			for (int x = 0; x < mat[y].length; x++) {
				Map<String, String> map = new HashMap<>();
				map.put("y", Integer.toString(y));
				map.put("x", Integer.toString(x));
				map.put("state", Integer.toString(mat[y][x]));
				list.add(map);
			}
		}
	}

}
