package application;


import java.net.URL;
import java.time.LocalDate;
import java.util.ResourceBundle;

import javafx.collections.SetChangeListener;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.DatePicker;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableColumn.CellEditEvent;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldListCell;
import javafx.scene.control.cell.TextFieldTableCell;
import model.Student;

public class SampleController {

	@FXML
	private TableView<Student> studentTabelView;

	@FXML
	private TableColumn<Student, String> matrikelCol;

	@FXML
	private TableColumn<Student, String> vornameCol;

	@FXML
	private TableColumn<Student, String> nachnameCol;

	@FXML
	private TableColumn<Student, LocalDate> gebCol;

	@FXML
	private TableColumn<Student, String> bildCol;

	@FXML
	private TextField matrikelnummerTF;

	@FXML
	private TextField vornameTF;

	@FXML
	private TextField nachnameTF;

	@FXML
	private DatePicker gebTF;

	@FXML
	void saveAction(ActionEvent event) {
		studentTabelView.getItems().add(new Student(matrikelnummerTF.getText(), 
													vornameTF.getText(),
													nachnameTF.getText(),
													gebTF.getValue()
													));
	}

	@FXML
	void initialize() {
		studentTabelView.getItems().add(new Student("INF1234", "Max", "Meier", LocalDate.now()));

		matrikelCol.setCellValueFactory(new PropertyValueFactory<>("matrikelnummer"));// getMatrikelnummer()
		vornameCol.setCellValueFactory(new PropertyValueFactory<>("vorname"));//
		nachnameCol.setCellValueFactory(new PropertyValueFactory<>("nachname"));//
		gebCol.setCellValueFactory(new PropertyValueFactory<>("geburtsdatum"));//
		bildCol.setCellValueFactory(new PropertyValueFactory<>("bild"));//
		
		
		nachnameCol.setCellFactory(TextFieldTableCell.forTableColumn());// Zelle editierbar machen
		
		bildCol.setCellFactory(e -> new MyImageCell());
		
		
		ContextMenu cm = new ContextMenu( );
		MenuItem deleteItem = new MenuItem("Delete");
		deleteItem.setOnAction( e->{
		
			Student s = studentTabelView.getSelectionModel().getSelectedItem();// .getSelectedItems() 
			studentTabelView.getItems().remove(s);
			
		} );
		cm.getItems().add(deleteItem);
		studentTabelView.setContextMenu( cm );

	}

	@FXML public void onCancel() {
		
		System.out.println("onCancel");
	}

	@FXML public void onStart() {
		System.out.println("onStart");
		
	}

	@FXML public void onCommit(CellEditEvent<Student, String> e) {
		String newValue = e.getNewValue();
		System.out.println(newValue);
		String oldValue  = e.getOldValue();
		Student  s= e.getRowValue();
		System.out.println("onCommit: "+s);
	}
	
	
	class MyImageCell extends TableCell<Student, String>{
		
		
		@Override
		protected void updateItem(String item, boolean empty) {
			// TODO Auto-generated method stub
			//super.updateItem(item, empty);
		//	if(item!=null) {
				
				System.out.println(item);
				setGraphic(new Button("xxxx"));
		//	}
		}
	}
}
