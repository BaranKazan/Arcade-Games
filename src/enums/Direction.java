package enums;

/**
 * directions for movement
 *
 */
public enum Direction {
	UP(0, -1), RIGHT(1, 0), DOWN(0, 1), LEFT(-1, 0), STOP(0, 0);

	Direction(int x, int y) {
		this.x = x;
		this.y = y;
	}
	final int x, y;
}