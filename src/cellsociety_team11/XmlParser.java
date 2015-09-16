package cellsociety_team11;

import java.io.IOException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

public class XmlParser {
	private Document parseXmlFile(String fileName){
		//get the factory
		DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();

		try {

			//Using factory get an instance of document builder
			DocumentBuilder db = dbf.newDocumentBuilder();

			//parse using builder to get DOM representation of the XML file
			return db.parse(fileName);

		}catch(ParserConfigurationException pce) {
			pce.printStackTrace();
		}catch(IOException ioe) {
			ioe.printStackTrace();
		} catch (SAXException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	private Element parseDocument(Document dom){
		//get the root element
		Element docElement = dom.getDocumentElement();

		//get a nodelist of model
		NodeList nl = docElement.getElementsByTagName("Model");
		if(nl != null && nl.getLength() > 0) {
			return (Element)nl.item(0);
		}
		return null;
	}
	
	/**
	 * I take an employee element and read the values in, create
	 * an Employee object and return it
	 */
	public Model getModel(String fileName) {
		
		

		Element modelElement = parseDocument(parseXmlFile(fileName));
		String name = getTextValue(modelElement,"Name");
		int width = getIntValue(modelElement,"Width");
		int height = getIntValue(modelElement,"Height");

		String type = modelElement.getAttribute("type");

		//Create a new Employee with the value read from the xml nodes
		Model m = new Model();

		return m;
	}


	/**
	 * I take a xml element and the tag name, look for the tag and get
	 * the text content
	 * i.e for <employee><name>John</name></employee> xml snippet if
	 * the Element points to employee node and tagName is 'name' I will return John
	 */
	private String getTextValue(Element ele, String tagName) {
		String textVal = null;
		NodeList nl = ele.getElementsByTagName(tagName);
		if(nl != null && nl.getLength() > 0) {
			Element el = (Element)nl.item(0);
			textVal = el.getFirstChild().getNodeValue();
		}

		return textVal;
	}


	/**
	 * Calls getTextValue and returns a int value
	 */
	private int getIntValue(Element ele, String tagName) {
		return Integer.parseInt(getTextValue(ele,tagName));
	}
}