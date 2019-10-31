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
	 * Initialize the static variable <code> socketFromDictator </code>.
	 * Further when initializing CSDialog-and create links through which will communicate with the applicant in client
	 * <code> socketFromDictator </code> (<code> objectInputStream and objectOutputStream <code> </code> </code>), as well as create
	 * thread pooling <code> exe </code>, which will continue to put every single request from a single
	 * client for simultaneous processing.
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
	 * When an object implementing interface <code>Runnable</code> is used
	 * to create a thread, starting the thread causes the object's
	 * <code>run</code> method to be called in that separately executing
	 * thread.
	 * <p>
	 * The general contract of the method <code>run</code> is that it may
	 * take any action whatsoever.
	 * <p>
	 * <p>
	 * Создаём цикл - "пока сокет не закрыт" в котором запускаем ожидание поступления данных в канал <code>socket</code>
	 * <code>while(objectInputStream.available() == 0)</code> который усыпляет нить на 303 милисекунды и обновляет
	 * информацию  поступивших данных , если в одном из проходов данные обнаружены - (т.к. мы знаем что в канал клиент
	 * записывает данные типа - <code>EntryDic</code> то считываем  сериализуемый объект методом <code>readObject()</code>
	 * ссылки <code>objectInputStream</code>, которая отвечает за чтение из канала <code>socket</code> приводим его
	 * явным образом к типу - <code>EntryDic</code> для возможности получения доступа к его методам и переменным.
	 * Далее в объект - типа - <code>Future</code> - <code>sqlReply</code> сохраняем возвращаемую <code>Callable</code>
	 * объектом <code>DBServerHandler</code>  ссылку типа <code>EntryDic</code> с помощью метода <code>.get()</code> и
	 * явного приведения к типу - <code>EntryDic</code>.
	 * Далее через канал <code>objectOutputStream</code> отправляем <code>.writeObject()</code> сериализуемый объект
	 * типа - <code>EntryDic</code> обратно клиенту в <code>socket</code>.
	 * Далее происходит десириализация и обработка полей на стороне клиента <code>Main</code>.
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
