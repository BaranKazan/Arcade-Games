package shooterGame;

import factory.ShooterFactory;

/**
 * third wave of enemies
 */
public class LevelThree implements Level, Commons{

	private boolean exit;
	private Thread enemyWaveThread;
	private ShooterFactory factory;
	
	/**
	 * constructor
	 */
	public LevelThree() {
		factory = ShooterFactory.getInstance();
		start();
	}

	@Override
	public void start() {
		for(int i = 0; i < 16; i++) {
			GamePanel.enemies.add(factory.createHealthEnemy());
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
							factory.createLevelFour();
	
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
