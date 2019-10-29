package app;

public class MessageBoardTest {

	public static void main(String[] args) {
		MessageBoard messageBoard = new MessageBoard();
		Student bob = new Student();
		Student jak = new Student();
		
		messageBoard.addObserver(bob);
		messageBoard.addObserver(jak);
		messageBoard.changeMessage("Cto za hreni!");
	}

}
