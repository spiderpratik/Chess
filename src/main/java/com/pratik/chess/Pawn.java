package com.pratik.chess;

public class Pawn extends Piece {

	public Pawn(Color color, char origin) throws InvalidPositionException {
		super(color, origin + PAWN, PieceType.PAWN);
		if (origin < 'a' || origin > 'h')
			throw new InvalidPositionException("Origin incorrectly set");
	}

	@Override
	public boolean canMove(Square target, Board board) throws InvalidSquareException {
		if (target == this.getSquare())
			return false;
		if (Math.abs(this.getSquare().file() - target.file()) > 1)
			return false;
		if (this.getSquare().file() == target.file()) {
			// normal move
			if (target.rank() - this.getSquare().rank() == (this.getColor() == Color.WHITE ? 1 : -1))
				return true;
			// 2-step move
			if (target.rank() - this.getSquare().rank() == (this.getColor() == Color.WHITE ? 2 : -2) && 
					this.getSquare().rank() == (this.getColor() == Color.WHITE ? 2 : 7))
				return true;
		} else {
			Piece otherPawn;
			// En Passant move
			if (board.getEnpassantSquare() == target)
				otherPawn = board.getSquares().get(Square.getString(target.file(), this.getSquare().rank())).getPiece();
			else // Kill Move
				otherPawn = target.getPiece();
			
			if (otherPawn.getPieceType() == PieceType.PAWN && otherPawn.getColor() != this.getColor())
					return true;
		}
		return false;
	}
}
