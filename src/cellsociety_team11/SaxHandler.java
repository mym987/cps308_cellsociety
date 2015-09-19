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
	private Map<String, String> myAttributeMap = null;
	private List<Map<String, String>> myCells = null;

	private String myCurrentTag = null;
	private String myCurrentValue = null;
	
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
		//System.out.println("--startDocument()--");
	}

	@Override
	public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
		//System.out.println("--startElement()--" + qName);
		if (qName.equals(myNodeName)) {
			myAttributeMap = new HashMap<String, String>();
		}

		if (attributes != null && myAttributeMap != null) {
			for (int i = 0; i < attributes.getLength(); i++) {
				myAttributeMap.put(attributes.getQName(i), attributes.getValue(i));
			}
		}
		myCurrentTag = qName;
	}

	@Override
	public void characters(char[] ch, int start, int length) throws SAXException {
		if (myCurrentTag != null && myAttributeMap != null) {
			myCurrentValue = new String(ch, start, length);
			if (myCurrentValue != null && !myCurrentValue.trim().equals("") && !myCurrentValue.trim().equals("\n")) {
				myAttributeMap.put(myCurrentTag, myCurrentValue);
				//System.out.println("-----" + currentTag + " " + currentValue);
			}
			myCurrentTag = null;
			myCurrentValue = null;
		}
	}

	private Model createModel(String name, int width, int height) {
		name = getClass().getPackage().getName() + "." + name;
		try {
			Class[] types = { Integer.TYPE, Integer.TYPE, CellSocietyGUI.class};
			Constructor constructor = Class.forName(name).getDeclaredConstructor(types);
			return (Model) constructor.newInstance(width, height, myCSGUI);
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
			String name = myAttributeMap.get("name");
			int width = Integer.parseInt(myAttributeMap.get("width"));
			int height = Integer.parseInt(myAttributeMap.get("height"));
			myModel = createModel(name, width, height);
			myNodeName = "cell";
			myCells = new ArrayList<>(width * height);
		} else if (qName.equals("cell")) {
			myCells.add(myAttributeMap);
			myAttributeMap = null;
		}
	}

	@Override
	public void endDocument() throws SAXException {
		//System.out.println("--endDocument()--");
		super.endDocument();
		myModel.buildGrid(myCells, myCSGUI);
	}
}
