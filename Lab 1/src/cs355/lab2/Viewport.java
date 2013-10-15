package cs355.lab2;

import java.awt.geom.AffineTransform;

public class Viewport {

	private final double WIDTH = 512;
	private final double HEIGHT = 512;

	private double x;
	private double y;
	private double scale;

	public Viewport() {
		x = 0;
		y = 0;
		scale = 1;
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

	public void zoomIn() {
		if (scale <= 2) {
			scale *= 2;
			x += WIDTH / (2 * scale);
			y += HEIGHT / (2 * scale);
		}
	}

	public void zoomOut() {
		if (scale >= 0.5) {
			scale /= 2;
			x -= WIDTH / (4 * scale);
			y -= HEIGHT / (4 * scale);
		}
	}

	public double getScale() {
		return scale;
	}

	public AffineTransform getWorldToView() {

		AffineTransform transform = new AffineTransform();

		transform.concatenate(new AffineTransform(new double[] { scale, 0, 0,
				scale }));

		transform.concatenate(new AffineTransform(new double[] { 1, 0, 0, 1,
				-x, -y }));

		return transform;
	}

	public AffineTransform getViewToWorld() {

		AffineTransform transform = new AffineTransform();

		transform.concatenate(new AffineTransform(new double[] { 1, 0, 0, 1, x,
				y }));

		transform.concatenate(new AffineTransform(new double[] { 1 / scale, 0,
				0, 1 / scale }));

		return transform;
	}
}
