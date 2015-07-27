package com.pratik.chess;

public class Knight extends Piece {

	public Knight(Color color, String origin) throws InvalidPositionException {
		super(color, origin, PieceType.KNIGHT);
		if (!origin.equals(Piece.KINGSIDE) && !origin.equals(Piece.QUEENSIDE))
			throw new InvalidPositionException("Origin incorrectly set");
	}

	@Override
	public boolean canMove(Square target, Board board) throws InvalidSquareException {
		if (this.getSquare().equals(target))
			return false;
		if (!((Math.abs(this.getSquare().rank() - target.rank()) == 2
				&& Math.abs(this.getSquare().file() - target.file()) == 1)
				|| (Math.abs(this.getSquare().rank() - target.rank()) == 1
						&& Math.abs(this.getSquare().file() - target.file()) == 2)))
			return false;
		if (target.getPiece().getColor() == this.getColor())
			return false;
		return true;
	}
}
