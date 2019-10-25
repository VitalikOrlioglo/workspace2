package ui;

import java.time.LocalTime;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.sun.corba.se.spi.extension.ZeroPortPolicy;

import client.Client;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableRow;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import model.MessageObject;
import server.Server;

public class MessengerController {

	private static Logger log = LogManager.getLogger();
	@FXML
	private TextField localServerTextField;
	@FXML
	private TextField nameTextField;
	@FXML
	private TextField messageTextField;
	@FXML
	private TextField toServerTextField;
	@FXML
	private TableView<MessageObject> messageTableView;

	// ----------------------- Member---------------------------

	private Client client;
	private Server server;
	private MessageObject msgo;

	@FXML
	public void serverStartAction(ActionEvent event) {

		log.info("server start action");
		server.setServerhost(localServerTextField.getText());
		server.restart();

		server.messageProperty().addListener((a, b, info) -> {
			if (info.equals("SERVER_BOUND")) {
				Button b1 = (Button) event.getSource();
				b1.setDisable(true);
			}
		});

	}

	// Event Listener on Button.onAction
	/*
	 * nachricht senden und der Liste anh�ngen -Nachricht an
	 * TableView-OberervableArrayList -MessageObject zum Client setzen -Client
	 * restart
	 * 
	 */
	@FXML
	public void messageSendAction(ActionEvent event) {
		log.info("send  action");
		msgo = new MessageObject(nameTextField.getText(), messageTextField.getText(), LocalTime.now());

		client.setMessageObject(msgo);
		client.restart();
		messageTableView.getItems().add(msgo);

	}

	// Event Listener on Button.onAction
	@FXML // Client setzt Serverhost
	public void connectToServerAction(ActionEvent event) {
		log.info("client connoct to server action");
		client.setServerHost(toServerTextField.getText());

	}

	/*
	 * 
	 * -Server und Client erzeugen -Listener f�r die empfangene Nachricht -Nachricht
	 * an die Liste h�ngen -setOnFailed f�r Server, Client, MessageService
	 * 
	 * 
	 */

	@FXML
	public void initialize() {
		log.info("Controller init...");
		server = new Server();
		client = new Client();

		server.setOnFailed(e -> {
			log.error(server.getException().getStackTrace());
		});
		server.getMessageService().setOnFailed(e -> {
			log.error(server.getMessageService().getException().getStackTrace());
		});

		client.setOnFailed(e -> {
			log.error(client.getException().getStackTrace());
		});
		
		// b.z.w Listener
		server.getMessageService().setOnSucceeded(e -> {
			MessageObject msgo = server.getMessageService().getValue();
			log.debug("MessageService OnSucceeded " + msgo);
			messageTableView.getItems().add(msgo);
		});

		messageTableView.setRowFactory(row -> new TableRow<MessageObject>() {
			@Override
			protected void updateItem(MessageObject item, boolean empty) {
				super.updateItem(item, empty);// Original Verhalten
				if (item == null) {
					setStyle("");
				} else if (!item.isResponseObject()) {/// testen ob Client-Objekt
					setStyle("-fx-background-color: lightgrey");
				} else {
					setStyle("-fx-background-color: #ffdddd");
				}
			};
		});

	}
}
