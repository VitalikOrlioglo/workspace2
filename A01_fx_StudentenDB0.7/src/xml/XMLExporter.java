package xml;

import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

import org.jdom2.Document;
import org.jdom2.Element;
import org.jdom2.output.Format;
import org.jdom2.output.XMLOutputter;

import javafx.collections.ObservableList;
import model.Student;

/*
 * <studenten>
 * 		<student id="1">
 * 			<matrikelnummer>M11</matrikelnummer>
 * 		</student>
 * 		<student id="2">
 * 			<matrikelnummer>M11</matrikelnummer>
 * 		</student>
 * 		. . . 
 * </studenten>
 */


public class XMLExporter {

	private static final String STUDENTEN_XML = "Studenten.xml";

	public static void export(List<Student> observableList) {
		Element rootElement = new Element("studenten");
		Document doc = new Document(rootElement);
		
		for (Student student : observableList) {
			Element studentElement = new Element("student");
			studentElement.setAttribute("id", student.getId()+"");
			
			rootElement.addContent(studentElement);
		}
		
		
		// write XML
		XMLOutputter outputter = new XMLOutputter(Format.getPrettyFormat());
		
		try (FileOutputStream out = new FileOutputStream(STUDENTEN_XML)){ // try catch with resources
			outputter.output(doc, out);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
