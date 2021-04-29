package shooterGame;

import factory.ShooterFactory;

/**
 * second wave of enemies
 */
public class LevelTwo implements Level, Commons{
	
	private boolean exit;
	private Thread enemyWaveThread;
	private ShooterFactory factory;
	
	/**
	 * constructor
	 */
	public LevelTwo() {
		factory = ShooterFactory.getInstance();
		start();
	}

	@Override
	public void start() {
		for(int i = 0; i < 8; i++) {
			GamePanel.enemies.add(factory.createSplitEnemy());
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
							factory.createLevelThree();
		
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
