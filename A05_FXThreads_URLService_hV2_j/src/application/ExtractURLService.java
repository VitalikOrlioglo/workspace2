package application;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.concurrent.Service;
import javafx.concurrent.Task;
import javafx.scene.control.Hyperlink;

public class ExtractURLService extends Service<List<Hyperlink> > {

	StringProperty selectedLink = new SimpleStringProperty();

	
	private String urlString;
	Hyperlink hyperlink;

	@Override
	protected Task<List<Hyperlink>> createTask() {
		Task<List<Hyperlink>> task = new Task<List<Hyperlink>>() {

			@Override
			protected List<Hyperlink> call() throws Exception {

		return extractUrls(urlString);//übergibg List e

				
				
//				return null;
			}
			
		};

		
		return task;
	}

	public void setUrlText(String value) {
		this.urlString = value;
		System.out.println("Extract...setUrlText");
	}


	public List<Hyperlink> extractUrls(String value){
		List<Hyperlink> result = new ArrayList<Hyperlink>();
		String urlPattern = "(https?|ftp|file)://[-a-zA-Z0-9+&@#/%?=~_|!:,.;]*[-a-zA-Z0-9+&@#/%=~_|]";
		Pattern p = Pattern.compile(urlPattern, Pattern.CASE_INSENSITIVE);
		Matcher m = p.matcher(value);
		
		//Füllt die liste
		while (m.find()) {
//			selectedLink = new SimpleStringProperty();
			hyperlink = new Hyperlink();
//			System.out.println("value.substring(m.start(0), m.end(0)): "+ value.substring(m.start(0), m.end(0)));//alle ok!!
			hyperlink.setText(value.substring(m.start(0), m.end(0)));
			hyperlink.setUnderline(true);
			
			
			//--------------------------------Hyperlink pressed-------------------------
			hyperlink.setOnAction(e->{
				setSelectedLink(hyperlink.getText());//SimpleStringProperty
				e.getSource(); //System.out.println("hyperlink.getText(): " + hyperlink.getText());
				System.out.println();
				
				});
			
			result.add(hyperlink);
//			result.add(hyperlink.);
//			m.group();
		}
		
//		System.out.println(result);
		return result;
		
	}

	public final StringProperty selectedLinkProperty() {
		return this.selectedLink;
	}
	

	public final String getSelectedLink() {
		return this.selectedLinkProperty().get();
	}
	

	public final void setSelectedLink(final String selectedLink) {
		this.selectedLinkProperty().set(selectedLink);
	}
	


	

	
}
