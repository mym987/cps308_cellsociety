package xmlFactory;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

public class SegModel extends Model {
	
	private static final int EMPTY_STATE = 0;
	private static final int STATE_A = 1;
	private static final int STATE_B = 2;

	private int myNumA, myNumB;

	SegModel(int width, int height, double percentA, double percentB) {
		super("SegModel", width, height);
		int total = width * height;
		percentA = (percentA < 0) ? 0 : percentA;
		percentB = (percentB < 0) ? 0 : percentB;
		int numA = (int) (percentA * total);
		int numB = (int) (percentB * total);
		myNumA = numA <= total ? numA : total;
		myNumB = (numB <= total - myNumA) ? numB : total - myNumA;
	}

	@Override
	public List<Map<String, String>> getCells() {
		int mat[][] = new int[getWidth()][getHeight()];
		Random rand = new Random(System.currentTimeMillis());
		int total = getWidth()*getHeight();
		
		int i = 0;
		while(i < myNumA){
			int t = rand.nextInt(total);
			int x = t % getWidth(), y = t / getWidth();
			if(mat[x][y]==EMPTY_STATE){
				mat[x][y] = STATE_A;
				i++;
			}
		}
		i = 0;
		while(i < myNumB){
			int t = rand.nextInt(total);
			int x = t % getWidth(), y = t / getWidth();
			if(mat[x][y]==EMPTY_STATE){
				mat[x][y] = STATE_B;
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
	
	@Override
	public String toString(){
		return getClass().getName()+"_"+getWidth()+"_"+getHeight()+"_"+myNumA+"_"+myNumB;
	}

}
