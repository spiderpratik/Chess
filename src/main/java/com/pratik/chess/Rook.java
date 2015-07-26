package com.pratik.chess;

public class Rook extends Piece {

	protected Rook(Color color, String origin) {
		super(color, origin, PieceType.ROOK);
	}

	@Override
	public boolean canMove(Square target, Board board) throws InvalidSquareException {
		if (this.getSquare().equals(target))
			return false;
		if (this.getSquare().rank() != target.rank() && this.getSquare().file() != target.file())
			return false;
		if (target.getPiece().getColor() == this.getColor())
			return false;
		char f = this.getSquare().file();
		int fdir = direction(f, target.file()); 
		byte r = this.getSquare().rank();
		int rdir = direction(r, target.rank());
		for (; f < target.file() && r < target.rank(); f = (char) (f + fdir), r = (byte) (r + rdir))
			if (!board.getSquares().get(Square.getString(f, r)).equals(Piece.BLANK))
				return false;
		return true;
	}
}
