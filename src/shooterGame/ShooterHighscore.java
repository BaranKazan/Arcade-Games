package shooterGame;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

import arcade.Highscore;
import view.ShooterPlayerScoreView;

/**
 * high score for shooter game
 */
public class ShooterHighscore implements Commons, Highscore<ShooterPlayerScoreView>{

	public static final ShooterHighscore instance = new ShooterHighscore();
	private File highScoreFile;
	private String fileName;
	private int data, leastPoint, leastPointIndex, i;
	private List<String> listOfScore;
	private ShooterPlayerScoreView domain;

	/**
	 * constructor
	 */
	public ShooterHighscore() {
		init();
	}

	/**
	 * initiates variables and creates file
	 */
	private void init() {
		listOfScore = null;
		domain = null;
		fileName = "shooter_highscore.txt";
		highScoreFile = new File(fileName);
		if (!highScoreFile.exists()) {
			try {
				highScoreFile.createNewFile();
				FileWriter fileWriter = new FileWriter(fileName);
				BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
				for (int i = 0; i < TOP_FIVE; i++) {
					if (i != 4) {
						bufferedWriter.write("0");
						bufferedWriter.newLine();
					} else {
						bufferedWriter.write("0");
					}
				}
				bufferedWriter.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	@Override
	public void writeScore(int score) {
		leastPoint = score;
		leastPointIndex = 0;
		i = 0;
		try {
			List<String> lines = Files.readAllLines(highScoreFile.toPath());

			while (i < TOP_FIVE) {
				data = Integer.parseInt(lines.get(i));
				if (leastPoint > data) {
					leastPoint = data;
					leastPointIndex = i;
				}
				i++;
			}
			if (leastPoint != score) {
				lines.set(leastPointIndex, Integer.toString(score));
				Files.write(highScoreFile.toPath(), lines);
			}

		} catch (IOException e) {
			e.printStackTrace();
			System.out.println("Error");
		}
	}

	@Override
	public List<ShooterPlayerScoreView> getScore() {
		List<ShooterPlayerScoreView> listOfDomain = new ArrayList<ShooterPlayerScoreView>();
		try {
			listOfScore = Files.readAllLines(highScoreFile.toPath());
			for (String s : listOfScore) {
				domain = new ShooterPlayerScoreView();
				domain.setScore(Integer.parseInt(s));
				listOfDomain.add(domain);

			}
		} catch (IOException e) {
			e.printStackTrace();
			System.out.println("File does not exist?");
		}
		listOfDomain.sort(Comparator.comparingInt(ShooterPlayerScoreView::getScore).reversed());
		for (int i = 0; i < listOfDomain.size(); i++) {
			listOfDomain.get(i).setPosition(i + 1);
			listOfDomain.get(i).initView();
		}
		return listOfDomain;
	}

	/**
	 * 
	 * @return instance of class
	 */
	public static ShooterHighscore getInstance() {
		return instance;
	}

}
