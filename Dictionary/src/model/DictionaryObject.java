package model;

import java.io.Serializable;

public class DictionaryObject implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public String word;
	public String definition;
	public int flag;
	public DictionaryObject() {}
	
	/**
	 * 
	 * @param word
	 * @param definition
	 * @param flag
	 */
	public DictionaryObject(String word, String definition, int flag) {
		this.word = word;
		this.definition = definition;
		this.flag = flag;
	}

	public String getWord() {
		return word;
	}

	public void setWord(String word) {
		this.word = word;
	}

	public String getDefinition() {
		return definition;
	}

	public void setDefinition(String definition) {
		this.definition = definition;
	}

	public int getFlag() {
		return flag;
	}

	public void setFlag(int flag) {
		this.flag = flag;
	}
	
}
