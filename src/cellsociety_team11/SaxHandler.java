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
	private List<Map<String, String>> myCells;
	
	private String currentTag = null;
	private String currentValue = null;
	
	private CellSocietyGUI myCSGUI;
	
	public SaxHandler(CellSocietyGUI CSGUI) {
		myNodeName = "Model";
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
		System.out.println("--startElement()--" + qName);
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
				System.out.println("-----" + currentTag + " " + currentValue);
			}
			currentTag = null;
			currentValue = null;
		}
	}

	@Override
	public void endElement(String uri, String localName, String qName) throws SAXException {
		System.out.println("--endElement()--" + qName);
		if (qName.equals("Model")) {
			String name = attributeMap.get("Name");
			int rows = Integer.parseInt(attributeMap.get("Rows"));
			int columns = Integer.parseInt(attributeMap.get("Columns"));
			try {
				Constructor<?> c = Class.forName(name).getConstructor(Integer.TYPE, Integer.TYPE);
				myModel = (Model) c.newInstance(rows, columns);
			} catch (NoSuchMethodException | SecurityException | ClassNotFoundException | InstantiationException
					| IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {
				e.printStackTrace();
			}
			myNodeName = "Cell";
			myCells = new ArrayList<>(rows*columns);
		}
		else if (qName.equals("Cell")) {
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
