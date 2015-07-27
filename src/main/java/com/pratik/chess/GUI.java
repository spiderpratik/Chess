package com.pratik.chess;

public class GUI {

	public static void drawBoard(Board board) {
		try {
			System.out.println(board.getLogger().getLastMove() + "\t" +
					(board.castlability(Color.WHITE, Piece.KINGSIDE) ? "K" : "") +
					(board.castlability(Color.WHITE, Piece.QUEENSIDE) ? "Q" : "") +
					(board.castlability(Color.BLACK, Piece.KINGSIDE) ? "k" : "") +
					(board.castlability(Color.BLACK, Piece.QUEENSIDE) ? "q" : "") +
					"\t" + (board.getEnpassantSquare() == null ? board.getEnpassantSquare().toString() : "-"));
		} catch (InvalidSquareException e) {
			e.printStackTrace();
		}
		System.out.println();
		for (char f = 'a'; f <= 'h'; f++) {
			for (byte r = 1; r < 8; r++) {
				Piece p = board.getSquares().get(Square.getString(f, r)).getPiece();
				System.out.print(p.getColor() == Color.WHITE ? p.getPieceType().longName()
						: Character.toLowerCase(p.getPieceType().longName().charAt(0)));
			}
			System.out.println();
		}
		System.out.println();
	}
}
