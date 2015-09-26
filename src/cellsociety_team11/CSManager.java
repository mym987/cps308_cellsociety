package cellsociety_team11;

import java.io.File;
import java.util.Map;

import gui.CellSocietyGUI;
import model.Model;
import model.SaxParser;

public class CSManager {

	private CellSocietyGUI myCsGui;
	private SaxParser myParser;
	private Model myModel;
	private Map<String,String> myModelConfigMap;
	
	public CSManager(CellSocietyGUI csGui){
		myCsGui = csGui;
		myParser = new SaxParser(myCsGui);
	}
	
	public void step(){
		if(myModel!=null)
			myModel.step();
	}
	
	public boolean reset(){
		clear();
		if(myModelConfigMap == null)
			return createModelFromXML();
		else{
			try {
				return createModelFromMap(myModelConfigMap);
			} catch (Exception e) {
				return false;
			}
		}
	}

	
	private boolean createModelFromXML() {
		try {
			myModel = myParser.getModel();
			return myModel != null;
		} catch (Exception e) {
			return false;
		}
	}
	
	private boolean createModelFromMap(Map<String,String> map) throws Exception{
		String name = map.get("name");
		Model model = Model.getModel(name, myCsGui);
		if (model != null) {
			model.initialize(map);
			if(model.getCells()!=null){
				myModel=model;
				myModelConfigMap = map; //store the map for future reset()
				return true;
			}
		}
		return false;
	}
	
	public void clear(){
		if (myModel != null)
			myModel.clear();
	}
	
	public boolean loadXML(File file) throws Exception{
		clear();
		if (file != null && myParser.initialize(file)) {
			myModelConfigMap = null;
			return createModelFromXML();
		}
		return false;
	}
	
	public boolean loadModelConfig(Map<String,String> map)throws Exception{
		clear();
		if(!map.containsKey("name"))return false;
		return createModelFromMap(map);
	}
}
