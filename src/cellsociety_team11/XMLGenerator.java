package cellsociety_team11;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;
 
public class XMLGenerator{
    private Document myDoc;
    private Element myRoot;
    
    public static void main(String[] args){
    	XMLGenerator generator = new XMLGenerator();
    	generator.init();
    	generator.createXml("GOL.xml");
    }
 
    public void init() {
        try {
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();
            myDoc = builder.newDocument();
        } catch (ParserConfigurationException e) {
            System.out.println(e.getMessage());
        }
    }
 
    private Element addElement(Element root, Object name,Object attr){
    	Element e = myDoc.createElement(name.toString());
    	e.appendChild(myDoc.createTextNode(attr.toString()));
    	root.appendChild(e);
    	return e;
    }
    
    private Element addElement(Element root, Object name){
    	Element e = myDoc.createElement(name.toString());
    	root.appendChild(e);
    	return e;
    }
    
    private void addGroup(Element root, Map<?,?> map){
    	map.forEach((k,v)->{addElement(root,k,v);});
    }
    
    private void createModel(String name, int r, int c){
    	Element model = addElement(myRoot,"model");
    	addElement(model,"name",name);
        addElement(model,"rows",r);
        addElement(model,"columns",c);
    }
    
    private Element createRoot(String name){
    	Element root = myDoc.createElement(name);
    	myDoc.appendChild(root);
    	return root;
    }
    
    private void createCells(){
    	Element cells = addElement(myRoot,"cells");
    	int mat[][] = {
    			{	0	,	0	,	0	,	0	,	0	,	0	,	0	,	0	,	0	,	0	},
    			{	0	,	0	,	0	,	0	,	0	,	0	,	0	,	0	,	0	,	0	},
    			{	0	,	1	,	1	,	0	,	0	,	0	,	0	,	0	,	0	,	0	},
    			{	0	,	1	,	1	,	0	,	0	,	0	,	0	,	0	,	0	,	0	},
    			{	0	,	0	,	0	,	0	,	0	,	0	,	0	,	0	,	0	,	0	},
    			{	0	,	0	,	0	,	0	,	0	,	1	,	1	,	1	,	0	,	0	},
    			{	0	,	0	,	0	,	0	,	0	,	0	,	0	,	0	,	0	,	0	},
    			{	0	,	0	,	0	,	0	,	0	,	0	,	0	,	0	,	0	,	0	},
    			{	0	,	0	,	0	,	0	,	0	,	0	,	0	,	0	,	0	,	0	},
    			{	0	,	0	,	0	,	0	,	0	,	0	,	0	,	0	,	0	,	0	}};
    	for(int r=0;r<mat.length;r++){
    		for(int c=0;c<mat[r].length;c++){
    			Map<String,Integer> map = new HashMap<>();
    			map.put("row", r);
    			map.put("column", c);
    			map.put("state", mat[r][c]);
    			addGroup(addElement(cells,"cell"),map);	
    		}
    	}
    }
    
    public void createXml(String fileName) {
    	myRoot = createRoot("MyModel");
        createModel("GOLModel",10,10);
        createCells();
        
        TransformerFactory tf = TransformerFactory.newInstance();
        try {
            Transformer transformer = tf.newTransformer();
            DOMSource source = new DOMSource(myDoc);
            transformer.setOutputProperty(OutputKeys.ENCODING, "gb2312");
            transformer.setOutputProperty(OutputKeys.INDENT, "yes");
            PrintWriter pw = new PrintWriter(new FileOutputStream(fileName));
            StreamResult result = new StreamResult(pw);
            transformer.transform(source, result);
            System.out.println("Successfully generated XML file!");
        } catch (TransformerConfigurationException e) {
            System.out.println(e.getMessage());
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
        } catch (FileNotFoundException e) {
            System.out.println(e.getMessage());
        } catch (TransformerException e) {
            System.out.println(e.getMessage());
        }
    }
 
    public void parserXml(String fileName) {
        try {
            DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
            DocumentBuilder db = dbf.newDocumentBuilder();
            Document document = db.parse(fileName);
             
            NodeList cells = document.getChildNodes();
            for (int i = 0; i < cells.getLength(); i++) {
                Node employee = cells.item(i);
                NodeList cellInfo = employee.getChildNodes();
                for (int j = 0; j < cellInfo.getLength(); j++) {
                    Node node = cellInfo.item(j);
                    NodeList cellMeta = node.getChildNodes();
                    for (int k = 0; k < cellMeta.getLength(); k++) {
                        System.out.println(cellMeta.item(k).getNodeName()
                                + ":" + cellMeta.item(k).getTextContent());
                    }
                }
            }
            System.out.println("Completed");
        } catch (FileNotFoundException e) {
            System.out.println(e.getMessage());
        } catch (ParserConfigurationException e) {
            System.out.println(e.getMessage());
        } catch (SAXException e) {
            System.out.println(e.getMessage());
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }
}