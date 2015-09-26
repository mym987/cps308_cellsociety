package model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import gui.CellSocietyGUI;

public class SaxHandler extends DefaultHandler {
	
	private String myNodeName;
	private Map<String, String> myAttributeMap = null;
	private Map<String, String> myModelConfig;
	private List<Map<String, String>> myCells;

	private String myCurrentTag = null;
	private String myCurrentValue = null;

	public Map<String, String> getModelConfig() {
		return myModelConfig;
	}
	
	public List<Map<String, String>> getCells(){
		return myCells;
	}

	@Override
	public void startDocument() throws SAXException {
		super.startDocument();
		myNodeName = "model";
	}

	@Override
	public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
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
			}
			myCurrentTag = null;
			myCurrentValue = null;
		}
	}

	@Override
	public void endElement(String uri, String localName, String qName) throws SAXException{
		if (qName.equals("model")) {
			int width = Integer.parseInt(myAttributeMap.get("width"));
			int height = Integer.parseInt(myAttributeMap.get("height"));
			myModelConfig = myAttributeMap;
			myNodeName = "cell";
			myCells = new ArrayList<>(width * height);
			myAttributeMap = null;
		} else if (qName.equals("cell")) {
			myCells.add(myAttributeMap);
			myAttributeMap = null;
		}
	}

	@Override
	public void endDocument() throws SAXException {
		super.endDocument();
	}
}