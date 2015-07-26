package com.pratik.chess;

public class Logger {

	private int counter = 1;
	private Color turn;
	private StringBuffer longNotation = new StringBuffer();
	private StringBuffer shortNotation = new StringBuffer();

	public Logger() {
		this(1, Color.WHITE, null, null);
	}

	public Logger(int counter, Color turn, String longNotation, String shortNotation) {
		if (longNotation == null)
			this.longNotation = new StringBuffer();
		else
			this.longNotation = new StringBuffer(longNotation);
		if (shortNotation == null)
			this.shortNotation = new StringBuffer();
		else
			this.shortNotation = new StringBuffer(shortNotation);
		this.counter = counter;
		this.turn = turn;
	}

	public void addMove(Piece p, Square from, Square to, Board board) {
		// TODO - also do white and black turns
		boolean takes = to.getPiece() != Piece.BLANK;
		longNotation.append(p.getPieceType().longName() + from.toString() + (takes ? "x" : "-") + to.toString());
		// DO WORK FOR SHORT NOTATION
		shortNotation.append(p.getPieceType().shortName() + from.toString() + (takes ? "x" : "-") + to.toString());
	}

	public String getPNG() {
		// TODO
		return null;
	}

	public void writePNG(String fileName) {
		// TODO
	}

	public void writePNGlong(String fileName) {
		// TODO
	}
	
	public static FEN loadPNG(Board board, String fileName) {
		// TODO
		return null;
	}
}
