package model;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Map;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.xml.sax.SAXException;

import gui.CellSocietyGUI;

public class SaxParser {
	
	private CellSocietyGUI myCsGui;
	private Map<String, String> myModelConfig;
	private List<Map<String, String>> myCells;
	
	public SaxParser(CellSocietyGUI csGui){
		myCsGui = csGui;
	}
	
	public void initialize(File file){
		try {
            SAXParserFactory parserFactory = SAXParserFactory.newInstance();
            SAXParser parser=parserFactory.newSAXParser();
            SaxHandler myHandler=new SaxHandler();
            parser.parse(file, myHandler);
            myModelConfig = myHandler.getModelConfig();
            myCells = myHandler.getCells();
            
        } catch (SAXException e) {
            e.printStackTrace();
        } catch (ParserConfigurationException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public Model getModel(){
		if(myModelConfig==null || myCells == null)
			return null;
		String name = myModelConfig.get("name");
		Model model = Model.getModel(name, myCsGui);
		if (model != null) {
			model.initialize(myModelConfig, myCells);
			System.out.println("Model created successfully!");
		}
		return model;
	}

}