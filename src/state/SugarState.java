package state;

import java.util.Random;
import javafx.scene.paint.Color;

public class SugarState extends State{
	private int myAgent;

	SugarState(int s) {
		super(s);
		Color[] colors = {Color.WHITE,Color.BISQUE, Color.ORANGE, Color.DARKORANGE, Color.ORANGERED, Color.BLACK}; 
		setAvailableColors(colors);
		this.setColor(s);
		
		Random randomGenerator = new Random();
		int randomInt = randomGenerator.nextInt(1); //0=no agent; 1=agent
		setAgent(randomInt);
		//drawCircle
	}
	
	private void setAgent(int a){
		myAgent = a;
		if(a == 1) {
			this.setColor(5);
		}
	}
	
	public int getAgent(){
		return myAgent;
	}

}
