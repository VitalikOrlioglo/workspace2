package app;

import java.io.File;
import java.util.List;

import org.jdom2.Document;
import org.jdom2.Element;
import org.jdom2.input.SAXBuilder;

public class XMLReader {

	public static void main(String[] args) {
		try {
			SAXBuilder builder = new SAXBuilder();
			Document doc = builder.build(new File("out.xml"));
			
			Element rootelement = doc.getRootElement();
			System.out.println(rootelement.getName()); // root
			List<Element> childList = rootelement.getChildren(); // rootelement.getChildren("NAME");
			
			for (Element element : childList) {
				System.out.println("\t" + element.getName()+" id: " + element.getAttributeValue("id"));
				System.out.println("\t" + element.getTextTrim());
				
				List<Element> list = element.getChildren();
				for (Element element2 : list) {
					System.out.println("\t" + element2.getName());
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
