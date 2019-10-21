package service;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.concurrent.Service;
import javafx.concurrent.Task;
import javafx.scene.control.Hyperlink;

public class ExctractURLService extends Service<List<Hyperlink>> {

	private static final String URL_PATTERN = "(https?|ftp|file)://[-a-zA-Z0-9+&@#/%?=~_|!:,.;]*[-a-zA-Z0-9+&@#/%=~_|]"
;
	
	private String htmlSourceCode;

	private StringProperty linkProperty = new SimpleStringProperty();
	@Override
	protected Task<List<Hyperlink>> createTask() {
		// TODO Auto-generated method stub
		return new Task<List<Hyperlink>>() {

			@Override
			protected List<Hyperlink> call() throws Exception {
			//	updateMessage("ExctractURLService call...");
				List<Hyperlink> result = extractUrls(htmlSourceCode);
			//	updateMessage("ExctractURLService call end...");
				return result;
			}
		};

	}

	public String getHtmlSourceCode() {
		return htmlSourceCode;
	}

	public void setHtmlSourceCode(String htmlSourceCode) {
		this.htmlSourceCode = htmlSourceCode;
	}

	public List<Hyperlink> extractUrls(String value) {
		List<Hyperlink> result = new ArrayList<>();
		String urlPattern = URL_PATTERN;
		Pattern p = Pattern.compile(urlPattern, Pattern.CASE_INSENSITIVE);
		Matcher m = p.matcher(value);
		while (m.find()) {
			String s = m.group();
			Hyperlink hlink = new Hyperlink(s);
			hlink.setOnAction(e ->{
				linkProperty.set(s);
			});
			result.add(hlink);
		}
		return result;

	}

	public final StringProperty linkPropertyProperty() {
		return this.linkProperty;
	}
	

	public final String getLinkProperty() {
		return this.linkPropertyProperty().get();
	}
	

	public final void setLinkProperty(final String linkProperty) {
		this.linkPropertyProperty().set(linkProperty);
	}
	

}
