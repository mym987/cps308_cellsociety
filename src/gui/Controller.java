/**
 * @author Mike Ma
 */
package gui;

import java.io.File;
import java.io.IOException;
import java.util.Map;

import model.Model;
import model.SaxParser;
import model.XmlWriter;

public class Controller {

	private CSViewer myCsGui;
	private SaxParser myParser;
	private Model myModel;
	private Map<String,String> myModelConfigMap;
	private XmlWriter myWriter;
	
	public Controller(CSViewer csGui){
		myCsGui = csGui;
		myParser = new SaxParser(myCsGui);
		myWriter = new XmlWriter();
	}
	
	/**
	 * Step to the next frame in the animation
	 */
	public void step(){
		if(myModel!=null)
			myModel.step();
	}
	
	public void setOutline(boolean value){
		if(myModel!=null){
			if(value)
				myModel.getCells().forEach(cell->{cell.addOutlines();});
			else
				myModel.getCells().forEach(cell->{cell.removeOutlines();});
		}
	}
	
	/**
	 * Reset the grid to it's original state with the current xml
	 * @return True if createModel succeeded
	 */
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
	
	/**
	 * Save an XML file to the speficied directory
	 * @param dir The directory to save in
	 * @return The name of the xml file saved
	 * @throws Exception
	 */
	public String save(File dir) throws Exception{
		if(!dir.isDirectory() || !dir.canWrite()){
			throw new IOException("Current working dir is not writable.");
		}
		if(myModel==null){
			throw new Exception("Model does not exist!");
		}
		myWriter.init(myModel);
		return myWriter.createXml(dir);
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
