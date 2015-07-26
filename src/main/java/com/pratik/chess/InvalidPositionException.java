package com.pratik.chess;

public class InvalidPositionException extends Exception{
//for FEN and PNG
	public InvalidPositionException(String msg) {
		super(msg);
	}
	
	public InvalidPositionException(String msg, Throwable t) {
		super(msg, t);
	}
}
