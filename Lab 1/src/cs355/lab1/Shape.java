package cs355.lab1;

import java.awt.Color;

public abstract class Shape {

	private Color color;
	private double x;
	private double y;
	private double rotation;
	
	protected Shape(Color color, double x, double y) {
		this.color = color;
		this.x = x;
		this.y = y;
		rotation = 0;
	}

	public Color getColor() {
		return color;
	}

	public void setColor(Color color) {
		this.color = color;
	}

	public double getX() {
		return x;
	}

	public void setX(double x) {
		this.x = x;
	}

	public double getY() {
		return y;
	}

	public void setY(double y) {
		this.y = y;
	}

	public double getRotation() {
		return rotation;
	}

	public void setRotation(double rotation) {
		this.rotation = rotation;
	}
}
