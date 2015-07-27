package com.pratik.chess;

public class Bishop extends Piece {

	public Bishop(Color color, String origin) throws InvalidPositionException {
		super(color, origin, PieceType.BISHOP);
		if (!origin.equals(Piece.KINGSIDE) && !origin.equals(Piece.QUEENSIDE))
			throw new InvalidPositionException("Origin incorrectly set");
	}

	@Override
	public boolean canMove(Square target, Board board) throws InvalidSquareException {
		if (this.getSquare().equals(target))
			return false;
		if (!(this.getSquare().rank() + this.getSquare().file() == target.rank() + target.file()) && !(Math
				.abs(this.getSquare().rank() - this.getSquare().file()) == Math.abs(target.rank() - target.file())))
			return false;
		if (target.getPiece().getColor() == this.getColor())
			return false;
		char f = this.getSquare().file();
		int fdir = direction(f, target.file());
		byte r = this.getSquare().rank();
		int rdir = direction(r, target.rank());
		for (; f != target.file() && r != target.rank(); f = (char) (f + fdir), r = (byte) (r + rdir))
			if (!board.getSquares().get(Square.getString(f, r)).equals(Piece.BLANK))
				return false;
		return true;
	}
}
