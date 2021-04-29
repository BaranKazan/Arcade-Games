package shooterGame;

/**
 * 
 * common values for variables
 */
public interface Commons {

	int TOP_FIVE = 5;
	int BULLET_ANGLE = 270;
	int BULLET_SIZE = 4;
	int BULLET_SPEED = 8;
	
	int ENEMY_BASIC_SPEED = 1;
	int ENEMY_BASIC_SIZE = 6;
	int ENEMY_BASIC_HEALTH = 1;
	int ENEMY_BASIC_POINTS = 1;
	
	int ENEMY_BOSS_SPEED = 2;
	int ENEMY_BOSS_SIZE = 35;
	int ENEMY_BOSS_HEALTH = 50;
	int ENEMY_BOSS_EXPLODE_AMOUNT = 8;
	int ENEMY_BOSS_POINTS = 5;
	int ENEMY_HIT_DELAY = 50;
	
	int ENEMY_HEALTH_SPEED = 1;
	int ENEMY_HEALTH_SIZE = 15;
	int ENEMY_HEALTH_HEALTH = 5;
	int ENEMY_HEALTH_EXPLODE_AMOUNT = 2;
	
	int ENEMY_SPLIT_SPEED = 1;
	int ENEMY_SPLIT_SIZE = 8;
	int ENEMY_SPLIT_HEALTH = 1;
	int ENEMY_SPLIT_EXPLODE_AMOUNT = 2;
	
	int WIDTH = 800;
	int HEIGHT = 600;
	int COLLISON_HEIGHT = HEIGHT - 60;
	int POWERBAR_SIZE = 50;
	
	double SLOW = 0.3;
	int ANGLE_RANDOM = 150;
	
	double POWERUP_DROP_SPEED = 2*0.3;
	int POWERUP_SIZE = 5;
	
	int SLOWDOWN_TIMER = 3000;
	
	int NEXT_LEVEL_DELAY = 3000;
	
	int PLAYER_SIZE = 5;
	int PLAYER_SPEED = 2;
	int PLAYER_LIVES = 3;
	int PLAYER_FIRING_DELAY = 200;
}
