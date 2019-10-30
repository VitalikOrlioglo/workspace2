package ui;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Tab;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import model.DictionaryObject;

public class Controller {
	private static Logger log = LogManager.getRootLogger();
	
	ExecutorService executorService = Executors.newFixedThreadPool(15);

    @FXML
    private Tab tabSearch;

    @FXML
    private TextField textSearch;

    @FXML
    private TextArea textAreaSearch;

    @FXML
    private Tab tabAdd;

    @FXML
    private TextField textAddWord;

    @FXML
    private TextArea textAddDef;

    @FXML
    private Tab tabEdit;

    @FXML
    private TextField textEditWord;

    @FXML
    private TextArea textEditDef;

    @FXML
    private Tab tabDelete;

    @FXML
    private TextField textDeleteWordDef;
    
    /**
     * Обработчик события по нажатию кнопки searchWord.
	 * создаётся новая ссылочная переменная типа DictionaryObject с именем dictionaryObject куда в
	 * качестве одного из параметров передаётся слово textSearch, определение которому нужно найти в словаре.
	 * 
	 * Далее dictionaryObject  передаётся аргументом в конструктор Callable  объекта
	 * ClientCallable , который в свою очередь создаётся в параллельной нити в пуле
	 * <code>ExecutorService</code> с именем <code>executorService</code> и возвращает на выходе своего метода <code>call</code>
	 * объект заявленный в <code>Future</code>  - <code>EntryDic</code> во временную переменную -
	 * <code>ReplyFu</code>.
	 * <p>
	 * Далее из полученного в ответе объекта <code>ReplyFu</code> через его метод - <code>get()</code> получаем
	 * <code>EntryDic</code> а из него через внутренние методы геттеры необходимое определение по заданному в поиске слову,
	 * сохраняем его во временную переменную <code>replyDefine</code> типа <code>String</code> и назначаем её в объект
	 * формы - <code>txtAreaSearch</code> методом <code>setText()</code> - выводим определение в ожидаемом поле формы.
	 * 
     * @param event
     */
    @FXML
    void searchWord(ActionEvent event) {
    	log.info("Controller: searchWord is pressed, start searching");
    	String searchDefByWord = textSearch.getText();
    	log.info("Controller: looking for definition of word - " + searchDefByWord);
    	DictionaryObject dictionaryObject = new DictionaryObject(word, definition, flag):
    }
    
    @FXML
    void clearSearch(ActionEvent event) {

    }
    
    @FXML
    void addWordDef(ActionEvent event) {
    	
    }

    @FXML
    void clearAdd(ActionEvent event) {

    }
    
    @FXML
    void editWordDef(ActionEvent event) {

    }

    @FXML
    void clearEdit(ActionEvent event) {

    }

    @FXML
    void deleteWordDef(ActionEvent event) {

    }

    @FXML
    void initialize() {}
}
