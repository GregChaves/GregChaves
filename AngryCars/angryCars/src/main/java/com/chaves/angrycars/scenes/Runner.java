package com.chaves.angrycars.scenes;

/**
 * @author gregorio.de.chaves
 * 
 */
public class Runner {

	static Runner runner = null;
	public static boolean isGamePlaying;
	public static boolean isGamePaused;

	private Runner() {
	}

	public static Runner check() {
		if (runner != null) {
			runner = new Runner();
		}
		return runner;
	}

	public static boolean isGamePlaying() {
		return isGamePlaying;
	}

	public static boolean isGamePaused() {
		return isGamePaused;
	}

	public static void setGamePlaying(boolean isGamePlaying) {
		Runner.isGamePlaying = isGamePlaying;
	}

	public static void setGamePaused(boolean isGamePaused) {
		Runner.isGamePaused = isGamePaused;
	}
}