package cs355.lab1;

import java.awt.geom.Point2D;
import java.util.ArrayList;
import java.util.List;

public class MyModel {

	private List<Shape> shapes;

	public MyModel() {
		shapes = new ArrayList<>();
	}

	public List<Shape> getShapes() {
		return shapes;
	}

	public void addShape(Shape shape) {
		shapes.add(shape);
	}

	public void removeShape(Shape shape) {
		shapes.remove(shape);
	}

	public Point2D testHandles(double x, double y, Shape selected) {

		if (selected instanceof Line) {

			Line line = (Line) selected;

			if ((x - line.getX1()) * (x - line.getX1()) + (y - line.getY1())
					* (y - line.getY1()) <= (3) * (3)) {

				double x1 = line.getX1();
				double y1 = line.getY1();
				line.setX1(line.getX2());
				line.setY1(line.getY2());
				line.setX2(x1);
				line.setY2(y1);

				return new Point2D.Double(line.getX2(), line.getY2());
			} else if ((x - line.getX2()) * (x - line.getX2())
					+ (y - line.getY2()) * (y - line.getY2()) <= (3) * (3)) {
				return new Point2D.Double(line.getX2(), line.getY2());
			}
		}

		x -= selected.getX();
		y -= selected.getY();

		if (selected instanceof Square) {

			Square square = (Square) selected;

			if ((x + square.getSize() / 2) * (x + square.getSize() / 2)
					+ (y + square.getSize() / 2) * (y + square.getSize() / 2) <= (3) * (3)) {
				return new Point2D.Double(selected.getX() + square.getSize()
						/ 2, selected.getY() + square.getSize() / 2);
			} else if ((x - square.getSize() / 2) * (x - square.getSize() / 2)
					+ (y + square.getSize() / 2) * (y + square.getSize() / 2) <= (3) * (3)) {
				return new Point2D.Double(selected.getX() - square.getSize()
						/ 2, selected.getY() + square.getSize() / 2);
			} else if ((x + square.getSize() / 2) * (x + square.getSize() / 2)
					+ (y - square.getSize() / 2) * (y - square.getSize() / 2) <= (3) * (3)) {
				return new Point2D.Double(selected.getX() + square.getSize()
						/ 2, selected.getY() - square.getSize() / 2);
			} else if ((x - square.getSize() / 2) * (x - square.getSize() / 2)
					+ (y - square.getSize() / 2) * (y - square.getSize() / 2) <= (3) * (3)) {
				return new Point2D.Double(selected.getX() - square.getSize()
						/ 2, selected.getY() - square.getSize() / 2);
			}
		} else if (selected instanceof Rectangle) {

			Rectangle rectangle = (Rectangle) selected;

			if ((x + rectangle.getWidth() / 2) * (x + rectangle.getWidth() / 2)
					+ (y + rectangle.getHeight() / 2)
					* (y + rectangle.getHeight() / 2) <= (3) * (3)) {
				return new Point2D.Double(selected.getX()
						+ rectangle.getWidth() / 2, selected.getY()
						+ rectangle.getHeight() / 2);
			} else if ((x - rectangle.getWidth() / 2)
					* (x - rectangle.getWidth() / 2)
					+ (y + rectangle.getHeight() / 2)
					* (y + rectangle.getHeight() / 2) <= (3) * (3)) {
				return new Point2D.Double(selected.getX()
						- rectangle.getWidth() / 2, selected.getY()
						+ rectangle.getHeight() / 2);
			} else if ((x + rectangle.getWidth() / 2)
					* (x + rectangle.getWidth() / 2)
					+ (y - rectangle.getHeight() / 2)
					* (y - rectangle.getHeight() / 2) <= (3) * (3)) {
				return new Point2D.Double(selected.getX()
						+ rectangle.getWidth() / 2, selected.getY()
						- rectangle.getHeight() / 2);
			} else if ((x - rectangle.getWidth() / 2)
					* (x - rectangle.getWidth() / 2)
					+ (y - rectangle.getHeight() / 2)
					* (y - rectangle.getHeight() / 2) <= (3) * (3)) {
				return new Point2D.Double(selected.getX()
						- rectangle.getWidth() / 2, selected.getY()
						- rectangle.getHeight() / 2);
			}
		} else if (selected instanceof Circle) {

			Circle circle = (Circle) selected;

			if ((x + circle.getRadius()) * (x + circle.getRadius())
					+ (y + circle.getRadius()) * (y + circle.getRadius()) <= (3) * (3)) {
				return new Point2D.Double(selected.getX() + circle.getRadius(),
						selected.getY() + circle.getRadius());
			} else if ((x - circle.getRadius()) * (x - circle.getRadius())
					+ (y + circle.getRadius()) * (y + circle.getRadius()) <= (3) * (3)) {
				return new Point2D.Double(selected.getX() - circle.getRadius(),
						selected.getY() + circle.getRadius());
			} else if ((x + circle.getRadius()) * (x + circle.getRadius())
					+ (y - circle.getRadius()) * (y - circle.getRadius()) <= (3) * (3)) {
				return new Point2D.Double(selected.getX() + circle.getRadius(),
						selected.getY() - circle.getRadius());
			} else if ((x - circle.getRadius()) * (x - circle.getRadius())
					+ (y - circle.getRadius()) * (y - circle.getRadius()) <= (3) * (3)) {
				return new Point2D.Double(selected.getX() - circle.getRadius(),
						selected.getY() - circle.getRadius());
			}
		} else if (selected instanceof Ellipse) {

			Ellipse ellipse = (Ellipse) selected;

			if ((x + ellipse.getWidth() / 2) * (x + ellipse.getWidth() / 2)
					+ (y + ellipse.getHeight() / 2)
					* (y + ellipse.getHeight() / 2) <= (3) * (3)) {
				return new Point2D.Double(selected.getX() + ellipse.getWidth()
						/ 2, selected.getY() + ellipse.getHeight() / 2);
			} else if ((x - ellipse.getWidth() / 2)
					* (x - ellipse.getWidth() / 2)
					+ (y + ellipse.getHeight() / 2)
					* (y + ellipse.getHeight() / 2) <= (3) * (3)) {
				return new Point2D.Double(selected.getX() - ellipse.getWidth()
						/ 2, selected.getY() + ellipse.getHeight() / 2);
			} else if ((x + ellipse.getWidth() / 2)
					* (x + ellipse.getWidth() / 2)
					+ (y - ellipse.getHeight() / 2)
					* (y - ellipse.getHeight() / 2) <= (3) * (3)) {
				return new Point2D.Double(selected.getX() + ellipse.getWidth()
						/ 2, selected.getY() - ellipse.getHeight() / 2);
			} else if ((x - ellipse.getWidth() / 2)
					* (x - ellipse.getWidth() / 2)
					+ (y - ellipse.getHeight() / 2)
					* (y - ellipse.getHeight() / 2) <= (3) * (3)) {
				return new Point2D.Double(selected.getX() - ellipse.getWidth()
						/ 2, selected.getY() - ellipse.getHeight() / 2);
			}
		} else if (selected instanceof Triangle) {

			Triangle triangle = (Triangle) selected;

			if ((x - triangle.getX1()) * (x - triangle.getX1())
					+ (y - triangle.getY1()) * (y - triangle.getY1()) <= (3) * (3)) {
				triangle.setCornerSelected(0);
				return new Point2D.Double(selected.getX() + triangle.getX1(),
						selected.getY() + triangle.getY1());
			} else if ((x - triangle.getX2()) * (x - triangle.getX2())
					+ (y - triangle.getY2()) * (y - triangle.getY2()) <= (3) * (3)) {
				triangle.setCornerSelected(1);
				return new Point2D.Double(selected.getX() + triangle.getX2(),
						selected.getY() + triangle.getY2());
			} else if ((x - triangle.getX3()) * (x - triangle.getX3())
					+ (y - triangle.getY3()) * (y - triangle.getY3()) <= (3) * (3)) {
				triangle.setCornerSelected(2);
				return new Point2D.Double(selected.getX() + triangle.getX3(),
						selected.getY() + triangle.getY3());
			}
		}

		return null;
	}

