package serverside;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.concurrent.Callable;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import model.DictionaryObject;

public class DBCallable implements Callable<DictionaryObject>{
	private static Logger log = LogManager.getRootLogger();
	private final String URL ="jdbc:mysql://localhost:3306/java2";
	
	DictionaryObject dictionaryObject;
	Connection connection;
	
	PreparedStatement searchStatement;
	PreparedStatement addStatement;
	PreparedStatement editStatement;
	PreparedStatement deleteStatement;
	
	/**
	 * Конструктор загружает драйвер базы с которой будет работать объект, соединение 
	 * и создаёт предскомпилированные запросы для отправки команд базе.
	 * 
	 * @param dictionaryObject
	 */
	public DBCallable(DictionaryObject dictionaryObject) {
		this.dictionaryObject = dictionaryObject;
		log.info("DBCallable: Constructor starts");
		try {
			log.info("DBCallable: DB driver loading starts");
			connection = DriverManager.getConnection(URL, "root", "");
			log.info("DBCallable: DB driver loaded");
			
			searchStatement = connection.prepareStatement("SELECT beschreibung FROM designpattern WHERE UPPER(name) LIKE UPPER(?);");
			addStatement = connection.prepareStatement("INSERT INTO designpattern(name, beschreibung) VALUES(?, ?);");
			editStatement = connection.prepareStatement("UPDATE designpattern SET beschreibung = ? WHERE UPPER(name) LIKE UPPER(?);");
			deleteStatement = connection.prepareStatement("DELETE FROM designpattern WHERE UPPER(name) LIKE UPPER(?);");
			
			log.info("DBCallable : preparedstatements & connection created");
		} catch (SQLException e) {
			log.error("DBCallable: DB Connection/Driver loading troubles \n" + e.getMessage());
			System.exit(1);
		}	
	}
	
	/**
	 * Возвращающий метод call вызывает метод Selector которому в качестве аргумента
	 * передаёт ссылочную переменную типа dictionaryObject полученную в консрукторе DBCallable.
	 * DBCallable в свою очередь возвращает другой dictionaryObject для ответной передачи его
	 * запросившему клиенту в объект ServerRunnable.
	 * 
	 * @return DictionaryObject from DB
	 */
	@Override
	public DictionaryObject call() {
		log.info("DBCallable : call method in DBCallable starts");
		DictionaryObject dictionaryObjectFrom = null;
		try {
			dictionaryObjectFrom = selector(dictionaryObject);
			log.debug("DBCallable : answer in call method from method selector received." + "DictionaryObject is: "+
					"Flag - " + dictionaryObjectFrom.flag + " Word - " + dictionaryObjectFrom.word + " Def - " + dictionaryObjectFrom.definition);
		} catch (InterruptedException e) {
			log.error("DBCallable: Thread.sleep(); troubles \n" + e.getMessage());
		}
		log.info("DBCallable: DictionaryObject from call method in DBCallable returning to ServerRunnable");
		return dictionaryObjectFrom;
	}
	
	/**
	 * Метод - селектор - получает параметром объект типа dictionaryObject сохраняет его в своей локальной
	 * переменной и проверяет чему равен переданный с объектом флаг операции(-1 = DELETE, 0 = SEARCH, 1 = Add,
	 * 10 = Edit), и вызывает соответствующие методы, передавая им в
	 * параметры копию ссылки на полученный объект типа dictionaryObject.
	 * 
	 * @param dictionaryObject
	 * @return
	 */
	private DictionaryObject selector(DictionaryObject dictionaryObject) throws InterruptedException {
		log.info("DBCallable : selector works");
		DictionaryObject dicIn = dictionaryObject;
		DictionaryObject dicOut = null;
		int flag = dictionaryObject.flag;

		log.debug("DBCallable: input dictionaryObject in selector in DBCallable: "
				+ "Flag - " + dicIn.flag + " Word - " + dicIn.word + " Def - " + dicIn.definition);
		Thread.sleep(5000);
		switch (flag) {
			case -1:
				log.info("DBCallable: DELETE - flag in selector in DBCallable, try to delete entry");
				log.info("DBCallable: deleting entry with word - " + dicIn.getWord());
				dicOut = delete(dicIn);
				break;
			case 0:
				log.info("DBCallable: SEARCH - flag in selector in DBCallable, try to SEARCH entry");
				log.info("DBCallable: searching entry with word - " + dicIn.getWord());
				dicOut = search(dicIn);
				log.debug("DBCallable: return into method selector from method  search: "
						+ "Founded Flag - " + dicOut.flag + " Founded Word - " + dicOut.word + " Founded Def - " + dicOut.definition);
				break;
			case 1:
				log.info("DBCallable: ADD - flag in selector in DBCallable, try to ADD entry");
				log.debug("DBCallable : adding entry with word - " + dicIn.word);
				dicOut = add(dicIn);
				break;
			case 10:
				log.info("DBCallable: EDIT - flag in method selector in DBCallable, try to EDIT");
				log.debug("DBCallable: editing entry with word - " + dicIn.word);
				dicOut = edit(dicIn);
				break;
		}

		log.debug("DBCallable: ready to return answer from method selector to call method. dictionaryObject(dicOut): "
				+"Flag - "+ dicOut.flag + " Word - " + dicOut.word + " Def - " + dicOut.definition);
		return dicOut;
	}
	
