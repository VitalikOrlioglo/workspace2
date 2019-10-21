package service;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javafx.concurrent.Service;
import javafx.concurrent.Task;

public class ExctractURLService extends Service<List<String>> {

	private static final String URL_PATTERN = "(https?|ftp|file)://[-a-zA-Z0-9+&@#/%?=~_|!:,.;]*[-a-zA-Z0-9+&@#/%=~_|]"
;
	
	private String htmlSourceCode;

	@Override
	protected Task<List<String>> createTask() {
		// TODO Auto-generated method stub
		return new Task<List<String>>() {

			@Override
			protected List<String> call() throws Exception {
			//	updateMessage("ExctractURLService call...");
				List<String> result = extractUrls(htmlSourceCode);
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

	public List<String> extractUrls(String value) {
		List<String> result = new ArrayList<String>();
		String urlPattern = URL_PATTERN;
		Pattern p = Pattern.compile(urlPattern, Pattern.CASE_INSENSITIVE);
		Matcher m = p.matcher(value);
		while (m.find()) {
			result.add(m.group());
		}
		return result;

	}

}
