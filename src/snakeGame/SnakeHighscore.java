package snakeGame;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

import arcade.Highscore;
import state.State;
import view.SnakePlayerScoreView;

/**
 * Highscore of snake game.
 */

public class SnakeHighscore implements Highscore<SnakePlayerScoreView>{

	public static final SnakeHighscore instance = new SnakeHighscore();
	private File highScoreFile;
	private String fileName;
	private final int TOP_FIVE = 5;

	/**
	 * Creating the file if it doesn't exits.
	 */
	public SnakeHighscore() {
		fileName = "snake_highscore.txt";
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
		int i = 0;
		int data;
		int leastPoint = score;
		int leastPointIndex = 0;
		try {
			List<String> lines = Files.readAllLines(highScoreFile.toPath());

			while (i < TOP_FIVE) {
				data = Integer.parseInt(lines.get(i).replaceAll("[^0-9.]", ""));
				if (leastPoint > data) {
					leastPoint = data;
					leastPointIndex = i;
				}
				i++;
			}
			if (leastPoint != score) {
				lines.set(leastPointIndex, State.getState().getDifficulty() + " " + Integer.toString(score));
				Files.write(highScoreFile.toPath(), lines);
			}

		} catch (IOException e) {
			e.printStackTrace();
			System.out.println("Error");
		}
	}

	@Override
	public List<SnakePlayerScoreView> getScore() {
		List<String> listOfScore = null;
		List<SnakePlayerScoreView> listOfDomain = new ArrayList<SnakePlayerScoreView>();
		SnakePlayerScoreView domain = null;
		try {
			listOfScore = Files.readAllLines(highScoreFile.toPath());
			for (String s : listOfScore) {
				domain = new SnakePlayerScoreView();
				if (!s.equals("0")) {
					String[] splited = s.split("\\s+");
					domain.setScore(Integer.parseInt(splited[1]));
					listOfDomain.add(domain);
				} else {
					domain.setScore(0);
					listOfDomain.add(domain);
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
			System.out.println("File does not exist?");
		}
		listOfDomain.sort(Comparator.comparingInt(SnakePlayerScoreView::getScore).reversed());
		for (int i = 0; i < listOfDomain.size(); i++) {
			listOfDomain.get(i).setPosition(i + 1);
			listOfDomain.get(i).initView();
		}
		return listOfDomain;
	}

	public static SnakeHighscore getInstance() {
		return instance;
	}

}
