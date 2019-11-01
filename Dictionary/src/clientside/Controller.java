package clientside;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

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
	
	ExecutorService controllerExecutorService = Executors.newFixedThreadPool(15);

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
	 * 
     * @param event
     */
    @FXML
    void searchWord(ActionEvent event) {
    	log.info("Controller: searchWord is pressed, start searching");
    	try {
        	String searchDefByWord = textSearch.getText();
        	log.info("Controller: looking for definition of word - " + searchDefByWord);
        	DictionaryObject searchDictionaryObject = new DictionaryObject(searchDefByWord, "null", 0);
//        	searchDictionaryObject.setWord(searchDefByWord);
//        	searchDictionaryObject.setDefinition("none");
        	log.debug("Controller: data in DictionaryObject: "
        	+ "Flag - " + searchDictionaryObject.flag + " Word - " + searchDictionaryObject.word + " Def - " + searchDictionaryObject.definition);
        	
			Thread.sleep(5000);
			
			log.info("Controller: Controller send DictionaryObject to handle in ClientCallable class in executor service");
			Future<DictionaryObject> replyFuture = controllerExecutorService.submit(new ClientCallable(searchDictionaryObject));
			
			log.debug("Controller: submit thread to ClientCallable with (searchDictionaryObject) done, reply received - "
			+ replyFuture.getClass().getCanonicalName());
			
			log.info("Controller: calling get() method on replyFuture");
			// через метод get получаем доступ к результату
			DictionaryObject reply = replyFuture.get();
			
			String replyDefinition = reply.definition;
			log.debug("Controller: " + replyDefinition + " reply found");
			textAreaSearch.setText(replyDefinition);
		} catch (InterruptedException e) {
			log.error("Controller: Thread.sleep() troubles \n" + e.getMessage());
		} catch (ExecutionException e) {
			log.error("Controller: replyFuture.get(); troubles \n" + e.getMessage());
		}
    	
    }
    
    @FXML
    void clearSearch(ActionEvent event) {
    	log.info("clearSearch");
    	textSearch.clear();
    	textAreaSearch.clear();
    }
    
    /**
     * Обработчик события по нажатию кнопки addWordDef
     * @param event
     */
    @FXML
    void addWordDef(ActionEvent event) {
    	log.info("Add Button is pressed");
		try {
			String addWord = textAddWord.getText();
	    	String addDefinition = textAddDef.getText();
	    	log.info("Controller: Controller send DictionaryObject to handle in ClientCallable class in executor service");
	    	
	    	Future<DictionaryObject> replyFuture = controllerExecutorService.submit(new ClientCallable(new DictionaryObject(addWord, addDefinition, 1)));
			DictionaryObject reply = replyFuture.get();
	    	String replyWord = reply.word;
	    	String replyDefinition = reply.definition;
	    	
	    	textAddWord.setText(replyWord);
	    	textAddDef.setText(replyDefinition);
		} catch (InterruptedException e) {
			log.error("Controller: controllerExecutorService.submit troubles \n" + e.getMessage());
		} catch (ExecutionException e) {
			log.error("Controller: replyFuture.get(); troubles \n" + e.getMessage());
		}
    	
    	
    }
    
    @FXML
    void clearAdd(ActionEvent event) {
    	log.info("clearAdd");
    	textAddWord.clear();
    	textAddDef.clear();
    }
    
    /**
     * Обработчик события по нажатию кнопки editWordDef
	 * 
     * @param event
     */
    @FXML
    void editWordDef(ActionEvent event) {
    	log.info("Edit Button is pressed");
    	try {
    		String editWord = textEditWord.getText();
        	String editDefinition = textEditDef.getText();
        	log.info("Controller: Controller send DictionaryObject to handle in ClientCallable class in executor service");
			
        	Future<DictionaryObject> replyFuture = controllerExecutorService.submit(new ClientCallable(new DictionaryObject(editWord, editDefinition, 10)));
			
			DictionaryObject reply = replyFuture.get();
			String replyWord = reply.word;
			String replyDefinition = reply.definition;
			
			textEditWord.setText(replyWord);
			textEditDef.setText(replyDefinition);
		} catch (InterruptedException e) {
			log.error("Controller: controllerExecutorService.submit troubles \n" + e.getMessage());
		} catch (ExecutionException e) {
			log.error("Controller: replyFuture.get(); troubles \n" + e.getMessage());
		}
    }

    @FXML
    void clearEdit(ActionEvent event) {
    	log.info("clearEdit");
    	textEditWord.clear();
    	textEditDef.clear();
    }
    
    /**
     * Обработчик события по нажатию кнопки deleteWordDef
	 * 
     * @param event
     */
    @FXML
    void deleteWordDef(ActionEvent event) {
    	log.info("Delete Button is pressed");
    	try {
    		String delWord = textDeleteWordDef.getText();
        	log.info("Controller: Controller send DictionaryObject to handle in ClientCallable class in executor service");
			
        	Future<DictionaryObject> replyFuture = controllerExecutorService.submit(new ClientCallable(new DictionaryObject(delWord, "none", -1)));
			
        	DictionaryObject reply = replyFuture.get();
			String replyWord = reply.definition;
			
			textDeleteWordDef.setText(replyWord);
    	} catch (InterruptedException e) {
    		log.error("Controller: controllerExecutorService.submit troubles \n" + e.getMessage());
		} catch (ExecutionException e) {
			log.error("Controller: replyFuture.get(); troubles \n" + e.getMessage());
		}
    }

    @FXML
    void initialize() {}
}
