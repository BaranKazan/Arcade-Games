package factory;

import snakeGame.BasicEdible;
import snakeGame.Edible;
import snakeGame.Point;
import snakeGame.SnakeCommons;
import snakeGame.SnakeGamePanel;
import snakeGame.SnakeHead;
import snakeGame.SnakePart;

public class SnakeFactory {
	
private static final SnakeFactory instance = new SnakeFactory();
	
	public static SnakeFactory getInstance() {
		return instance;
	}
	
	public SnakeHead createSnakeHead() {
		return new SnakeHead();
	}
	public SnakeGamePanel createSnakeGame() {
		return new SnakeGamePanel(SnakeCommons.BOARDWIDTH, SnakeCommons.BOARDHEIGHT); 
	}
	
	public Edible createBasicEdible(int height, int width, int pixelSize) {
		return new BasicEdible(height, width, pixelSize);
	}
	
	public Point createPoint(int x, int y) {
		return new Point(x, y);
	}
	
	public SnakePart createSnakePart(Point point) {
		return new SnakePart(point);
	}
}
