package com.pratik.chess;

public enum Color {
	WHITE ("white"),
	BLACK ("black");
	
	private final String color;
	
	Color(String color) {
		this.color = color;
	}
	
	@Override
	public String toString() {
		return color;
	}
}
