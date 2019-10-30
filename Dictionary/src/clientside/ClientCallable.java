package clientside;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.concurrent.Callable;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import model.DictionaryObject;

public class ClientCallable implements Callable<DictionaryObject>{
	private static Logger log = LogManager.getRootLogger();
	
	DictionaryObject dictionaryObject;
	Socket clientSocket;
	ObjectInputStream in;
	ObjectOutputStream out;
	
	/**
	 * @param dictionaryObject
	 * @throws InterruptedException 
	 */
	public ClientCallable(DictionaryObject dictionaryObject) throws InterruptedException {
		this.dictionaryObject = dictionaryObject;
		log.info("ClientCallable : Constructor starts: DictionaryObject iniated in ClientCallable constructor, socket creation");
		log.debug("ClientCallable: dictionaryObject from Controller in ClientCallable received: "
				+ "flag - " + this.dictionaryObject.flag + " word - " + this.dictionaryObject.word + " def - " + this.dictionaryObject.definition);
		Thread.sleep(5000);
		try {
			clientSocket = new Socket("localhost", 1111);
			out = new ObjectOutputStream(clientSocket.getOutputStream());
			in = new ObjectInputStream(clientSocket.getInputStream());
		} catch (UnknownHostException e) {
			log.error(e.getMessage(), e);
		} catch (IOException e) {
			log.error(e.getMessage(), e);
		}
		log.info("ClientCallable: Client's Socket and in/out channels in constructor created");
		log.info("ClientCallable: Constructor ended");
		
	}

	/**
	 * Перегруженный метод call объявляет и инициализирует сокет clientSocket для обработки
	 * поступившего на входе в конструктор dictionaryObject. Объявляет каналы обмена сообщения.
	 * 
	 * @return computed reply from Server
	 * @throws Exception if unable to compute a result
	 */
	@Override
	public DictionaryObject call() throws Exception {
		log.info("ClientCallable: Call method starts");
		try {
			log.debug("ClientCallable: DictionaryObject try to write from ClientCallable to ServerRunnable in channel "
					+ "flag - " + this.dictionaryObject.flag + " word - " + this.dictionaryObject.word + " def - " + this.dictionaryObject.definition);
			
// передаём в канал для сервера объект полученный из контроллера
			out.writeObject(dictionaryObject);
			out.flush();
			log.info("ClientCallable : DictionaryObject wrote in socket channel to ServerRunnable, while loop waits for "
	                + "reply from ServerRunnable");
			
// дожидаемся ответа
			
			log.info("ClientCallable: reply from ServerRunnable received");
// возвращаем полученный из сервера объект содержащий внутри себя ответ
			DictionaryObject replyFromServer = (DictionaryObject)in.readObject();
			log.debug("ClientCallable: DictionaryObject = " + "flag - " + this.dictionaryObject.flag + " word - " + this.dictionaryObject.word + " def - " + this.dictionaryObject.definition);
			
			
			return replyFromServer;
		} catch (IOException e) {
// в случае ошибки соединения или чтения/записи объекта в канал возвращаем ошибку
			log.error(e.getMessage(), e);
			return new DictionaryObject("error", "error", 2);
		} catch (Exception e) {
// в случае ошибки соединения или чтения/записи объекта в канал возвращаем ошибку
			log.error(e.getMessage(), e);
			return new DictionaryObject("error", "error", 2);
		}
	}

}
