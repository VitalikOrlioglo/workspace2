package application;

import javafx.fxml.FXML;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeView;

public class SampleController {

	@FXML TreeView<String> treeView;
	
	@FXML
	void initialize() {
		TreeItem<String> rootItem = new TreeItem<>("root");
		TreeItem<String> child1 = new TreeItem<>("child1");
		TreeItem<String> child2 = new TreeItem<>("child2");
		
		rootItem.getChildren().add(child1);
		rootItem.getChildren().add(child2);
		
		rootItem.setExpanded(true);
		treeView.setRoot(rootItem);
	}
	
	
}
