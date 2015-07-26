package com.pratik.chess;

import java.util.Hashtable;
import java.util.Map;

public class Square {

	private final char file;
	private final byte rank;
	private Piece piece = Piece.BLANK;
	
	private Square(char file, byte rank) throws InvalidSquareException {
		if (file < 'a' || file > 'h' || rank < 1 || rank > 8)
			throw new InvalidSquareException("Cannot create square '" + file + rank + "'");
		this.file = file;
		this.rank = rank;
	}
	
	public static synchronized final Map<String, Square> newSquares() throws InvalidSquareException {
		Map<String, Square> board = new Hashtable<String, Square>(85, .8f);
		for (char f = 'a'; f <= 'h'; f++)
			for (byte r = 1; r < 8; r++)
				board.put(new String(new char[] {f, (char) r}), new Square(f, r));
		return board;
	}
	
	public char file() {
		return file;
	}
	
	public byte rank() {
		return rank;
	}
	
	public Piece getPiece() {
		return piece;
	}
	
	public Color getColor() {
		return ((file - 'a') + (rank - 1)) % 2 == 0 ? Color.BLACK : Color.WHITE; 
	}
	
	public void setPiece(Piece p) {
		this.piece = p;
		try {
			if (p != Piece.BLANK)
				p.setSquare(this);
		} catch (InvalidSquareException e) {
			e.printStackTrace();
		}
	}
	
	@Override
	public String toString() {
		return new String(new char[] {file, (char) rank});
	}
}
