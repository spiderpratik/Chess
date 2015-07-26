package com.pratik.chess;

public class InvalidSquareException extends Exception {

	public InvalidSquareException(String msg) {
		super(msg);
	}
	
	public InvalidSquareException(String msg, Throwable t) {
		super(msg, t);
	}

}
