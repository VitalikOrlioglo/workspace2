package application;

import java.io.File;
import java.io.InputStream;
import java.time.LocalDate;

import dao.StudentDAO;
import dao.StudentMYSQLDAOImpl;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.DatePicker;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.TableColumn.CellEditEvent;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.FileChooser;
import model.Student;

public class StudentenController {
//	private StudentDAO dao;
	private static final String DEFAULT_IMG = "/img/default_user.png";// von Packages-Root-> getResorces...

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

	private String currentImage = "";

	@FXML Button selectImageBTN;
	
	private StudentMYSQLDAOImpl studentMysql = new StudentMYSQLDAOImpl();
	
	@FXML
	void saveAction(ActionEvent event) {
		studentTabelView.getItems().add(new Student(matrikelnummerTF.getText(), vornameTF.getText(),
				nachnameTF.getText(), gebTF.getValue(), currentImage));
		studentTabelView.getItems().get(0);
	}
	
	@FXML
	void initialize() {
		studentTabelView.getItems().add(new Student("INF1234", "Max", "Meier", LocalDate.now()));
		initTable();
		
	}
	
	private void initTable() {
		matrikelCol.setCellValueFactory(new PropertyValueFactory<>("matrikelnummer"));// getMatrikelnummer()
		vornameCol.setCellValueFactory(new PropertyValueFactory<>("vorname"));//
		nachnameCol.setCellValueFactory(new PropertyValueFactory<>("nachname"));//
		gebCol.setCellValueFactory(new PropertyValueFactory<>("geburtsdatum"));//
		bildCol.setCellValueFactory(new PropertyValueFactory<>("bild"));//

		// ---------- CellFactory --------------------------
		nachnameCol.setCellFactory(TextFieldTableCell.forTableColumn());// Zelle editierbar machen

		bildCol.setCellFactory(e -> new MyImageCell());

		initTableContextMenu();
	}
	
	private void initTableContextMenu() {
		ContextMenu cm = new ContextMenu();
		MenuItem deleteItem = new MenuItem("Delete");
		deleteItem.setOnAction(e -> {
			Student s = studentTabelView.getSelectionModel().getSelectedItem();// .getSelectedItems()
			studentTabelView.getItems().remove(s);
		});
		cm.getItems().add(deleteItem);
		studentTabelView.setContextMenu(cm);
	}
	
	@FXML
	public void onCancel() {
		System.out.println("onCancel");
	}

	@FXML
	public void onStart() {
		System.out.println("onStart");
	}

	@FXML
	public void onCommit(CellEditEvent<Student, String> e) {
		String newValue = e.getNewValue();
		System.out.println(newValue);
		String oldValue = e.getOldValue();
		Student s = e.getRowValue();
		System.out.println("onCommit: " + s);
	}
	
	class MyImageCell extends TableCell<Student, String> {

		@Override
		protected void updateItem(String item, boolean empty) {
	
			if (item == null || empty) { //workaround-> doppelte Eintr�ge
				 setGraphic(null);
		         setText(null);
			}else {
				
				if (item.equals("")) {
					InputStream in = getClass().getResourceAsStream(DEFAULT_IMG);
					ImageView iv = new ImageView(new Image(in));
					iv.setFitWidth(50);
					iv.setPreserveRatio(true);
					setGraphic(iv);
					
				} else {
					ImageView iv = new ImageView(new Image("file:/" + currentImage));
					iv.setFitWidth(50);
					iv.setPreserveRatio(true);
					setGraphic(iv);
					
				}
			}

		}
	}
	
	@FXML
	public void onSelectImage(ActionEvent event) {
		FileChooser fc = new FileChooser();
		File f = fc.showOpenDialog(null);
		if(f!=null) {
			currentImage = f.getAbsolutePath();
			selectImageBTN.setText(currentImage);
			
		}
	}
}
