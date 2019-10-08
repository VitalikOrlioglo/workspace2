package application;

import java.time.LocalDate;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.DatePicker;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableColumn.CellEditEvent;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.FileChooser;
import javafx.util.Callback;
import javafx.scene.control.Button;
import model.Student;

/**
 * Aufgabe:
 * TableView bekommt Bilder(wenn keins angegeben wird Default-Bild)
 * Student
 * @author vitali orlioglo
 * 08.10.2019
 * 00_j2_fx_TableView_v
 */
public class SampleController {
//	private static final String DEFAULT_IMG = ""

    @FXML
    private TableView<Student> studentTableView;

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
    
    private String pfad;
    
    
    @FXML
    void bildOpen(ActionEvent event) {
    	String item = new FileChooser().showOpenDialog(null).getAbsolutePath();
    	pfad = "file:///" + item;
//    	studentTableView.getItems().add(new Student(matrikelnummerTF.getText(),
//													vornameTF.getText(),
//													nachnameTF.getText(),
//													gebTF.getValue(),
//													item="file:///" + item
//													));
    }
	
    @FXML
    void saveAction(ActionEvent event) {
    	studentTableView.getItems().add(new Student(matrikelnummerTF.getText(),
    												vornameTF.getText(),
    												nachnameTF.getText(),
    												gebTF.getValue(),
    												pfad
    												));
    }

    @FXML
    void initialize() {
    	studentTableView.getItems().add(new Student("INF123", "Jora", "Kaban", LocalDate.now()));
    	initTable();
    	
    	//FileChooser, nur Bilder?
    }
    
    private void initTable() {
    	// устанавливаем тип и значение которое должно хранится в колонке
    	matrikelCol.setCellValueFactory(new PropertyValueFactory<>("matrikelnummer")); // getMatrikelnummer()
    	vornameCol.setCellValueFactory(new PropertyValueFactory<>("vorname")); //
    	nachnameCol.setCellValueFactory(new PropertyValueFactory<>("nachname")); //
    	gebCol.setCellValueFactory(new PropertyValueFactory<>("geburtsdatum")); //
    	bildCol.setCellValueFactory(new PropertyValueFactory<>("bild")); //
    	
    	// ----------------- CellFactory ------------------------------
    	nachnameCol.setCellFactory(TextFieldTableCell.forTableColumn());// Zelle editierbar machen
    	bildCol.setCellFactory(e -> new MyImageCell());
    	
//    	bildCol.setCellFactory(new Callback<TableColumn<Student,String>, TableCell<Student,String>>() {
//			
//			@Override
//			public TableCell<Student, String> call(TableColumn<Student, String> param) {
//				// TODO Auto-generated method stub
//				return TableC ;
//			}
//		});
//    	
    	
    	
//		bildCol.setCellFactory(new Callback<TableColumn<Student,String>, TableCell<Student,String>>() {
//		
//		@Override
//		public TableCell<Student, String> call(TableColumn<Student, String> param) {
//			// TODO Auto-generated method stub
//			return new TableCell<Student, String>(){
//				
//				@Override
//				protected void updateItem(String item, boolean empty) {
//					// TODO Auto-generated method stub
//					super.updateItem(item, empty);
//				}
//			};
//		}
//	});
    	
    	initTableContextMenu();  	
    }
    
    private void initTableContextMenu() {
    	ContextMenu cm = new ContextMenu();
    	MenuItem deleteItem = new MenuItem("Delete");
    	deleteItem.setOnAction(e->{
    		Student s = studentTableView.getSelectionModel().getSelectedItem(); // getSelectedItems()
    		studentTableView.getItems().remove(s);
    	});
    	cm.getItems().add(deleteItem);
    	studentTableView.setContextMenu(cm);
    }

	@FXML public void onCancel() {
		System.out.println("onCancel");
	}

	@FXML public void onStart() {
		System.out.println("onStart");
	}

	@FXML public void onCommit(CellEditEvent<Student, String> e) {
//		String newValue = e.getNewValue();
//		System.out.println(newValue);
//		String oldValue = e.getOldValue();
//		System.out.println(oldValue);
//		Student rowValue = e.getRowValue();
//		System.out.println("onCommit" + rowValue);
	}
	
	class MyImageCell extends TableCell<Student, String> {
		@Override
		protected void updateItem(String item, boolean empty) {
//			System.out.println("item ---- " + empty);
			ImageView ivDefault = new ImageView(new Image("file:///C:/Users/Student/Desktop/bilder_gross/user1.png"));
			ivDefault.setFitHeight(50);
			ivDefault.setFitWidth(50);
			ivDefault.setPreserveRatio(true);
//			Image img = 
//			System.out.println("empty" + empty);
//			super.updateItem(item, empty);
			if (item == null || empty) {
				super.setGraphic(null);
//				System.out.println(item);
//				super.setText("Hallo");
			} else {
				super.setGraphic(ivDefault);
//				System.out.println("");
			}
		}
	}
}
