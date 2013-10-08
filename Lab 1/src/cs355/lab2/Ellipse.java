package cs355.lab2;

import java.awt.Color;

public class Ellipse extends Shape {

	private double width;
	private double height;

	public Ellipse(Color color, double x, double y, double width, double height) {
		super(color, x, y);
		this.width = width;
		this.height = height;
	}

	public double getWidth() {
		return width;
	}

	public void setWidth(double width) {
		this.width = width;
	}

	public double getHeight() {
		return height;
	}

	public void setHeight(double height) {
		this.height = height;
	}
}
