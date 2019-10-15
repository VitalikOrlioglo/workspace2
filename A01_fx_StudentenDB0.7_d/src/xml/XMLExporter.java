package xml;

import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

import org.jdom2.Document;
import org.jdom2.Element;
import org.jdom2.output.Format;
import org.jdom2.output.XMLOutputter;

import model.Student;


/*
 * 
 * <studenten>
 * 		<student id="1">
 * 			<matrikelnummer>M111</matrikelnummer>
 * 		</student>
 * 		<student id="2">
 * 			<matrikelnummer>M222</matrikelnummer>
 * 		</student>
 * 		...
 * </studenten>
 */

public class XMLExporter {

	private static final String XML = "studenten.xml";

	public static void export( List<Student> observableList) {
		Element rootElement = new Element("studenten"); // <studenten>

		Document doc = new Document(rootElement);
		
		for (Student student : observableList) {
			Element studentElement = new Element("student");
			studentElement.setAttribute("id",student.getId()+""); // <student id="1">
			
			Element matrikelElement = new Element("matrikelnummer");
			matrikelElement.setText(student.getMatrikelnummer());
			studentElement.addContent(matrikelElement);
			
			Element vornameElement = new Element("vorname");
			vornameElement.setText(student.getVorname());
			studentElement.addContent(vornameElement);
			
			Element nachnameElement = new Element("nachname");
			nachnameElement.setText(student.getNachname());
			studentElement.addContent(nachnameElement);
			
			Element geburtsdatumElement = new Element("geburtsdatum");
			geburtsdatumElement.setText(student.getformatGeburtsdatum());
			studentElement.addContent(geburtsdatumElement);
			
			Element bildElement = new Element("bild");
			bildElement.setText(student.getBild());
			studentElement.addContent(bildElement);
			
			rootElement.addContent(studentElement);
		}
		
		
		
		
		// write XML
		XMLOutputter outputter = new XMLOutputter(Format.getPrettyFormat());

		try (FileOutputStream out = new FileOutputStream(XML)){
			
			outputter.output(doc, out);
			
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

}
