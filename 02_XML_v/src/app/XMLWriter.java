package app;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import org.jdom2.Attribute;
import org.jdom2.Document;
import org.jdom2.Element;
import org.jdom2.output.Format;
import org.jdom2.output.XMLOutputter;

public class XMLWriter {

	public static void main(String[] args) {
		
		System.out.println("Write xml ...");
		
		Element rootElement = new Element("root");
		
		Document doc = new Document(rootElement);
		
		Element child1 = new Element("child1");
		
		child1.addContent(new Element("child1a"));
		
		Element child2 = new Element("child2");
		child2.setAttribute(new Attribute("id", "42"));
		child2.setText("ein Textelement");
		
		rootElement.addContent(child1);
		rootElement.addContent(child2);
		
		XMLOutputter outputter = new XMLOutputter(Format.getPrettyFormat());
		
		try (FileOutputStream out = new FileOutputStream("out.xml")){ // try catch with resources
			outputter.output(doc, out);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
