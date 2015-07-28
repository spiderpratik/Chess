package com.pratik.chess;

public class GameCoordinator {

	public void play() {
		Board board = new Board();
		Thread guiThread = new Thread(board.gui());
		guiThread.start();
		
		try {
			// JOINS
			guiThread.join();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}
