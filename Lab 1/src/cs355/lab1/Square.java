package cs355.lab1;

import java.awt.Color;

public class Square extends Shape {

	private double size;

	public Square(Color color, double x, double y, double size) {
		super(color, x, y);
		this.size = size;
	}

	public double getSize() {
		return size;
	}

	public void setSize(double size) {
		this.size = size;
	}
}
