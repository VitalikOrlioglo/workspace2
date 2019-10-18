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
    
    private String pfad;

    @FXML
    void onBild(ActionEvent event) {
    	String item = new FileChooser().showOpenDialog(null).getAbsolutePath();
    	pfad="file:///" +  item;
    	 studentTabelView.getItems().add(new Student(matrikelnummerTF.getText(),
					vornameTF.getText(),
					nachnameTF.getText(),
					gebTF.getValue() , 
					pfad
					));
//    	studentTabelView.updateItem(item, true);
//    	studentTabelView.getSelectionModel().getSelectedItem().;
//    	studentTabelView.getSelectionModel().getSelectedCells()
//    	studentTabelView.getSelectionModel().getSelectedItem().setBild(item);
//    	MyImageCell mc= new MyImageCell();
//    	mc.updateItem(item, true);
//    	System.out.println("on bild, item: " + item);
    	nachnameTF.clear();
    	vornameTF.clear();
    	matrikelnummerTF.clear();
    }

    @FXML
    void saveAction(ActionEvent event) {
    	
    	 studentTabelView.getItems().add(new Student(matrikelnummerTF.getText(),
    			 										vornameTF.getText(),
    			 										nachnameTF.getText(),
    			 										gebTF.getValue() ,
    			 										pfad
    			 										));
    }

    @FXML
    void initialize() {
    	
    
    initTable();
   
    //editieren: doppelklick, dann "in die Zelle kommen"
    }

	private void initTable() {
//		studentTabelView.getItems().add(new Student("INF1234", "Max", "Meier", LocalDate.now() ));
		studentTabelView.getItems().add(new Student("123", "vorname", "nachname",  LocalDate.now(), "file:///C:\\temp\\a.png"));
		matrikelCol.setCellValueFactory(new PropertyValueFactory<>("matrikelnummer"));//für jede Spalte und jede Zelle --- Eigenschaft der klasse ...hier wird gettermethodenname benutzt
		vornameCol.setCellValueFactory(new PropertyValueFactory<>("vorname"));
		nachnameCol.setCellValueFactory(new PropertyValueFactory<>("nachname"));//
		gebCol.setCellValueFactory(new PropertyValueFactory<>("geburtsdatum"));
		bildCol.setCellValueFactory(new PropertyValueFactory<>("bild"));
		//zusätzliche innereklasse
		//--------------CellFactory
		nachnameCol.setCellFactory(TextFieldTableCell.forTableColumn());//...factory...Aussehen  Eigenschaften der Zelle)...fortable...Zelle editierbar machen
   
		
//		bildCol.setCellFactory(new Callback<TableColumn<Student,String>, TableCell<Student,String>>() {
//			
//			@Override
//			public TableCell<Student, String> call(TableColumn<Student, String> param) {//hier wird TableCell zurückgegeben!!
//				// TODO Auto-generated method stub
//				return new TableCell<Student, String>(){//Ein parameter und gibt ein Objekt zurück
//					
//				};//hier könnte man auch anonyme classemachen - falls man sofort eine Methode überschreiben will
//			}
//		});
//		bildCol.setCellFactory(e-> {
//			return new MyImageCell();
//			
//		});//value...it new kommt man nicht weiterCallback: gibt ne mtehode zurück Tablezelle lambdaausdruck mit einem return wird;
		
		bildCol.setCellFactory(e-> new MyImageCell()); //Ergebnis. MyImageCell ist auch Object!
		
		//Löschen
		initContextTableMenu();
	}

	private void initContextTableMenu() {
		ContextMenu cm =	new ContextMenu();
		MenuItem deleteItem = new MenuItem("Delete");
		
		deleteItem.setOnAction(e->{
			
			Student s=studentTabelView.getSelectionModel().getSelectedItem();//kennt alle selektierten Teile..s.a. getselectedItems
			studentTabelView.getItems().remove(s);//...und entfernt sie
			
		});
		
		cm.getItems().add(deleteItem);
		studentTabelView.setContextMenu(cm);
	}

	@FXML public void onCancel() {//geil: events!!System.out.println("onStart")
		System.out.println("onCancel");
	}

	@FXML public void onStart() {
		System.out.println("onStart");
	}

	@FXML public void onCommit(CellEditEvent<Student, String> e) {//Obertyp... Methode um zu sehen, was es da gibt. Alternativ über SceneBuilder->Code->unterstrich
		String newValue= e.getNewValue();
		String oldValue = e.getOldValue();
		Student s= e.getRowValue();//gesucht mit e.
		System.out.println("onCommit" + e.getNewValue());//Obertyp Event behalten? dann nur Objektmethoden nach e.---e.getSource()
		System.out.println("onCommit" + s);
	}
    //Innere Klasse kann auch static sein.
	class MyImageCell extends TableCell<Student, String>{//Innere Klasse, da man nicht unbedingt eine neue Datei anlegen will
		@Override
		protected void updateItem(String item, boolean empty) {//zeigt normalerweise den Text an---
			// TODO Auto-generated method stub
			if (item==null || empty) setGraphic(null);//fängt irgendeinen bug ab...
			//super.updateItem(item, empty);// soll ja eigentlich was anderes machwn
			else {
//			System.out.println(item);
			//setGraphic: will Node haben, wie Button
//			setGraphic(new Button("xxx"));//eine Methode von TableCell (oder geerbt)steht eigentlich super.setGraphic davor
//			setGraphic(new ImageView(new Image(new File("C:temp/a.png"))));
//			setGraphic(new ImageView(new Image("file:///C:/temp/b.png")));
			//item="file:///C:/temp/b.png";
//			System.out.println("UPDATE Item: " + item);	
			ImageView iv = new ImageView(new Image(item));
			iv.setPreserveRatio(true);
			iv.setFitHeight(80);
			setGraphic(iv);
//			setGraphic(new ImageView(new Image(item)));
			
					}
		
	}
	
}}



