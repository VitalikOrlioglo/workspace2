package application;

import java.io.File;
import java.io.InputStream;
import java.net.URL;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import dao.StudentDAO;
import dao.StudentDummyDAOImpl;
import dao.StudentMySQLDAOImpl;
import db.DBConnectException;
import javafx.animation.FadeTransition;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.DatePicker;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableColumn.CellEditEvent;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.FileChooser;
import javafx.util.Duration;
import model.Student;
import util.Config;
import util.FileUtils;
import javafx.scene.control.Label;
import javafx.scene.control.ComboBox;

enum Test {
	A, B, C
}

public class StudentenController {

	private StudentDAO dao;

//	private static final String DEFAULT_IMG = "/img/default_user.png";// von Packages-Root-> getResorces...

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

	private File currentImageFile = new File("");

	@FXML
	private Button selectImageBTN;

	@FXML Label infoLabel;
	
	@FXML TextField searchTF;

	@FXML ComboBox<String> searchComboBox;
	
	private ObservableList<String> comboboxList = FXCollections.observableArrayList("matrikelnummer", "vorname", "nachname"); // TODO -> Properties

	@FXML
	void saveAction(ActionEvent event) {
		Student s = new Student(matrikelnummerTF.getText(), vornameTF.getText(),
				nachnameTF.getText(), gebTF.getValue(), currentImageFile.getName());
		boolean saved = dao.save(s);
		if(saved) {
			studentTabelView.getItems().add(s);// oder findAll
			FileUtils.copyToUserImages(currentImageFile);//
			setInfoMessage("Datensatz gespeichert!");
		}else {
			System.out.println("Fehler...");//TODO log
		}
	}

	@FXML
	void initialize() {
		try {
			searchComboBox.setItems(comboboxList);
			FileUtils.createUserDir();//
			//dao = new StudentDummyDAOImpl();
			dao = new StudentMySQLDAOImpl();
			
			// studentTabelView.getItems().add(new Student("INF1234", "Max", "Meier",
			// LocalDate.now()));
			studentTabelView.setItems(FXCollections.observableArrayList(dao.findAll()));
			initTable();
			// TODO FileChooser, nur Bilder?
		} catch (DBConnectException e) {
			Alert alert = new Alert(AlertType.ERROR);
			alert.setContentText("keine Datenbankverbindung!");
			alert.showAndWait();
			Platform.exit();
		} catch (Exception e) {
			System.out.println("Algemeine Fehler");
		}
	}

	private void initTable() {
		matrikelCol.setCellValueFactory(new PropertyValueFactory<>("matrikelnummer"));// getMatrikelnummer()
		vornameCol.setCellValueFactory(new PropertyValueFactory<>("vorname"));//
		nachnameCol.setCellValueFactory(new PropertyValueFactory<>("nachname"));//
		gebCol.setCellValueFactory(new PropertyValueFactory<>("geburtsdatum"));//
		bildCol.setCellValueFactory(new PropertyValueFactory<>("bild"));//
		// ---------- CellFactory --------------------------
		nachnameCol.setCellFactory(TextFieldTableCell.forTableColumn());// Zelle editierbar machen
		vornameCol.setCellFactory(TextFieldTableCell.forTableColumn());
		bildCol.setCellFactory(e -> new MyImageCell());
		gebCol.setCellFactory(e -> new MyDateCell());
		initTableContextMenu();
	}

	private void initTableContextMenu() {
		ContextMenu cm = new ContextMenu();
		MenuItem deleteItem = new MenuItem("Delete");
		deleteItem.setOnAction(e -> { // TODO auslagern
			deletedStudent();
		});
		cm.getItems().add(deleteItem);
		studentTabelView.setContextMenu(cm);
	}

	private void deletedStudent() {
		Student s = studentTabelView.getSelectionModel().getSelectedItem();// .getSelectedItems()
		
		boolean deleted = dao.deleteStudent(s.getId());
		if (deleted) {
			studentTabelView.getItems().remove(s);
			setInfoMessage("Datensatz geloscht! (Matr. Nr. : " + s.getMatrikelnummer() + ")");
		}
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
	//	e.getTableColumn().getText() -> Spaltenuberschrift
		String fieldname = e.getTableColumn().getId(); // <TableColumn id="nachname"
		System.out.println("onCommit: " + s);
		boolean uptated = dao.updateStudent(s.getId(), fieldname, newValue);
		if (uptated) { // TODO Model aktualisieren durch zb Binding
			System.out.println("update: " + uptated); //log
			setInfoMessage("Update erfolgreich, Matr. Nr.: " + s.getMatrikelnummer());
		}
		dao.updateStudent(s.getId(), fieldname, newValue);
	}

	
	private void setInfoMessage(String msg) {
		infoLabel.setOpacity(1);
		FadeTransition fade = new FadeTransition(Duration.seconds(1), infoLabel);
		fade.setDelay(Duration.seconds(4));
		fade.setFromValue(1); // Opacity
		fade.setToValue(0);
		fade.play();
		infoLabel.setText(msg);
	}
	
	class MyDateCell extends TableCell<Student, LocalDate> {
		@Override
		protected void updateItem(LocalDate item, boolean empty) {
			if (item == null || empty) { // workaround-> doppelte Eintr�ge
				setGraphic(null);
				setText(null);
			} else {
				System.out.println("LocalDate: " + item);
				String formattedDate = item.format(DateTimeFormatter.ofPattern("dd.MM.yyyy"));
				setText(formattedDate);
			}
		}
	}
	
	/*
	 * TODO auslagern
	 */
	class MyImageCell extends TableCell<Student, String> {

		@Override
		protected void updateItem(String item, boolean empty) {
			if (item == null || empty) { // workaround-> doppelte Eintr�ge
				setGraphic(null);
				setText(null);
			} else {
				if (item.equals("")) {
					URL url = getClass().getResource(Config.DEFAULT_IMG);//-> /img/default_user.png
					createImage(url.toExternalForm());
				} else {
					createImage("file:/"+Config.USER_IMAGE_PATH+item);
				}
			}

		}

		private void createImage(String path) {
			ImageView iv = new ImageView(new Image(path));
			iv.setFitWidth(50);
			iv.setPreserveRatio(true);
			setGraphic(iv);
		}
	}

	@FXML
	public void onSelectImage(ActionEvent event) {
		FileChooser fc = new FileChooser();
		File f = fc.showOpenDialog(null);
		if (f != null) {
			currentImageFile = f;
			selectImageBTN.setText(currentImageFile.getName());
//			FileUtils.createUserDir();// >> FIXME -> zu init 
//			FileUtils.copyToUserImages(f);// >> FIXME -> zu save 

		}
	}

	@FXML public void searchAction() {
		System.out.println(searchTF.getText());
		String selectedItem = searchComboBox.getSelectionModel().getSelectedItem();
		studentTabelView.getItems().setAll(dao.findByField(selectedItem, searchTF.getText()));
	}

	@FXML public void refreshAction(ActionEvent event) {
		studentTabelView.setItems(FXCollections.observableArrayList(dao.findAll()));
	}
}
