package serverside;

import model.DictionaryObject;

import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Server {
	ServerSocket serverSocket;
	Socket socket;

	static ExecutorService executorService;

	DictionaryObject dictionaryObject;

	/**
	 * Поднимает сервер для работы с клентом на порту 5432  инициализирует обработчиков
	 * чтения объектов <code>EntryDic</code> из канала и
	 * передачи в канал(сокет) и ждёт обращения <code>accept();</code>accept();
	 * после handShaking-а инициализирет объект EntryDic переданным от клиента в сокет объектом
	 * <code>EntryDic</code> (методом ObjectInputStream), вызывает метод селектор выбора операции
	 * (удаление записи, поиск определения по слову, добавление записи, корректировка определения)
	 * по флагу в самом объекте , результирующий EntryDic из пула нитей <code>exeDic</code> который запускает
	 * <code>Runnable</code> объект <code>CSDialog</code> в котором и происходит всё общение формы с серввером,
	 * <code>CSDialog</code> в свою очередь <code>Callable </code> объект - <code>DBServerHandler</code> - сервер
	 * базы данных , который выполняет действия в базе и возвращает ответ из БД в <code>CSDialog</code> в виде
	 * <code>EntryDic</code> в сокет методом (<code>ObjectOutputStream</code>-а - <code>writeObject();</code>)
	 * клиент принимает на своей стороне объект , расшифровывает записи в нём и отображает результат
	 * в GUI.
	 * @param args
	 */
	public static void main(String[] args) {
		// создаём пул потоков для работы с сервером базы и создания подсерверов общения
		executorService = Executors.newFixedThreadPool(10);

	}

}
