package serverside;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import model.DictionaryObject;

public class ServerRunnable implements Runnable {
	private static Logger log = LogManager.getRootLogger();
	
	Socket serverRunnableSocket;
	DictionaryObject dictionaryObject;
	DictionaryObject replyDictionaryObject;
	ExecutorService exeService;
	
	/**
	 * 
	 * @param socketFromServer
	 */
	public ServerRunnable(Socket socketFromServer) {
		this.serverRunnableSocket = socketFromServer;
		log.info("ServerRunnable: constructor starts , socketFromServer initiated , io channels for server creation starts");
		log.debug("ServerRunnable: data on Socket from Server: \n" + "Port: " + serverRunnableSocket.getLocalPort() +
				" Socket Channel: " + serverRunnableSocket.getChannel() + " RemoteSocketAddress: " + serverRunnableSocket.getRemoteSocketAddress());
		// инициализация нового пулла потоков для запроса к базе данных
		exeService = Executors.newSingleThreadExecutor();
		log.info("ServerRunnable: SingleThreadExecutor for querying the database created");
		log.info("ServerRunnable: Constructor ended");
	}
	
	/**
	 * 
	 */
	@Override
	public void run() {
		log.info("ServerRunnable: run() method starts");
		try(ObjectOutputStream out = new ObjectOutputStream(serverRunnableSocket.getOutputStream());
			ObjectInputStream in = new ObjectInputStream(serverRunnableSocket.getInputStream()) ) {
			log.info("ServerRunnable: in/out objects created in run() method");
			log.debug("ServerRunnable : in/out objects in run method: "
			+ " input name: " + in.getClass().getName() + " output name: " + out.getClass().getName());
			
			// сохраняем полученный объект от ClientCallable в свою переменную
			dictionaryObject = (DictionaryObject) in.readObject();
			log.info("ServerRunnable : DictionaryObject from channel from ClientCallable class to ServerRunnable received");
			log.debug("ServerRunnable: dictionaryObject fields: "
					+ "flag - " + dictionaryObject.flag + " word - " + dictionaryObject.word + " def - " + dictionaryObject.definition);
			Thread.sleep(5000);
			
			// с помощью Callable объекта для общения с базой - DBCallable - запрашиваем базу, передавая
			// в конструктор DBCallable обьект dictionaryObject
			// получаем обьект sqlReply от базы
			Future<DictionaryObject> sqlReply = exeService.submit(new DBCallable(dictionaryObject));
			log.debug("ServerRunnable: object sqlReply from DBCallable to ServerRunnable received. DictionaryObject is: "
			+ "Flag - " + sqlReply.get().flag + " Word - " + sqlReply.get().word + " Def - " + sqlReply.get().definition);
			
			replyDictionaryObject = sqlReply.get();
//			log.debug("ServerRunnable : replyDictionaryObject: " + "Flag - " + replyDictionaryObject.getFlag());
			
			// пишем полученный DictionaryObject от DBCallable в канал сокета общения с ClientCallable
			if (!serverRunnableSocket.isClosed()) {
				out.writeObject(replyDictionaryObject);
				out.flush();
				Thread.sleep(5000);
				log.info("ServerRunnable : reply DictionaryObject to ClientCallable from ServerRunnable sent back in socket");
				log.info("ServerRunnable: waits . . .");
				Thread.sleep(1000);
				
				// закрываем executor и socket после выполнения/транзакции
				exeService.shutdown();
				serverRunnableSocket.close();
				log.info("ServerRunnable: executor and socket closed");
			} else if(serverRunnableSocket.isClosed()){
				log.info("ServerRunnable : socket suddenly dead...");
			}
		} catch (IOException e) {
			log.error("ServerRunnable: IO troubles \n" + e.getMessage());
			System.exit(1);
		} catch (InterruptedException e) {
			log.error("ServerRunnable: Thread.sleep() troubles \n" + e.getMessage());
			System.exit(1);
		} catch (ClassNotFoundException e) {
			log.error("ServerRunnable: sqlReply.get() troubles \n" + e.getMessage());
			System.exit(1);
		} catch (ExecutionException e) {
			log.error("ServerRunnable: exeService.submit troubles \n" + e.getMessage());
			System.exit(1);
		} finally {
			try {
				exeService.shutdown();
				serverRunnableSocket.close();
			} catch (IOException e) {
				log.error("ServerRunnable: finally closing of executor and socket troubles \n" + e.getMessage());
				System.exit(1);
			}
		}
	}
}
