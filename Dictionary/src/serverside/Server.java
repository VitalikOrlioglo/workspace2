package serverside;

import model.DictionaryObject;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Server {
	private static Logger log = LogManager.getRootLogger();
	
	static ServerSocket serverSocket;
	static Socket socketToServerRunnable;

	static ExecutorService serverExecutorService;

//	static DictionaryObject dictionaryObject;

	/**
	 * Поднимает сервер для работы с клентом на порту 1111  инициализирует обработчиков
	 * чтения объектов DictionaryObject из канала и
	 * передачи в канал(сокет) и ждёт обращения accept.
	 * после handShaking-а инициализирет объект DictionaryObject переданным от клиента в сокет объектом
	 * DictionaryObject (методом ObjectInputStream), вызывает метод селектор выбора операции
	 * (удаление записи, поиск определения по слову, добавление записи, корректировка определения)
	 * по флагу в самом объекте , результирующий DictionaryObject из пула потоков который запускает
	 * Runnable объект ServerRunnable в котором и происходит всё общение формы с серввером,
	 * ServerRunnable в свою очередь Callable объект - DBCallable - сервер
	 * базы данных , который выполняет действия в базе и возвращает ответ из БД в ServerRunnable в виде
	 * DictionaryObject в сокет методом ObjectOutputStream-а - writeObject.
	 * клиент принимает на своей стороне объект , расшифровывает записи в нём и отображает результат
	 * в GUI.
	 * @param args
	 */
	public static void main(String[] args) {
		try {
			log.info("Server: Main Method started");
			// создаём пул потоков для работы с сервером базы и создания подсерверов общения
			serverExecutorService = Executors.newFixedThreadPool(10);
			// открываем порт для подключения
			serverSocket = new ServerSocket(1111);
			log.debug("Server: serversocket in main method start am Port " + serverSocket.getLocalPort());
			
			// начинаем рабочий цикл по приёму подключений с запросами
			while (!serverSocket.isClosed()) {
				log.debug("Server {} wartet mit accept ", serverSocket.getLocalPort());
				socketToServerRunnable = serverSocket.accept();
				log.debug("Server wartet mit accept {} ", socketToServerRunnable.getInetAddress());
				
				// когда подключение установлено,
				// создает новый поток в котором делегирует свое общение с клиентом и базой данных к ServerRunnable
				log.info("Server: serverExecutorService in Server create new thread for ServerRunnable");
				serverExecutorService.execute(new ServerRunnable(socketToServerRunnable));
			}
			// закрываем пулл потоков
			serverExecutorService.shutdown();
		} catch (IOException e) {
			log.error(e.getMessage(), e);
		} finally {
			// закрываем сокет
			try {
				serverSocket.close();
				log.debug("Server: serverSocket is closed");
			} catch (IOException e) {
				log.error(e.getMessage(), e);
			}
		}
	}
}
