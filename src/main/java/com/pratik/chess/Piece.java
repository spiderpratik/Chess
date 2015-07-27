package com.pratik.chess;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public abstract class Piece {

	private final Color color;
	private final String origin;
	private Square square;
	private PieceType type;

	public static final String KINGSIDE = "kingside", QUEENSIDE = "queenside", PAWN = PieceType.PAWN.fullName();

	public static final Piece BLANK = new Piece(null, "blank", PieceType.BLANK) {
		@Override
		public boolean canMove(Square target, Board board) {
			return false;
		}
	};

	protected Piece(Color color, String origin, PieceType type) {
		this.color = color;
		this.origin = origin;
		this.type = type;
		this.square = null;
	}

	@Override
	public final boolean equals(Object o) {
		if (this == o)
			return true;
		if (o == null)
			return false;
		if (o.getClass() != this.getClass())
			return false;
		Piece p = (Piece) o;
		if (p.getPieceType() != this.getPieceType())
			return false;
		if (p.getColor() != this.getColor())
			return false;
		if (p.getOrigin().equals(this.getOrigin()))
			return false;
		return true;
	}

	public final String getOrigin() {
		return origin;
	}

	public final Color getColor() {
		return color;
	}

	public final PieceType getPieceType() {
		return type;
	}

	public final Square getSquare() throws InvalidSquareException {
		if (square == null)
			throw new InvalidSquareException("Square for <" + this.toString() + "> is not yet set");
		return square;
	}

	public abstract boolean canMove(Square target, Board board) throws InvalidSquareException;

	@Override
	public final String toString() {
		System.err.println(color);
		System.err.println(square);
		System.err.println(origin);
		System.err.println(type);
		String str = getColor().toString() + " " + getOrigin() + " " + getPieceType().toString() + " at ";
		try {
			str += getSquare().toString();
		} catch (NullPointerException | InvalidSquareException e) {
			str += "null";
		}
		return str;
	}

	public final void setSquare(Square square) throws InvalidSquareException {
		this.square = square;
		if (square.getPiece() != this)
			throw new InvalidSquareException("Multiple piece <" + this.toString() + ", " + square.getPiece().toString()
					+ "> on same Square <" + square.toString() + ">");
	}

	public static final Collection<Piece> newPieces(Color color) {
		List<Piece> pieces = new ArrayList<Piece>(20);
		try {
			for (char f = 'a'; f <= 'h'; f++)
				pieces.add(new Pawn(color, f));
			pieces.add(new King(color));
			pieces.add(new Queen(color));
			pieces.add(new Rook(color, Piece.KINGSIDE));
			pieces.add(new Rook(color, Piece.QUEENSIDE));
			pieces.add(new Knight(color, Piece.KINGSIDE));
			pieces.add(new Knight(color, Piece.QUEENSIDE));
			pieces.add(new Bishop(color, Piece.KINGSIDE));
			pieces.add(new Bishop(color, Piece.QUEENSIDE));
		} catch (InvalidPositionException e) {
			e.printStackTrace();
		}
		return pieces;
	}

	protected static int direction(int from, int to) {
		return ((from < to) ? 1 : ((from > to) ? -1 : 0));
	}
}
