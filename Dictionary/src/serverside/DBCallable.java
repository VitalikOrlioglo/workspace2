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
			log.error("DBCallable: DB Connection/Driver loading troubles \n" + e.getMessage());
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


	private DictionaryObject edit(DictionaryObject dicIn) {
		DictionaryObject dicEdit;
		return dicEdit;
	}

	private DictionaryObject add(DictionaryObject dicIn) {
		DictionaryObject dicAdd;
		return dicAdd;
	}

	private DictionaryObject search(DictionaryObject dicIn) {
		DictionaryObject dicSearch;
		return dicSearch;
	}

	/**
	 * SQL запрос к таблице <code>"word_define"</code> в базе данных с именем - <code>"dictionary"</code>, в случае
	 * удачного удаления, выполняется код в фигурных скобках в которых происходит <code>return </code>преобразованного
	 * <code>EntryDic</code> в котором флаг операции <code>flag</code> не изменяется, а в полях <code>word </code>и
	 * <code>definition</code> пишется "ок " - подтверждение удачного выполнения удаления, в случае если удаление не
	 * произошло то выполнение из круглых скобок не попадает в фигурные, а падает в (<code>SQLException</code>)
	 * ниже , в котором происходит формирование нового (<code>new EntryDic();</code>)в котором флаг операции
	 * не меняется, а в полях word и definition выводится - error.
	 * <p>
	 * Предусмотрено логирование аварийного завершения выполнения операции в файл <code>"DBServerHandler_error_log.txt"</code>
	 * <code>file</code> переменная.
	 * @param dicIn
	 * @return
	 */
	private DictionaryObject delete(DictionaryObject dicIn) {
		log.debug("DBCallable : starts delete method");
		DictionaryObject dicDelete;

		return dicDelete;
	}


}