	/**
	 * Edit Query
	 * 
	 * @param dicIn
	 * @return
	 */
	private DictionaryObject edit(DictionaryObject dicIn) {
		log.info("DBCallable : starts edit method");
		DictionaryObject dicEdit;
		String   tmpWord       = dicIn.word;
		String   tmpDefinition = dicIn.definition;
		
		try {
			editStatement.setString(1, tmpDefinition);
			editStatement.setString(1, tmpWord);
			editStatement.executeUpdate();
			log.info("DBCallable: editStatement sent in edit method ");
			
			editStatement.close();
			connection.close();
			log.info("DBCallable: editStatement & connection closed in edit method");
			
			
			dicEdit = new DictionaryObject("ok, edited", "ok, edited", 10);
			return dicEdit;
		} catch (SQLException e) {
			log.error("DBCallable: SQL query or editStatement.close(); troubles\n" + e.getMessage());
			return new DictionaryObject("error editing", "error editing", 10);
		} finally {
			try {
				editStatement.close();
				connection.close();
			} catch (SQLException e) {
				log.error("DBCallable: DB resources .close(); or connection.close(); troubles\n" + e.getMessage());
			}
		}
	}
	
	/**
	 * Add Query
	 * 
	 * @param dicIn
	 * @return
	 */
	private DictionaryObject add(DictionaryObject dicIn) {
		log.info("DBCallable : starts add method");
		DictionaryObject dicAdd;
		String   tmpWord       = dicIn.word;
		String   tmpDefinition = dicIn.definition;
		
		try {
			addStatement.setString(1, tmpWord);
			addStatement.setString(2, tmpDefinition);
			addStatement.executeUpdate();
			log.info("DBCallable: addStatement sent in add method ");
			
//			dicAdd = new DictionaryObject("ok, " + tmpWord + " added und ", tmpDefinition + "added, flag is ", 1);
			dicAdd = new DictionaryObject("ok, added", "ok, added ", 1);
			
			addStatement.close();
			connection.close();
			log.info("DBCallable: addStatement & connection closed in add method");
			
			return dicAdd;
		} catch (SQLException e) {
			log.error("DBCallable: SQL query or addStatement.close(); troubles\n" + e.getMessage());
			return new DictionaryObject("error adding", "error adding", 1);
		} finally {
			try {
				addStatement.close();
				connection.close();
			} catch (SQLException e) {
				log.error("DBCallable: DB resources .close(); or connection.close(); troubles\n" + e.getMessage());
			}
		}
	}
	
	/**
	 * Search Query
	 * 
	 * @param dicIn
	 * @return
	 */
	private DictionaryObject search(DictionaryObject dicIn) {
		log.info("DBCallable : starts search method");
		String tmpWord = dicIn.word;
		DictionaryObject dicSearch = new DictionaryObject("0", "0", 0);
		String tmpDefinitionBack;
		try {
			searchStatement.setString(1, tmpWord);
			log.info("DBCallable: searchStatement in search method");
			
			ResultSet resultSearch = searchStatement.executeQuery();
			resultSearch.next();
			tmpDefinitionBack = resultSearch.getString("beschreibung");
			
			log.debug("DBCallable: resultSearch received in search method tmpDefinitionBack - " + tmpDefinitionBack);
			
			resultSearch.close();
			searchStatement.close();
			
			log.info("DBCallable : resultSearch & searchStatement were closed in search method");
			
			dicSearch.flag = 0;
			dicSearch.word = tmpWord;
			dicSearch.definition = tmpDefinitionBack;
			log.info("DBCallable: definition from DB " + tmpDefinitionBack + " returning from method search to method selector in DBCallable");
			
			return dicSearch;
		} catch (SQLException e) {
			log.error("DBCallable: SQL query or searchStatement.close(); troubles\n" + e.getMessage());
			return new DictionaryObject("error searching", "error searching", 0);
		} finally {
			try {
				searchStatement.close();
				connection.close();
			} catch (SQLException e) {
				log.error("DBCallable: DB resources .close(); or connection.close(); troubles\n" + e.getMessage());
			}
		}
	}

	/**
	 * Delete Query
	 * 
	 * @param dicIn
	 * @return
	 */
	private DictionaryObject delete(DictionaryObject dicIn) {
		log.debug("DBCallable : starts delete method");
		DictionaryObject dicDelete;
		String tmpWord = dicIn.word;
		try {
			deleteStatement.setString(1, tmpWord);
			deleteStatement.executeUpdate();
			
			log.info("DBCallable: deleteStatement send in delete method");
			deleteStatement.close();
			connection.close();
			
			log.info("DBCallable: deleteStatement & connection closed in delete method");
			dicDelete = new DictionaryObject("ok, deleted", "ok, deleted", -1);
			
			log.debug("DBCallable: DictionaryObject reply from DBCallable after delete method received = " +
					dicDelete.flag + " "+ dicDelete.word + " " + dicDelete.definition);
			
			return dicDelete;
		} catch (SQLException e) {
			log.error("DBCallable: SQL query or deleteStatement.close(); troubles\n" + e.getMessage());
			return new DictionaryObject("error deleting", "error deleting", -1);
		} finally {
			try {
				deleteStatement.close();
				connection.close();
			} catch (SQLException e) {
				log.error("DBCallable: DB resources .close(); or connection.close(); troubles\n" + e.getMessage());
			}
		}
	}
}
