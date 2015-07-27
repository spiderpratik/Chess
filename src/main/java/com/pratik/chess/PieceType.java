package com.pratik.chess;

public enum PieceType {
	BLANK ("Blank", "."),
	ROOK ("Rook", "R"),
	KNIGHT ("Knight", "N"),
	BISHOP ("Bishop", "B"),
	QUEEN ("Queen", "Q"),
	PAWN ("Pawn", "P", ""),
	KING ("King", "K");
	
	private final String fullName, longName, shortName;
	
	PieceType(String fullName, String longName, String shortName) {
		this.fullName = fullName;
		this.longName = longName;
		this.shortName = shortName;
	}
	
	PieceType(String fullName, String shortName) {
		this(fullName, shortName, shortName);
	}
	
	public String fullName() {
		return fullName;
	}
	
	public String longName() {
		return longName;
	}
	
	public String shortName() {
		return shortName;
	}
	
	@Override
	public String toString() {
		return fullName;
	}
}
