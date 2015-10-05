/**
 * @author Mike Ma
 */
package model;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Map;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.xml.sax.SAXException;

import gui.CSViewer;

public class SaxParser {

	private CSViewer myCsGui;
	private Map<String, String> myModelConfig;
	private List<Map<String, String>> myCells;

	public SaxParser(CSViewer csGui) {
		myCsGui = csGui;
	}

	public boolean initialize(File file) throws SAXException, ParserConfigurationException, IOException {
		SAXParserFactory parserFactory = SAXParserFactory.newInstance();
		SAXParser parser = parserFactory.newSAXParser();
		SaxHandler myHandler = new SaxHandler();
		parser.parse(file, myHandler);
		myModelConfig = myHandler.getModelConfig();
		myCells = myHandler.getCells();
		return myModelConfig != null && myCells != null;
	}

	public Model getModel() throws Exception {
		if (myModelConfig == null || myCells == null)
			return null;
		String name = myModelConfig.get("name");
		Model model = Model.getModel(name, myCsGui);
		if (model != null) {
			model.initialize(myModelConfig, myCells);
		}
		return model;
	}

}