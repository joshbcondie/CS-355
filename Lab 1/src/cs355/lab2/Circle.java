package cs355.lab2;

import java.awt.Color;

public class Circle extends Shape {

	private double radius;

	public Circle(Color color, double x, double y, double radius) {
		super(color, x, y);
		this.radius = radius;
	}

	public double getRadius() {
		return radius;
	}

	public void setRadius(double radius) {
		this.radius = radius;
	}
}
