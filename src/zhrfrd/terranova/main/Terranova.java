package zhrfrd.terranova.main;

public class Terranova {
	public static void main(String[] args) {
		Game game = new Game();
		
		game.setFrameGameLayout();
		game.startThreadGame();
	}
}