package cellsociety_team11;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import gui.CellSocietyGUI;

public class SaxHandler extends DefaultHandler {

	private Model myModel;
	private String myNodeName;
	private Map<String, String> attributeMap = null;
	private List<Map<String, String>> myCells = null;

	private String currentTag = null;
	private String currentValue = null;
	
	private CellSocietyGUI myCSGUI;
	
	public SaxHandler(CellSocietyGUI CSGUI) {
		myNodeName = "model";
		myCSGUI = CSGUI;
	}

	public Model getModel() {
		return myModel;
	}

	@Override
	public void startDocument() throws SAXException {
		System.out.println("--startDocument()--");
	}

	@Override
	public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
		//System.out.println("--startElement()--" + qName);
		if (qName.equals(myNodeName)) {
			attributeMap = new HashMap<String, String>();
		}

		if (attributes != null && attributeMap != null) {
			for (int i = 0; i < attributes.getLength(); i++) {
				attributeMap.put(attributes.getQName(i), attributes.getValue(i));
			}
		}
		currentTag = qName;
	}

	@Override
	public void characters(char[] ch, int start, int length) throws SAXException {
		if (currentTag != null && attributeMap != null) {
			currentValue = new String(ch, start, length);
			if (currentValue != null && !currentValue.trim().equals("") && !currentValue.trim().equals("\n")) {
				attributeMap.put(currentTag, currentValue);
				//System.out.println("-----" + currentTag + " " + currentValue);
			}
			currentTag = null;
			currentValue = null;
		}
	}

	private Model createModel(String name, int r, int c) {
		name = getClass().getPackage().getName() + "." + name;
		try {
			Class[] types = { Integer.TYPE, Integer.TYPE, CellSocietyGUI.class};
			Constructor constructor = Class.forName(name).getDeclaredConstructor(types);
			return (Model) constructor.newInstance(r, c, myCSGUI);
		} catch (ClassNotFoundException | NoSuchMethodException | SecurityException | InstantiationException
				| IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public void endElement(String uri, String localName, String qName) throws SAXException {
		//System.out.println("--endElement()--" + qName);
		if (qName.equals("model")) {
			String name = attributeMap.get("name");
			int rows = Integer.parseInt(attributeMap.get("rows"));
			int columns = Integer.parseInt(attributeMap.get("columns"));
			myModel = createModel(name, rows, columns);
			myNodeName = "cell";
			myCells = new ArrayList<>(rows * columns);
		} else if (qName.equals("cell")) {
			myCells.add(attributeMap);
			attributeMap = null;
		}
	}

	@Override
	public void endDocument() throws SAXException {
		System.out.println("--endDocument()--");
		super.endDocument();
		myModel.buildGrid(myCells, myCSGUI);
	}
}
