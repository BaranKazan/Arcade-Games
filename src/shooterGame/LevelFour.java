package shooterGame;

import factory.ShooterFactory;

/**
 * fourth wave of enemies
 */
public class LevelFour implements Level, Commons{

	private boolean exit;
	private Thread enemyWaveThread;
	private ShooterFactory factory;

	/**
	 * constructor
	 */
	public LevelFour() {
		factory = ShooterFactory.getInstance();
		start();
	}

	@Override
	public void start() {
		for(int i = 0; i < 1; i++) {
			GamePanel.enemies.add(factory.createBossEnemy());
		}
		enemyWaveThread = new Thread(new Runnable() {           
			public void run() { 
				exit = false;
				while(!exit) {
					try {
						Thread.sleep(NEXT_LEVEL_DELAY);
						if(GamePanel.enemies.size() == 0) {
							exit = true;
							enemyWaveThread.interrupt();
							factory.createLevelOne();

						}
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
			} 
		});
		enemyWaveThread.start();
	}
}
