package model;

import java.io.IOException;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.xml.sax.SAXException;

import gui.CellSocietyGUI;

public class SaxParser {
	public static IModel getModel(String fileName, CellSocietyGUI CSGUI){
		try {
            SAXParserFactory parserFactory = SAXParserFactory.newInstance();
            SAXParser parser=parserFactory.newSAXParser();
            SaxHandler myHandler=new SaxHandler(CSGUI);
            parser.parse(fileName, myHandler);
            return myHandler.getModel();
        } catch (SAXException e) {
            e.printStackTrace();
        } catch (ParserConfigurationException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
        return null;
	}

}