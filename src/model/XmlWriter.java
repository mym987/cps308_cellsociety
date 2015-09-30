package model;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Map;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Document;
import org.w3c.dom.Element;

public class XmlWriter {
	private Document myDocument;
	private Element myRoot;
	private Model myModel;

	/**
	 * Initialize with a model
	 * 
	 * @param model
	 * @throws Exception
	 */
	public void init(Model model) throws Exception {
		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
		DocumentBuilder builder;
		builder = factory.newDocumentBuilder();
		myDocument = builder.newDocument();
		myModel = model;
		myRoot = myDocument.createElement("File");
		myDocument.appendChild(myRoot);
	}

	/**
	 * Add an attribute to the root node
	 * 
	 * @param root
	 *            the node to which the attribute will be add
	 * @param name
	 *            name of the attribute
	 * @param attribute
	 *            the attribute that will be added, toString() will be called to
	 *            convert the attribute to string
	 * @return element representing the attribute
	 */
	private Element addAttribute(Element root, Object name, Object attribute) {
		Element e = myDocument.createElement(name.toString());
		e.appendChild(myDocument.createTextNode(attribute.toString()));
		root.appendChild(e);
		return e;
	}

	/**
	 * Add a group of attributes to the given node
	 * 
	 * @param root
	 *            the node to which the attribute will be add
	 * @param map
	 *            a map in which keys are the attribute names, and values are
	 *            the attributes
	 */
	private void addAttributes(Element root, Map<?, ?> map) {
		map.forEach((k, v) -> {
			addAttribute(root, k, v);
		});
	}

	private Element createRootElement(Element root, Object name) {
		Element e = myDocument.createElement(name.toString());
		root.appendChild(e);
		return e;
	}

	/**
	 * create a model attached to the root node with parameters got from the
	 * model
	 */
	private void createModel() {
		Element model = createRootElement(myRoot, "model");
		addAttributes(model, myModel.getParameters());
	}

	/**
	 * create cells attached to the cells tab under the root node with
	 * parameters got from the cells
	 */
	private void createCells() {
		Element cells = createRootElement(myRoot, "cells");
		myModel.getCells().forEach(cell -> {
			addAttributes(createRootElement(cells, "cell"), cell.getAttributes());
		});
	}

	/**
	 * create an xml file in given directory
	 * 
	 * @param dir
	 *            the directory in which the XML file will be created
	 * @return absolute path name of the file created
	 * @throws TransformerException
	 * @throws IOException
	 */
	public String createXml(File dir) throws IOException, TransformerException {
		createModel();
		createCells();

		DateFormat df = new SimpleDateFormat("MMddyy_HHmmss");
		Calendar calObj = Calendar.getInstance();
		String timeStamp = df.format(calObj.getTime());
		String fileName = dir.getAbsolutePath() + File.separator + myModel + "_" + timeStamp + ".xml";

		TransformerFactory tf = TransformerFactory.newInstance();
		Transformer transformer = tf.newTransformer();
		DOMSource source = new DOMSource(myDocument);
		transformer.setOutputProperty(OutputKeys.ENCODING, "UTF-8");
		transformer.setOutputProperty(OutputKeys.INDENT, "yes");
		PrintWriter pw = new PrintWriter(new FileOutputStream(fileName));
		StreamResult result = new StreamResult(pw);
		transformer.transform(source, result);
		return fileName;
	}

}
