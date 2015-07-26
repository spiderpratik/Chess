package com.pratik.chess;

public class InvalidMoveException extends Exception {

	public InvalidMoveException(String msg) {
		super(msg);
	}
	
	public InvalidMoveException(String msg, Throwable t) {
		super(msg, t);
	}
}
