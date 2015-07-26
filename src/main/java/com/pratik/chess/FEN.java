package com.pratik.chess;

import java.util.Collection;

public class FEN {

	public final int halfmoves;
	public final int fullmoves;
	public final Color turn;
	public final boolean castlewk, castlewq, castlebk, castlebq;
	public final Collection<Piece> whites, blacks;
	public final Square enPassant;
	
	public FEN(int halfmoves, int fullmoves, Color turn, boolean castlewk, boolean castlewq, boolean castlebk, boolean castlebq, Collection<Piece> whites, Collection<Piece> blacks, Square enPassant)
	{
		this.halfmoves = halfmoves;
		this.fullmoves = fullmoves;
		this.turn = turn;
		this.castlebk = castlebk;
		this.castlewk = castlewk;
		this.castlebq = castlebq;
		this.castlewq = castlewq;
		this.whites = whites;
		this.blacks = blacks;
		this.enPassant = enPassant;
	}
	
	public static void validate(String fen) throws InvalidPositionException {
		// TODO
		throw new InvalidPositionException("Invalid FEN");
	}
	
	public static FEN setup(Board board, String fenString) throws InvalidPositionException {
		validate(fenString);
		// TODO
		FEN fen = null;
		return fen;
	}
	
	public String getFEN(Board board) {
		StringBuffer fenString = new StringBuffer(100);
		// TODO
		return fenString.toString();
	}
}