	public Shape getSelectedShape(double x, double y, int tolerance) {

		for (int i = shapes.size() - 1; i >= 0; i--) {

			if (shapes.get(i) instanceof Square) {

				Square square = (Square) shapes.get(i);

				if (Math.abs(x - square.getX()) <= square.getSize() / 2
						&& Math.abs(y - square.getY()) <= square.getSize() / 2) {
					return square;
				}
			} else if (shapes.get(i) instanceof Rectangle) {

				Rectangle rectangle = (Rectangle) shapes.get(i);

				if (Math.abs(x - rectangle.getX()) <= rectangle.getWidth() / 2
						&& Math.abs(y - rectangle.getY()) <= rectangle
								.getHeight() / 2) {
					return rectangle;

				}

			} else if (shapes.get(i) instanceof Circle) {

				Circle circle = (Circle) shapes.get(i);

				if ((x - circle.getX()) * (x - circle.getX())
						+ (y - circle.getY()) * (y - circle.getY()) <= circle
						.getRadius() * circle.getRadius()) {
					return circle;
				}

			} else if (shapes.get(i) instanceof Ellipse) {

				Ellipse ellipse = (Ellipse) shapes.get(i);

				if ((x - ellipse.getX())
						* (x - ellipse.getX())
						/ ((ellipse.getWidth() / 2) * (ellipse.getWidth() / 2))
						+ (y - ellipse.getY())
						* (y - ellipse.getY())
						/ ((ellipse.getHeight() / 2) * (ellipse.getHeight() / 2)) <= 1) {
					return ellipse;
				}

			} else if (shapes.get(i) instanceof Triangle) {

				Triangle triangle = (Triangle) shapes.get(i);
				x -= triangle.getX();
				y -= triangle.getY();

				double normalX = -(triangle.getY2() - triangle.getY1());
				double normalY = triangle.getX2() - triangle.getX1();
				double dotProduct1 = normalX * (x - triangle.getX1()) + normalY
						* (y - triangle.getY1());

				normalX = -(triangle.getY3() - triangle.getY2());
				normalY = triangle.getX3() - triangle.getX2();
				double dotProduct2 = normalX * (x - triangle.getX2()) + normalY
						* (y - triangle.getY2());

				normalX = -(triangle.getY1() - triangle.getY3());
				normalY = triangle.getX1() - triangle.getX3();
				double dotProduct3 = normalX * (x - triangle.getX3()) + normalY
						* (y - triangle.getY3());

				if (dotProduct1 >= 0 && dotProduct2 >= 0 && dotProduct3 >= 0) {
					return triangle;
				} else if (dotProduct1 <= 0 && dotProduct2 <= 0
						&& dotProduct3 <= 0) {
					return triangle;
				}

				x += triangle.getX();
				y += triangle.getY();

			} else if (shapes.get(i) instanceof Line) {

				Line line = (Line) shapes.get(i);

				double length = Math.sqrt((line.getX2() - line.getX1())
						* (line.getX2() - line.getX1())
						+ (line.getY2() - line.getY1())
						* (line.getY2() - line.getY1()));
				double dx = (line.getX2() - line.getX1()) / length;
				double dy = (line.getY2() - line.getY1()) / length;

				double dotProduct = (x - line.getX1()) * dx
						+ (y - line.getY1()) * dy;

				if (0 <= dotProduct && dotProduct <= length) {

					double projectionX = line.getX1() + dotProduct * dx;
					double projectionY = line.getY1() + dotProduct * dy;

					if ((x - projectionX) * (x - projectionX)
							+ (y - projectionY) * (y - projectionY) <= tolerance
							* tolerance) {
						return line;
					}
				}
			}
		}

		return null;
	}
}
