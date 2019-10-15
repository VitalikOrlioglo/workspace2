package application;

import java.io.File;
import java.io.IOException;
import java.util.List;

import org.jdom2.Document;
import org.jdom2.Element;
import org.jdom2.JDOMException;
import org.jdom2.input.SAXBuilder;

import javafx.collections.ObservableList;
import javafx.scene.control.TreeItem;

public class CDTreeCreator {

	public static TreeItem<String> createTree(String string) {
		TreeItem<String> rootItem = new TreeItem<>();
		try {
			SAXBuilder builder = new SAXBuilder();
			Document doc = builder.build(new File("cd-catalog.xml"));
			Element rootelement = doc.getRootElement();
			rootItem.setValue(rootelement.getName());
			
			List<Element> cdElemente = rootelement.getChildren(); // <CD>...
			for (Element cdElement : cdElemente) {
				TreeItem<String> cdItem = new TreeItem<>();
//				System.out.println(cdElement.getName());
				cdItem.setValue(cdElement.getChildText("TITLE")); // Textelement vom Child-Element
				
				List<Element> cdDataElemente = cdElement.getChildren(); // TITLE, ARTIST, ...
				for (Element cdDataElement : cdDataElemente) {
					TreeItem<String> cdDataItem = new TreeItem<>();
					cdDataItem.setValue(cdDataElement.getName()+": " + cdDataElement.getTextTrim());
					cdItem.getChildren().add(cdDataItem);
				}
				rootItem.getChildren().add(cdItem);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		rootItem.setExpanded(true);
		return rootItem;
		
	}

}
