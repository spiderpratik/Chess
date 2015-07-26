package com.pratik.chess;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Map;

public class Board {

	private final Map<String, Square> board;
	private final Collection<Piece> whites;
	private final Collection<Piece> blacks;
	private Logger log; // TODO: make a dummy logger for edited boards

	private Square enPassant;
	private Color turn;
	// Castling Availability flags
	private boolean castlewk, castlewq, castlebk, castlebq;
	// Number of halfmoves since the last capture or pawn advance. 50-move rule
	private int halfmoves;
	// Move Counter
	private int fullmoves;

	public Board() throws InvalidSquareException {
		board = Square.newSquares();
		whites = new ArrayList<Piece>(20);
		blacks = new ArrayList<Piece>(20);
		log = new Logger();
		setupStartPosition();
	}

	public void clear() throws InvalidPositionException {
		whites.clear();
		blacks.clear();
		for (Square s : board.values()) {
			s.setPiece(Piece.BLANK);
		}
	}

	public void setStartPosition() throws InvalidPositionException {
		clear();
		setupStartPosition();
	}
	
	private void setupStartPosition() {
		whites.addAll(Piece.newPieces(Color.WHITE));
		blacks.addAll(Piece.newPieces(Color.BLACK));
		turn = Color.WHITE;
		enPassant = null;
		halfmoves = 0;
		fullmoves = 1;
		try {
			for (Piece p : whites)
				p.setSquare(board.get(getStandardSquare(p)));
			for (Piece p : blacks)
				p.setSquare(board.get(getStandardSquare(p)));
		} catch (InvalidPositionException | InvalidSquareException e) {
			e.printStackTrace();
		}
		castlewk = castlewq = castlebk =
		castlebq = true;
		enPassant = null;
	}
	
	public void importFEN(String fenString) throws InvalidPositionException {
		FEN fen = FEN.setup(this, fenString);
		clear();
		FENsetup(fen);
	}
	
	public String exportFEN() {
		FEN fen = new FEN(halfmoves, fullmoves, turn, castlewk, castlewq, castlebk, castlebq, null, null, enPassant);
		return fen.getFEN(this);
	}
	
	public void loadPNG(String fileName) throws InvalidPositionException {
		clear();
		FEN fen = Logger.loadPNG(this, fileName);
		FENsetup(fen);
	}
	
	private void FENsetup(FEN fen) {
		this.halfmoves = fen.halfmoves;
		this.fullmoves = fen.fullmoves;
		this.turn = fen.turn;
		this.castlebk = fen.castlebk;
		this.castlewk = fen.castlewk;
		this.castlebq = fen.castlebq;
		this.castlewq = fen.castlewq;
		this.whites.addAll(fen.whites);
		this.blacks.addAll(fen.blacks);
		this.enPassant = fen.enPassant;
	}
	
	public void makeMove(Piece p, Square from, Square to) {
		// TODO
		log.addMove(p, from, to, this);
	}
	
	public Collection<Piece> getMovablePiecesTo(Square target, Color color) {
		// TODO
		return null;
	}
	
	public Square getEnpassantSquare() {
		return enPassant;
	}

	public static String getStandardSquare(Piece p) throws InvalidPositionException {
		char file;
		byte rank;
		if (p.getPieceType() == PieceType.PAWN) {
			file = p.getOrigin().charAt(0);
			if (file < 'a' || file > 'h' || !p.getOrigin().substring(1).equals(Piece.PAWN))
				throw new InvalidPositionException("Origin incorrectly set");
			rank = (byte) (p.getColor() == Color.WHITE ? 2 : 7);
		}
		else {
			rank = (byte) (p.getColor() == Color.WHITE ? 1 : 8);
			switch (p.getPieceType()) {
			case KING: {
				file = 'e';
				if (!p.getOrigin().equals(Piece.KINGSIDE))
					throw new InvalidPositionException("Origin incorrectly set");
				break;
			}
			case QUEEN: {
				file = 'd';
				if (!p.getOrigin().equals(Piece.QUEENSIDE))
					throw new InvalidPositionException("Origin incorrectly set");
				break;
			}
			case ROOK: {
				file = 'e';
				if (p.getOrigin().equals(Piece.KINGSIDE))
					file = 'h';
				else if (p.getOrigin().equals(Piece.QUEENSIDE))
					file = 'a';
				else
					throw new InvalidPositionException("Origin incorrectly set");
				break;
			}
			case BISHOP: {
				file = 'e';
				if (p.getOrigin().equals(Piece.KINGSIDE))
					file = 'f';
				else if (p.getOrigin().equals(Piece.QUEENSIDE))
					file = 'c';
				else
					throw new InvalidPositionException("Origin incorrectly set");
				break;
			}
			case KNIGHT: {
				file = 'e';
				if (p.getOrigin().equals(Piece.KINGSIDE))
					file = 'g';
				else if (p.getOrigin().equals(Piece.QUEENSIDE))
					file = 'b';
				else
					throw new InvalidPositionException("Origin incorrectly set");
				break;
			}
			default: throw new InvalidPositionException("Invalid Piece Type");
			}
		}
		
		return new String(new char[] {file, (char) rank});
	}
}
