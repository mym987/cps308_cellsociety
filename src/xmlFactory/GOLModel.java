package xmlFactory;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

public class GOLModel extends Model {

	private final double PROB_LIVE = 0.5;

	GOLModel(int width, int height) {
		super("GOLModel", width, height);
	}

	@Override
	public List<Map<String, String>> getCells() {
		List<Map<String, String>> list = new ArrayList<>();
		Random rand = new Random(System.currentTimeMillis());
		for (int x = 0; x < getWidth(); x++) {
			for (int y = 0; y < getHeight(); y++) {
				int state = rand.nextDouble() > PROB_LIVE ? 0 : 1;
				list.add(createCell(x, y, state));
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
