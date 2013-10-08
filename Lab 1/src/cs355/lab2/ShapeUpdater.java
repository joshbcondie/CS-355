package cs355.lab2;

import java.awt.geom.AffineTransform;
import java.awt.geom.NoninvertibleTransformException;
import java.awt.geom.Point2D;

public class ShapeUpdater {

	private double x1;
	private double y1;

	public double getX1() {
		return x1;
	}

	public double getY1() {
		return y1;
	}

	public void setX1(double x1) {
		this.x1 = x1;
	}

	public void setY1(double y1) {
		this.y1 = y1;
	}

	public void updateSquare(Shape shape, double x2, double y2) {

		Square square = (Square) shape;

		Point2D objectCoords1 = Transformation.worldToObject(shape, x1, y1);

		Point2D objectCoords2 = Transformation.worldToObject(shape, x2, y2);

		if (Math.abs(objectCoords2.getX() - objectCoords1.getX()) < Math
				.abs(objectCoords2.getY() - objectCoords1.getY())) {
			square.setSize(Math.abs(objectCoords2.getX() - objectCoords1.getX()));
		} else {
			square.setSize(Math.abs(objectCoords2.getY() - objectCoords1.getY()));
		}

		double localX = 0;

		if (objectCoords2.getX() >= objectCoords1.getX())
			localX = objectCoords1.getX() + square.getSize() / 2;
		else
			localX = objectCoords1.getX() - square.getSize() / 2;

		double localY = 0;

		if (objectCoords2.getY() >= objectCoords1.getY())
			localY = objectCoords1.getY() + square.getSize() / 2;
		else
			localY = objectCoords1.getY() - square.getSize() / 2;

		Point2D worldCoordCenter = Transformation.objectToWorld(shape, localX,
				localY);

		square.setX(worldCoordCenter.getX());
		square.setY(worldCoordCenter.getY());
	}

	public void updateRectangle(Shape shape, double x2, double y2) {

		Rectangle rectangle = (Rectangle) shape;

		Point2D objectCoords1 = Transformation.worldToObject(shape, x1, y1);

		Point2D objectCoords2 = Transformation.worldToObject(shape, x2, y2);

		if (objectCoords2.getX() >= objectCoords1.getX()) {
			rectangle.setWidth(objectCoords2.getX() - objectCoords1.getX());
		} else {
			rectangle.setWidth(objectCoords1.getX() - objectCoords2.getX());
		}
		if (objectCoords2.getY() >= objectCoords1.getY()) {
			rectangle.setHeight(objectCoords2.getY() - objectCoords1.getY());
		} else {
			rectangle.setHeight(objectCoords1.getY() - objectCoords2.getY());
		}

		Point2D worldCoordCenter = Transformation.objectToWorld(shape,
				(objectCoords1.getX() + objectCoords2.getX()) / 2,
				(objectCoords1.getY() + objectCoords2.getY()) / 2);

		rectangle.setX(worldCoordCenter.getX());
		rectangle.setY(worldCoordCenter.getY());
	}

	public void updateCircle(Shape shape, double x2, double y2) {

		Circle circle = (Circle) shape;

		if (Math.abs(x2 - x1) < Math.abs(y2 - y1)) {
			circle.setRadius(Math.abs(x2 - x1) / 2);
			circle.setX((Math.min(x2, x1) + circle.getRadius()));
			if (y2 < y1) {
				circle.setY(y1 - circle.getRadius());
			} else {
				circle.setY(y1 + circle.getRadius());
			}
		} else {
			circle.setRadius(Math.abs(y2 - y1) / 2);
			circle.setY((Math.min(y2, y1) + circle.getRadius()));
			if (x2 < x1) {
				circle.setX(x1 - circle.getRadius());
			} else {
				circle.setX(x1 + circle.getRadius());
			}
		}
	}

	public void updateEllipse(Shape shape, double x2, double y2) {

		Ellipse ellipse = (Ellipse) shape;

		Point2D objectCoords1 = Transformation.worldToObject(shape, x1, y1);

		Point2D objectCoords2 = Transformation.worldToObject(shape, x2, y2);

		if (objectCoords2.getX() >= objectCoords1.getX()) {
			ellipse.setWidth(objectCoords2.getX() - objectCoords1.getX());
		} else {
			ellipse.setWidth(objectCoords1.getX() - objectCoords2.getX());
		}
		if (objectCoords2.getY() >= objectCoords1.getY()) {
			ellipse.setHeight(objectCoords2.getY() - objectCoords1.getY());
		} else {
			ellipse.setHeight(objectCoords1.getY() - objectCoords2.getY());
		}

		Point2D worldCoordCenter = Transformation.objectToWorld(shape,
				(objectCoords1.getX() + objectCoords2.getX()) / 2,
				(objectCoords1.getY() + objectCoords2.getY()) / 2);

		ellipse.setX(worldCoordCenter.getX());
		ellipse.setY(worldCoordCenter.getY());
	}

	public void updateLine(Shape shape, double x2, double y2) {

		Line line = (Line) shape;
		line.setX2(x2);
		line.setY2(y2);
	}

	public void resetTriangleCenter(Triangle triangle) {

		double centerX = (triangle.getX1() + triangle.getX2() + triangle
				.getX3()) / 3;
		double centerY = (triangle.getY1() + triangle.getY2() + triangle
				.getY3()) / 3;

		triangle.setX1(triangle.getX1() - centerX);
		triangle.setY1(triangle.getY1() - centerY);
		triangle.setX2(triangle.getX2() - centerX);
		triangle.setY2(triangle.getY2() - centerY);
		triangle.setX3(triangle.getX3() - centerX);
		triangle.setY3(triangle.getY3() - centerY);

		AffineTransform transform = new AffineTransform();
		transform.rotate(triangle.getRotation());
		Point2D point = new Point2D.Double();
		transform.transform(new Point2D.Double(centerX, centerY), point);
		centerX = point.getX();
		centerY = point.getY();

		triangle.setX(triangle.getX() + centerX);
		triangle.setY(triangle.getY() + centerY);
	}

	public void updateTriangle(Shape shape, double x2, double y2) {

		Triangle triangle = (Triangle) shape;

		AffineTransform transform = new AffineTransform();
		transform.rotate(-triangle.getRotation());
		Point2D point = new Point2D.Double();
		transform.transform(new Point2D.Double(x2, y2), point);
		x2 = point.getX();
		y2 = point.getY();

		switch (triangle.getCornerSelected()) {

		case 0:
			triangle.setX1(x2);
			triangle.setY1(y2);
			break;

		case 1:
			triangle.setX2(x2);
			triangle.setY2(y2);
			break;
		case 2:
			triangle.setX3(x2);
			triangle.setY3(y2);
			break;
		}

		resetTriangleCenter(triangle);
	}

	public void rotateShape(Shape shape, double x2, double y2) {
		shape.setRotation(-Math.atan2(-(x2 - shape.getX()),
				-(y2 - shape.getY())));
	}
}
