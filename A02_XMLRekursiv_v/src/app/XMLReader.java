package app;

import java.io.File;
import java.io.IOException;
import java.util.List;

import org.jdom2.Attribute;
import org.jdom2.Document;
import org.jdom2.Element;
import org.jdom2.JDOMException;
import org.jdom2.input.SAXBuilder;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

/*
 * Ausgabe auf der Konsole: alle relevantenDaten aus der XML mit Hilfe von Rekursion
 * \t
 */

public class XMLReader {
	public static void readAllXML() throws JDOMException, IOException {
		SAXBuilder builder = new SAXBuilder();
		Document doc = builder.build(new File("menu.xml"));
		
		Element rootelement = doc.getRootElement();
		System.out.println(rootelement.getName()); // root
		// rekursive Methode aufrufen
		readAllNodes(rootelement);
		
	}
	

	
	
	private static void readAllNodes(Element element) {
		for (Element el : element.getChildren()) { // Abbruchbedingung
			List<Attribute> attributeList = el.getAttributes();
			String a = "";
			// if (!attributeList.isEmpty()) {
			for (Attribute attribute : attributeList) {
				a+=attribute.getName()+"="+attribute.getValue()+" ";
			}
			System.out.print(el.getName()+": "+a);
			System.out.println(el.getTextTrim());
			
			readAllNodes(el);
		}
	}
	
	
	public static void main(String[] args) {
		try {
			readAllXML();
		} catch (JDOMException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
