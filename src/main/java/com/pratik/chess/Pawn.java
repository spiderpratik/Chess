package com.pratik.chess;

public class Pawn extends Piece {

	public Pawn(Color color, char origin) throws InvalidPositionException {
		super(color, origin + PAWN, PieceType.PAWN);
		if (origin < 'a' || origin > 'h')
			throw new InvalidPositionException("Origin incorrectly set");
	}

	@Override
	public boolean canMove(Square target, Board board) throws InvalidSquareException {
		// TODO normal move
		// TODO kill move
		// TODO 2-step move
		// TODO enPassantmove
		return false;
	}
}
