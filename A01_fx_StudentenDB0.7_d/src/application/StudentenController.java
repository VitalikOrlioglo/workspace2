package application;

import java.io.File;
import java.net.URL;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ResourceBundle;

import dao.StudentDAO;
import dao.StudentMySQLDAOImpl;
import db.DBConnectException;
import javafx.animation.FadeTransition;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
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
import javafx.util.Duration;
import model.Student;
import util.Config;
import util.FileUtils;
import xml.XMLExporter;

public class StudentenController {

	@FXML
	public  ResourceBundle resources;

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

	@FXML
	private Label infoLabel;

	@FXML
	private TextField searchTF;

	@FXML
	private ComboBox<String> searchComboBox;

	private ObservableList<String> comboboxList = FXCollections.observableArrayList("matrikelnummer", "vorname",
			"nachname");// TODO ->Properties

	@FXML
	void saveAction(ActionEvent event) {
		Student s = new Student(matrikelnummerTF.getText(), vornameTF.getText(), nachnameTF.getText(), gebTF.getValue(),
				currentImageFile.getName());
		boolean saved = dao.save(s);
		if (saved) {
			studentTabelView.getItems().add(s);// oder findAll
			FileUtils.copyToUserImages(currentImageFile);//
			setInfoMessage("Datensatz gespeichert!");
//			setInfoMessage(resources.getString("Datensatzgespeichert"));
		} else {
			System.out.println("Fehler...");// TODO log
		}
	}

	@FXML
	void initialize() {
//		resources = ResourceBundle.getBundle(baseName);

		try {
			searchComboBox.setItems(comboboxList);
			FileUtils.createUserDir();//
			// dao = new StudentDummyDAOImpl();
			dao = new StudentMySQLDAOImpl();
			// TODO Auto-generated catch block

			studentTabelView.setItems(FXCollections.observableArrayList(dao.findAll()));
			initTable();
			// TODO FileChooser, nur Bilder?
		} catch (DBConnectException e) {
			Alert alert = new Alert(AlertType.ERROR);
			alert.setContentText("keine Datenbankverbindung!");
			alert.showAndWait();
			Platform.exit();
		} catch (Exception e) {
			System.out.println("Fehler");
		}
	}

	private void initTable() {
		matrikelCol.setCellValueFactory(new PropertyValueFactory<>("matrikelnummer"));// getMatrikelnummer()
		vornameCol.setCellValueFactory(new PropertyValueFactory<>("vorname"));//
		nachnameCol.setCellValueFactory(new PropertyValueFactory<>("nachname"));//
		gebCol.setCellValueFactory(new PropertyValueFactory<>("geburtsdatum"));//
		bildCol.setCellValueFactory(new PropertyValueFactory<>("bild"));//
		// ---------- CellFactory --------------------------
		vornameCol.setCellFactory(TextFieldTableCell.forTableColumn());// Zelle editierbar machen
		nachnameCol.setCellFactory(TextFieldTableCell.forTableColumn());// Zelle editierbar machen
		bildCol.setCellFactory(e -> new MyImageCell());
		gebCol.setCellFactory(e -> new MyDateCell());
		initTableContextMenu();
	}

	private void initTableContextMenu() {
		ContextMenu cm = new ContextMenu();
		MenuItem deleteItem = new MenuItem("Delete");
		deleteItem.setOnAction(e -> {// TODO auslagern
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
			setInfoMessage("Datensatz gel�scht! (Matr.Nr.: " + s.getMatrikelnummer() + ")");
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
		Student s = e.getRowValue();
		// e.getTableColumn().getText() -> Spalten�berschrift
		String fieldname = e.getTableColumn().getId(); // <TableColumn id="nachname"
		System.out.println("onCommit: " + s);
		boolean updated = dao.updateStudent(s.getId(), fieldname, newValue);
		if (updated) {// TODO Model aktualieren
			System.out.println("update: " + updated);// log
			setInfoMessage("Update erfolgreich, Matr.Nr.: " + s.getMatrikelnummer());
		}
	}

	private void setInfoMessage(String msg) {
		infoLabel.setOpacity(1);
		FadeTransition fade = new FadeTransition(Duration.seconds(1), infoLabel);
		fade.setDelay(Duration.seconds(4));
		fade.setFromValue(1);// Opacity
		fade.setToValue(0);
		fade.play();
		infoLabel.setText(msg);
	}

	/*
	 * TODO auslagern
	 */
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
					URL url = getClass().getResource(Config.DEFAULT_IMG);// -> /img/default_user.png
					createImage(url.toExternalForm());
				} else {
					createImage("file:/" + Config.USER_IMAGE_PATH + item);
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

	@FXML
	public void searchAction() {

		System.out.println(searchTF.getText());
		String selectedItem = searchComboBox.getSelectionModel().getSelectedItem();
		studentTabelView.getItems().setAll(dao.findByField(selectedItem, searchTF.getText()));

	}

	@FXML
	public void refreshAction() {
		studentTabelView.setItems(FXCollections.observableArrayList(dao.findAll()));

	}

	@FXML
	public void exportXMLAction(ActionEvent event) {

		XMLExporter.export(studentTabelView.getItems());// im Paket xml
	}
}
