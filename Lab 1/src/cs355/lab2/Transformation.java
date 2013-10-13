package cs355.lab2;

import java.awt.geom.AffineTransform;
import java.awt.geom.Point2D;

public class Transformation {

	public static Point2D objectToWorld(Shape shape, double x, double y) {

		AffineTransform transform = new AffineTransform();

		transform.concatenate(new AffineTransform(new double[] { 1, 0, 0, 1,
				shape.getX(), shape.getY() }));

		transform
				.concatenate(new AffineTransform(new double[] {
						Math.cos(shape.getRotation()),
						Math.sin(shape.getRotation()),
						-Math.sin(shape.getRotation()),
						Math.cos(shape.getRotation()) }));

		Point2D point = transform.transform(new Point2D.Double(x, y), null);
		return point;
	}

	public static Point2D worldToObject(Shape shape, double x, double y) {

		AffineTransform transform = new AffineTransform();

		transform
				.concatenate(new AffineTransform(new double[] {
						Math.cos(shape.getRotation()),
						-Math.sin(shape.getRotation()),
						Math.sin(shape.getRotation()),
						Math.cos(shape.getRotation()) }));

		transform.concatenate(new AffineTransform(new double[] { 1, 0, 0, 1,
				-shape.getX(), -shape.getY() }));

		Point2D point = transform.transform(new Point2D.Double(x, y), null);
		return point;
	}

	public static Point2D worldToView(Viewport viewport, double x, double y) {

		AffineTransform transform = new AffineTransform();

		transform.concatenate(new AffineTransform(new double[] {
				viewport.getScale(), 0, 0, viewport.getScale() }));

		transform.concatenate(new AffineTransform(new double[] { 1, 0, 0, 1,
				-viewport.getX(), -viewport.getY() }));

		Point2D point = transform.transform(new Point2D.Double(x, y), null);
		return point;
	}

	public static Point2D viewToWorld(Viewport viewport, double x, double y) {

		AffineTransform transform = new AffineTransform();

		transform.concatenate(new AffineTransform(new double[] { 1, 0, 0, 1,
				viewport.getX(), viewport.getY() }));

		transform.concatenate(new AffineTransform(new double[] {
				1 / viewport.getScale(), 0, 0, 1 / viewport.getScale() }));

		Point2D point = transform.transform(new Point2D.Double(x, y), null);
		return point;
	}
}
