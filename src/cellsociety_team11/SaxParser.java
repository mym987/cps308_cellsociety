package cellsociety_team11;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

public class SaxParser {
	public Model getModel(String fileName){
		try {
            SAXParserFactory parserFactory=SAXParserFactory.newInstance();
            SAXParser parser=parserFactory.newSAXParser();
            SaxHandler myHandler=new SaxHandler();
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