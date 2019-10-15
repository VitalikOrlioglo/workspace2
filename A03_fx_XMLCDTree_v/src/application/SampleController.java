package application;

import java.io.IOException;

import org.jdom2.JDOMException;

import javafx.fxml.FXML;
import javafx.scene.control.TreeView;

public class SampleController {

	@FXML TreeView<String> cdTreeView;
	
	@FXML
	void initialize() {
			cdTreeView.setRoot(CDTreeCreator.createTree("cd-catalog.xml"));

	}
	
}
