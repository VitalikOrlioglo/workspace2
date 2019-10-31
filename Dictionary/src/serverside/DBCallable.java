package serverside;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
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
	 * Конструктор. Присваивает переданный в параметрах<code>entryDic</code> статическому полю с таким же названием.
	 * Создаёт лог файл, переменную отвечающую за логирование, загружает драйвер
	 * базы с которой будет работать объект, соединение и создаёт предскомпилированные запросы для отправки команд базе.
	 * 
	 * @param dictionaryObject
	 */
	public DBCallable(DictionaryObject dictionaryObject) {
		this.dictionaryObject = dictionaryObject;
		log.info("DBCallable: Constructor starts");
		try {
			log.info("DBCallable: DB driver loading starts");
//			Class.forName("org.postgresql.Driver");
			connection = DriverManager.getConnection(URL, "root", "");
			log.info("DBCallable: DB driver loaded");
			
			searchStatement = connection.prepareStatement("SELECT beschreibung FROM designpattern WHERE UPPER(name) LIKE UPPER(?);");
			addStatement = connection.prepareStatement("INSERT INTO designpattern(name, beschreibung) VALUES(?, ?);");
			editStatement = connection.prepareStatement("UPDATE designpattern SET beschreibung = ? WHERE UPPER(name) LIKE UPPER(?);");
			deleteStatement = connection.prepareStatement("DELETE FROM designpattern WHERE UPPER(name) LIKE UPPER(?);");
			
			log.info("DBCallable : preparedstatements & connection created");
		} catch (SQLException e) {
			log.error("DBCallable: DB Connection troubles \n" + e.getMessage());
			System.exit(1);
		}	
	}
	
	/**
	 * Возвращающий метод - <code>call</code> вызывает - метод <code>toDoSelector</code>, которому в качестве аргумента
	 * передаёт ссылочную переменную типа - <code>EntryDic</code> полученную в консрукторе <code>DBServerHandler</code>.
	 * <code>DBServerHandler</code> в свою очередь возвращает другой <code>EnryDic</code> для ответной передачи его
	 * запросившему клиенту в объект <code>CSDialog</code>.
	 * <p>
	 * Предусмотрено логирование аварийного завершения выполнения операции в файл <code>"DBServerHandler_error_log.txt"</code>
	 * <code>file</code> переменная.
	 * 
	 * @return DictionaryObject from DB
	 */
	@Override
	public DictionaryObject call() throws Exception {
		log.info("DBCallable : call method in DBCallable starts");
		DictionaryObject dictionaryObjectFrom;
		dictionaryObjectFrom = selector(dictionaryObject);
		log.debug("DBCallable : answer in call method from method selector received." + "DictionaryObject is: "+
				"Flag - " + dictionaryObjectFrom.flag + " Word - " + dictionaryObjectFrom.word + " Def - " + dictionaryObjectFrom.definition);
		
		log.info("DBCallable: DictionaryObject from call method in DBCallable returning to ServerRunnable");
		return dictionaryObjectFrom;
	}
	
	/**
	 * Метод - селектор - получает параметром объект типа <code>EntryDic</code>, сохраняет его в своей локальной
	 * переменной и проверяет чему равен переданный с объектом флаг операции(удалить - <code>DELETE</code> существующую
	 * запись, 	найти - <code>SEARCH</code> определение по ключевому! слову,  добавить - <code>ADD</code> новую запись,
	 * корректировать существующую запись - <code>REDUCT</code>), и вызывает соответствующие методы, передавая им в
	 * параметры копию ссылки на полученный объект типа <code>EntryDic</code>.
	 * <p>
	 * Предусмотрено логирование аварийного завершения выполнения операции в файл <code>"DBServerHandler_error_log.txt"</code>
	 * <code>file</code> переменная.
	 * 
	 * @param dictionaryObject
	 * @return
	 */
	private DictionaryObject selector(DictionaryObject dictionaryObject) {
		return null;
	}
	
	
}
