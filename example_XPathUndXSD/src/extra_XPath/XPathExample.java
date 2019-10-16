package extra_XPath;

import java.io.IOException;
import java.util.List;

import org.jdom2.Content;
import org.jdom2.Document;
import org.jdom2.JDOMException;
import org.jdom2.input.SAXBuilder;
import org.jdom2.input.sax.XMLReaderJDOMFactory;
import org.jdom2.input.sax.XMLReaderXSDFactory;
import org.jdom2.input.sax.XMLReaders;
import org.jdom2.xpath.XPathExpression;
import org.jdom2.xpath.XPathFactory;

//<CATALOG>
//<CD>
//	<TITLE>Empire Burlesque</TITLE>
//	<ARTIST>Bob Dylan</ARTIST>
//	<COUNTRY>USA</COUNTRY>
//	<COMPANY>Columbia</COMPANY>
//	<PRICE>10.90</PRICE>
//	<YEAR>1985</YEAR>
public class XPathExample {

	public static void main(String[] args) {
		String xmlfile = "cd-catalog.xml";
		
		try {
			XMLReaderJDOMFactory schema = new XMLReaderXSDFactory("cd-catalog.xsd");
			SAXBuilder saxBuilder = new SAXBuilder(schema);
			Document doc = saxBuilder.build(xmlfile); // JDOMException,
														// IOException
			XPathFactory xpathFactory = XPathFactory.instance();

			String artistTextPath = "CATALOG/CD/ARTIST/text()"; // CD/ARTIST
			XPathExpression<Object> expr = xpathFactory.compile(artistTextPath);
			List<Object> artists = expr.evaluate(doc);
			for (int i = 0; i < artists.size(); i++) {
				Content content = (Content) artists.get(i);
				System.out.println(content.getValue());
			}
			
			
		} catch (JDOMException | IOException ex) {
			ex.printStackTrace();
		}
	}

}
