package cellsociety_team11;

import java.io.File;

import gui.CellSocietyGUI;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.animation.Animation.Status;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Slider;
import javafx.scene.input.MouseEvent;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.util.Duration;
import model.Model;
import model.SaxParser;

public class CSManager {

	private CellSocietyGUI myCsGui;
	private SaxParser myParser;
	private Model myModel;
	
	public CSManager(CellSocietyGUI csGui){
		myCsGui = csGui;
		myParser = new SaxParser(myCsGui);
	}
	
	public void step(){
		if(myModel!=null)
			myModel.step();
	}
	
	public boolean reset(){
		if (myModel != null)
			myModel.clear();
		return createModel();
	}
	
	private boolean createModel() {
		myModel = myParser.getModel();
		return myModel != null;
	}
	
	public boolean loadXML(File file) throws Exception{
		if (myModel != null)
			myModel.clear();
		if (file != null && myParser.initialize(file)) {
			return createModel();
		}
		return false;
	}

	

}
