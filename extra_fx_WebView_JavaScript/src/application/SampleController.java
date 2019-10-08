package application;

import java.net.URL;

import javafx.concurrent.Worker.State;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;
import netscape.javascript.JSObject;

public class SampleController {

	@FXML
	private WebView webView;

	@FXML
	private Label label;

	@FXML
	void initialize() {
		javafx.application.Platform.runLater(() -> {
			WebEngine engine = webView.getEngine();
			final URL urlGoogleMaps = getClass().getResource("Start.html");
			engine.load(urlGoogleMaps.toExternalForm());

			engine.getLoadWorker().stateProperty().addListener((a, b, newState) -> {
				if (newState == State.SUCCEEDED) {
					JSObject window = (JSObject) engine.executeScript("window");
					window.setMember("clickController",this);
					//window.setMember("clickController",new WebController());
				}
			}
//	            

			);

		});

	}

	public void printId(Object object) {
		if (org.w3c.dom.html.HTMLElement.class.isAssignableFrom(object.getClass())) {
			org.w3c.dom.html.HTMLElement it = (org.w3c.dom.html.HTMLElement) object;
			System.out.println("Id is " + it.getId());
			label.setText("Id from JavaScript: " + it.getId());
	}
	}
}
