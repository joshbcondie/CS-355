package cs355.lab2;

import java.awt.geom.AffineTransform;
import java.awt.geom.Point2D;

public class Transformation {

	public static Point2D objectToWorld(Shape shape, double x, double y) {
		AffineTransform transform = new AffineTransform();
		transform.translate(shape.getX(), shape.getY());
		transform.rotate(shape.getRotation());
		Point2D point = transform.transform(new Point2D.Double(x, y), null);
		return point;
	}

	public static Point2D worldToObject(Shape shape, double x, double y) {
		AffineTransform transform = new AffineTransform();
		transform.rotate(-shape.getRotation());
		transform.translate(-shape.getX(), -shape.getY());
		Point2D point = transform.transform(new Point2D.Double(x, y), null);
		return point;
	}
}
