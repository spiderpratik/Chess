package com.pratik.chess;

import java.util.Collection;

public class King extends Piece {
	
	public King(Color color, String origin) throws InvalidPositionException {
		super(color, origin, PieceType.KING);
		if (!origin.equals(Piece.KINGSIDE))
			throw new InvalidPositionException("Origin incorrectly set");
	}

	@Override
	public boolean canMove(Square target, Board board) throws InvalidSquareException {
		if (this.getSquare().equals(target))
			return false;
		if (this.isValidCastlingMove(target, board))
			return true;
		if (!this.isInRadiusOf(target))
			return false;
		if (target.getPiece().getColor() == this.getColor())
			return false;
		if (this.isChecked(target, board))
			return false;
		return true;
	}
	
	public boolean isChecked(Square target, Board board) throws InvalidSquareException {
		Color otherColor = this.getColor() == Color.WHITE ? Color.BLACK : Color.WHITE;
		Collection<Piece> otherPieces = board.getPieces(null, otherColor);
		King otherKing = (King) board.getPieces(PieceType.KING, otherColor).toArray()[0];
		otherPieces.remove(otherKing);
		for (Piece p : otherPieces)
			if (p.canMove(target, board))
				return true;
		if (otherKing.isInRadiusOf(target))
			return true;
		return false;
	}
	
	public boolean isValidCastlingMove(Square target, Board board) throws InvalidSquareException {
		// heavily based on assumption that castling flag is set correctly
		String side = target.file() > this.getSquare().file() ? Piece.KINGSIDE : Piece.QUEENSIDE;
		char rook = target.file() > this.getSquare().file() ? 'h' : 'a';
		if (!board.castlability(this.getColor(), side))
			return false;
		byte r = target.rank();
		if (Math.abs(this.getSquare().file() - target.file()) != 2 || this.getSquare().rank() != r)
			return false;
		if ((r != 1 && this.getColor() == Color.WHITE) || (r != 8 && this.getColor() == Color.BLACK))
			return false;
		Piece theKing = board.getSquares().get(Square.getString('e', (byte) (this.getColor() == Color.WHITE ? 1 : 8))).getPiece();
		Piece theRook = board.getSquares().get(Square.getString(rook, (byte) (this.getColor() == Color.WHITE ? 1 : 8))).getPiece();
		if (theKing != this || theRook.getPieceType() != PieceType.ROOK || theRook.getColor() != this.getColor())
			return false;
		char f = 'e';
		int fdir = direction(f, target.file());
		for (;f != rook; f = (char) (f + fdir))
			if (!board.getSquares().get(Square.getString(f, r)).equals(Piece.BLANK))
				return false;
		// all 3 (e,f,g or e,d,c) squares no check should be there
		f = 'e';
		for (int i = 0; i < 3; f = (char) (f + fdir))
			if (this.isChecked(board.getSquares().get(Square.getString(f, r)), board))
				return false;
		return false;
	}
	
	public boolean isInRadiusOf(Square target) throws InvalidSquareException {
		if (Math.abs(this.getSquare().rank() - target.rank()) <= 1 && Math.abs(this.getSquare().file() - target.file()) <= 1)
			return true;
		else return false;
	}
}
