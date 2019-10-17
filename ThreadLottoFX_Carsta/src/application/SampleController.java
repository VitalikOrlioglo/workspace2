package application;

import java.util.ArrayList;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.ToolBar;
import javafx.scene.layout.AnchorPane;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;

public class SampleController {
	WebEngine engine = null;
	static ArrayList<Integer> numberList = new ArrayList<>();
	static String part1 = null;
	static String part2 = null;
	

	
	@FXML
	private WebView webView;

    public WebView getWebView() {
		return webView;
	}


	@FXML
    private AnchorPane mitte;
    

    @FXML
    void starten(ActionEvent event) {
    	
    	makeWebview();
    	
    }
    

	

	
	public static String listToString(ArrayList<Integer> liste) {
		String komplett = "[";
		
		for(int i = 0; i < liste.size(); i++) {
			if(i == liste.size() - 1) {
				komplett += liste.get(i);
			}
			else {
				komplett += (liste.get(i) + ", ");
			}
			
		}
		
		komplett += "]";
		
		return komplett;
	}
	
	public void makeWebview() {
		
		Filehandler.lesen();
		
		String part3 = listToString(numberList);
		//System.out.println(part3);
		String html = part1 + part3 + part2;
		engine = webView.getEngine();
		engine.setJavaScriptEnabled(true);
		engine.loadContent(html);
		
		numberList = new ArrayList<Integer>();

		
		LottoThread lotto2 = new LottoThread();
		lotto2.start();
	
	}
	
	


    
}
